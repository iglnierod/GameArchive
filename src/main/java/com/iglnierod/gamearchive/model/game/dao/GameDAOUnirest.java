package com.iglnierod.gamearchive.model.game.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iglnierod.gamearchive.model.api.igdb.PostRequest;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.filter.GameFilter;
import com.iglnierod.gamearchive.model.genre.Genre;
import com.iglnierod.gamearchive.model.platform.Platform;
import com.iglnierod.gamearchive.model.session.Session;
import java.util.ArrayList;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GameDAOUnirest implements GameDAO {

    private String clientId;
    private String accessToken;
    private static String URL = "https://api.igdb.com/v4/games";

    public GameDAOUnirest() {
        clientId = System.getenv("IGDB_CLIENT_ID");
        accessToken = System.getenv("IGDB_ACCESS_TOKEN");
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

    // TODO
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

}
