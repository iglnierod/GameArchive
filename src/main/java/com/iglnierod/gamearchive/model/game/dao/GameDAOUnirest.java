package com.iglnierod.gamearchive.model.game.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iglnierod.gamearchive.model.api.igdb.PostRequest;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.filter.GameFilter;
import com.iglnierod.gamearchive.model.platform.Platform;
import com.iglnierod.gamearchive.model.session.Session;
import java.util.ArrayList;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GameDAOUnirest implements GameDAO {

    private String clientId;
    private String accessToken;

    public GameDAOUnirest() {
        clientId = System.getenv("IGDB_CLIENT_ID");
        accessToken = System.getenv("IGDB_ACCESS_TOKEN");
    }

    @Override
    public ArrayList<Game> search(String inputText, GameFilter filter) {
        // TODO: Rating filter
        inputText = "\"" + inputText + "\"";
        ArrayList<Game> games = new ArrayList<>();
        String url = "https://api.igdb.com/v4/games";
        
        String where = getWhereStatement(filter);
        
        PostRequest pr = PostRequest.builder()
                .fields("name, cover.image_id, summary, rating")
                .search(inputText)
                .where(where)
                .limit(filter.getLimit())
                .build();
        
        System.out.println("pr.asString(): " + pr.asString());
        String postResult = this.post(url, pr.asString());
        System.out.println("postResult: " + postResult);

        games = this.parse(postResult);

        return games;
    }

    public String getWhereStatement(GameFilter filter) {
        StringBuilder where = new StringBuilder();
        String platformFilter = "platforms = ";
        
        if(!filter.isAllPlatforms()) {
            platformFilter += Platform.getPlatformFilterString(Session.getCurrentClient().getPlatformsList());
        } else {
            platformFilter = "";
        }
        where.append(platformFilter);
        
        if(!where.isEmpty()) {
            where.append(" & ");
        }
        
        where.append("rating >= ").append(filter.getMinRating());
        
        if(filter.getGenres() != null) {
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
}
