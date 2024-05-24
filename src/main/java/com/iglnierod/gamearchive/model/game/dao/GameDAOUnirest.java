package com.iglnierod.gamearchive.model.game.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iglnierod.gamearchive.model.api.igdb.PostRequest;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.filter.GameFilter;
import com.iglnierod.gamearchive.model.game.rate.GameRate;
import com.iglnierod.gamearchive.model.genre.Genre;
import com.iglnierod.gamearchive.model.genre.dao.GenreDAO;
import com.iglnierod.gamearchive.model.genre.dao.GenreDAOPostgreSQL;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.platform.Platform;
import com.iglnierod.gamearchive.model.platform.dao.PlatformDAO;
import com.iglnierod.gamearchive.model.platform.dao.PlatformDAOPostgreSQL;
import com.iglnierod.gamearchive.model.session.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GameDAOUnirest implements GameDAO {

    private String clientId;
    private String accessToken;
    private static String URL = "https://api.igdb.com/v4/games";
    private final Database database;
    private ExecutorService executorService;

    public GameDAOUnirest(Database database) {
        this.database = database;
        clientId = System.getenv("IGDB_CLIENT_ID");
        accessToken = System.getenv("IGDB_ACCESS_TOKEN");
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public ArrayList<Game> search(String inputText, GameFilter filter) {
        inputText = "\"" + inputText + "\"";
        ArrayList<Game> games = new ArrayList<>();

        String where = getWhereStatement(filter);

        PostRequest pr = PostRequest.builder()
                .fields("name, cover.image_id, summary, rating")
                .search(inputText)
                .where(where)
                .limit(filter.getLimit())
                .build();

        System.out.println("pr.asString(): " + pr.asString());
        String postResult = this.post(URL, pr.asString());
        System.out.println("postResult: " + postResult);

        games = this.parse(postResult);

        return games;
    }

    public String getWhereStatement(GameFilter filter) {
        StringBuilder where = new StringBuilder();
        String platformFilter = "platforms = ";

        if (!filter.isAllPlatforms()) {
            platformFilter += Platform.getPlatformFilterString(Session.getCurrentClient().getPlatformsList());
        } else {
            platformFilter = "";
        }
        where.append(platformFilter);

        if (!where.isEmpty()) {
            where.append(" & ");
        }

        where.append("rating >= ").append(filter.getMinRating());

        if (filter.getGenres() != null) {
            where.append(" & ").append("genres.name = ").append(filter.getGenres());
        }

        return where.toString();
    }

    @Override
    public String post(String url, String body) {
        HttpResponse<String> jsonResponse = Unirest.post(url)
                .header("Client-ID", this.clientId)
                .header("Authorization", "Bearer " + this.accessToken)
                .header("Accept", "application/json")
                .body(body)
                .asString();

        return jsonResponse.getBody();
    }

    @Override
    public ArrayList<Game> parse(String jsonResponse) {
        ArrayList<Game> games = new ArrayList<>();

        JsonArray jsonArray = JsonParser.parseString(jsonResponse).getAsJsonArray();
        Gson gson = new Gson();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonGame = jsonArray.get(i).getAsJsonObject();

            int id = jsonGame.get("id").getAsInt();
            String name = jsonGame.get("name").getAsString();
            String summary = null;
            try {
                summary = jsonGame.get("summary").getAsString();
            } catch (java.lang.NullPointerException e) {
                System.err.println("(" + id + ") Game has no summary");
            }

            JsonObject jsonCover = jsonGame.getAsJsonObject("cover");
            String coverId = null;
            if (jsonCover != null) {
                coverId = jsonCover.get("image_id").getAsString();
            }
            Game game = new Game();
            game.setId(id);
            game.setName(name);
            game.setSummary(summary);
            game.setCoverId(coverId);
            games.add(game);
        }

        return games;
    }

    @Override
    public Game getAllInformation(int gameId) {
        Game game = new Game();
        // id, checksum, name, igdb_rating, rating_count, summary, artwork_id, cover_id, platforms, genres

        PostRequest pr = PostRequest.builder()
                .fields("checksum, name, rating, rating_count, summary, artworks.image_id, cover.image_id,"
                        + "platforms.checksum, platforms.abbreviation, platforms.platform_logo, platforms.name,"
                        + "genres.checksum, genres.name")
                .where("id = " + gameId)
                .build();

        String postResult = this.post(URL, pr.asString());

        return parseAll(postResult);
    }

    @Override
    public Game parseAll(String jsonResponse) {
        Gson gson = new Gson();
        Game game = new Game();

        JsonArray jsonArray = JsonParser.parseString(jsonResponse).getAsJsonArray();
        JsonObject jsonGame = jsonArray.get(0).getAsJsonObject(); // Obtenemos el primer objeto del array

        int id = jsonGame.get("id").getAsInt();
        String checksum = jsonGame.get("checksum").getAsString();
        String name = jsonGame.get("name").getAsString();
        String summary = null;
        try {
            summary = jsonGame.get("summary").getAsString();
        } catch (NullPointerException e) {
            System.err.println("(" + id + ") Game has no summary");
        }

        JsonArray jsonArtworks = jsonGame.getAsJsonArray("artworks");
        ArrayList<String> artworkIds = new ArrayList<>();
        if (jsonArtworks != null) {
            for (JsonElement artworkElement : jsonArtworks) {
                JsonObject jsonArtwork = artworkElement.getAsJsonObject();
                String artworkId = jsonArtwork.get("image_id").getAsString();
                artworkIds.add(artworkId);
            }
        }

        JsonObject jsonCover = jsonGame.getAsJsonObject("cover");
        String coverId = null;
        if (jsonCover != null) {
            coverId = jsonCover.get("image_id").getAsString();
        }

        float rating = 0;
        try {
            rating = jsonGame.get("rating").getAsFloat();
        } catch (NullPointerException e) {
            System.err.println("(" + id + ") Game has no rating");
        }

        int ratingCount = 0;
        try {
            ratingCount = jsonGame.get("rating_count").getAsInt();
        } catch (NullPointerException e) {
            System.err.println("(" + id + ") Game has no rating count");
        }

        game.setId(id);
        game.setChecksum(checksum);
        game.setName(name);
        game.setSummary(summary);
        game.setArtworkId(artworkIds);
        game.setCoverId(coverId);
        game.setIgdbRating(rating);
        game.setRatingCount(ratingCount);

        // Parse platforms
        JsonArray jsonPlatforms = jsonGame.getAsJsonArray("platforms");
        if (jsonPlatforms != null) {
            ArrayList<Platform> platforms = new ArrayList<>();
            for (JsonElement platformElement : jsonPlatforms) {
                JsonObject jsonPlatform = platformElement.getAsJsonObject();
                Platform platform = gson.fromJson(jsonPlatform, Platform.class);
                platforms.add(platform);
            }
            game.setPlatforms(platforms);
        }

        // Parse genres
        JsonArray jsonGenres = jsonGame.getAsJsonArray("genres");
        if (jsonGenres != null) {
            ArrayList<Genre> genres = new ArrayList<>();
            for (JsonElement genreElement : jsonGenres) {
                JsonObject jsonGenre = genreElement.getAsJsonObject();
                Genre genre = gson.fromJson(jsonGenre, Genre.class);
                genres.add(genre);
            }
            game.setGenres(genres);
        }

        return game;
    }

    @Override
    public void saveGame(Game game) {
        Connection connection = null;
        PreparedStatement psCheck = null;
        PreparedStatement psInsertGame = null;
        PreparedStatement psGamePlatform = null;
        PreparedStatement psGameGenre = null;

        try {
            connection = database.getConnection();
            connection.setAutoCommit(false); // Desactivar auto-commit para realizar un commit manual

            // Verificar si el juego ya está en la base de datos
            String queryCheck = "SELECT id FROM game WHERE id = ?";
            psCheck = connection.prepareStatement(queryCheck);
            psCheck.setInt(1, game.getId());
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
                System.out.println("Game already exists in the database.");
                return;
            }

            // Guardar plataformas y géneros en batch
            PlatformDAO platformDAO = new PlatformDAOPostgreSQL(database);
            GenreDAO genreDAO = new GenreDAOPostgreSQL(database);

            // Consultar plataformas existentes
            Set<Integer> existingPlatformIds = platformDAO.getExistingPlatformIds(game.getPlatforms(), connection);
            // Insertar plataformas no existentes
            platformDAO.savePlatformsInBatch(game.getPlatforms(), existingPlatformIds, connection);

            // Consultar géneros existentes
            Set<Integer> existingGenreIds = genreDAO.getExistingGenreIds(game.getGenres(), connection);
            // Insertar géneros no existentes
            genreDAO.saveGenresInBatch(game.getGenres(), existingGenreIds, connection);

            // Guardar el juego
            String insertGameQuery = "INSERT INTO game (id, checksum, name, igdb_rating, rating_count, summary, cover_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            psInsertGame = connection.prepareStatement(insertGameQuery);
            psInsertGame.setInt(1, game.getId());
            psInsertGame.setString(2, game.getChecksum());
            psInsertGame.setString(3, game.getName());
            psInsertGame.setFloat(4, game.getIgdbRating());
            psInsertGame.setInt(5, game.getRatingCount());
            psInsertGame.setString(6, game.getSummary());
            psInsertGame.setString(7, game.getCoverId());
            psInsertGame.executeUpdate();

            // Relacionar juego con plataformas en batch
            String insertGamePlatformQuery = "INSERT INTO platform_game (game_id, platform_id) VALUES (?, ?)";
            psGamePlatform = connection.prepareStatement(insertGamePlatformQuery);
            for (Platform platform : game.getPlatforms()) {
                psGamePlatform.setInt(1, game.getId());
                psGamePlatform.setInt(2, platform.getId());
                psGamePlatform.addBatch();
            }
            psGamePlatform.executeBatch();

            // Relacionar juego con géneros en batch
            String insertGameGenreQuery = "INSERT INTO genre_game (game_id, genre_id) VALUES (?, ?)";
            psGameGenre = connection.prepareStatement(insertGameGenreQuery);
            for (Genre genre : game.getGenres()) {
                psGameGenre.setInt(1, game.getId());
                psGameGenre.setInt(2, genre.getId());
                psGameGenre.addBatch();
            }
            psGameGenre.executeBatch();

            connection.commit(); // Realizar commit manual

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Revertir transacciones en caso de error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (psCheck != null) {
                    psCheck.close();
                }
                if (psInsertGame != null) {
                    psInsertGame.close();
                }
                if (psGamePlatform != null) {
                    psGamePlatform.close();
                }
                if (psGameGenre != null) {
                    psGameGenre.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }

    @Override
    public boolean addToList(Game game, List list) {
        saveGame(game);

        String query = "INSERT INTO list_game VALUES (?,?)";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, list.getId());
            ps.setInt(2, game.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Game> getGamesInList(int listId) {
        ArrayList<Game> games = new ArrayList<>();
        String query = "SELECT id, cover_id, name FROM game g JOIN list_game lg ON g.id = lg.game_id WHERE lg.list_id = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, listId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setCoverId(rs.getString("cover_id"));
                game.setName(rs.getString("name"));

                games.add(game);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return games;
    }

    @Override
    public boolean addToFavourite(Game game, int favListId) {
        saveGame(game);

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        String query = "INSERT INTO list_game VALUES (?,?)";

        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, favListId);
            ps.setInt(2, game.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<Game> getSimilar(int gameId) {
        ArrayList<Game> similarGames = new ArrayList<>();

        PostRequest pr = PostRequest.builder()
                .fields("similar_games")
                .where("id = " + gameId)
                .build();

        String postResult = this.post(URL, pr.asString());

        similarGames = this.parseSimilar(postResult);

        return similarGames;
    }

    public ArrayList<Game> parseSimilar(String jsonResponse) {
        ArrayList<Integer> similarGameIds = new ArrayList<>();
        JsonArray jsonArray = JsonParser.parseString(jsonResponse).getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonGame = jsonArray.get(i).getAsJsonObject();
            JsonArray similarGamesArray = jsonGame.getAsJsonArray("similar_games");

            for (int j = 0; j < similarGamesArray.size(); j++) {
                int similarGameId = similarGamesArray.get(j).getAsInt();
                similarGameIds.add(similarGameId);
            }
        }

        ArrayList<Game> similarGames = new ArrayList<>();
        if (!similarGameIds.isEmpty()) {
            PostRequest pr = PostRequest.builder()
                    .fields("id,name,summary,cover.image_id")
                    .where("id = (" + similarGameIds.toString().replaceAll("[\\[\\] ]", "") + ")")
                    .build();

            String postResult = this.post(URL, pr.asString());
            similarGames = this.parse(postResult);
        }

        return similarGames;
    }

    @Override
    public boolean addRating(Game game, int rating, String comment) {
        saveGame(getAllInformation(game.getId()));
        if (isGameRated(game)) {
            return false;
        }
        String query = "INSERT INTO rating VALUES (?,?,?,?)";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, Session.getCurrentClient().getUsername());
            ps.setInt(2, game.getId());
            ps.setInt(3, rating);
            ps.setString(4, comment);

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isGameRated(Game game) {
        String query = "SELECT * FROM rating WHERE game_id = ? AND username = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, game.getId());
            ps.setString(2, Session.getCurrentClient().getUsername());
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<GameRate> getRatings(Game game) {
        ArrayList<GameRate> ratings = new ArrayList<>();
        String query = "SELECT username, rating, comment FROM rating WHERE game_id = ? ORDER BY created_at DESC";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, game.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GameRate rate = new GameRate(
                        rs.getString("username"),
                        rs.getInt("rating"),
                        rs.getString("comment")
                );
                ratings.add(rate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ratings;
    }
}
