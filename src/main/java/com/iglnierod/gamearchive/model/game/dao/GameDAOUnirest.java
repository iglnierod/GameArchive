package com.iglnierod.gamearchive.model.game.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iglnierod.gamearchive.model.api.igdb.PostRequest;
import com.iglnierod.gamearchive.model.game.Game;
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
    public ArrayList<Game> search(String inputText) {
        inputText = "\"" + inputText + "\"";
        // TODO
        ArrayList<Game> games = new ArrayList<>();
        String url = "https://api.igdb.com/v4/games";
        PostRequest pr = PostRequest.builder()
                .fields("name, cover.image_id, summary") // String fields = "fields name, cover.image_id"
                .search(inputText) // String search = "search "Hollow Knight""
                .limit("10") // String limit = "limit 10;"
                .build();
        //System.out.println("PostRequest: " + pr.asString());
        String postResult = this.post(url, pr.asString());
        System.out.println("postResult: " + postResult);

        games = this.parse(postResult);

        return games;
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
            String summary = jsonGame.get("summary").getAsString();

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
