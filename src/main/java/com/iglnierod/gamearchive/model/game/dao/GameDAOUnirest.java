package com.iglnierod.gamearchive.model.game.dao;

import com.google.gson.Gson;
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
        // TODO
        ArrayList<Game> games = new ArrayList<>();
        String url = "https://api.igdb.com/v4/games";
        PostRequest pr = PostRequest.builder()
                .fields("name, cover.image_id")
                .search("\""+inputText+"\"")
                .limit("1")
                .build();
        System.out.println("PostRequest: " + pr.asString());
        String postResult = this.post(url, pr.asString());
        System.out.println("postResult: " + postResult);
        
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

        String responseString = jsonResponse.getBody();

        return responseString;
    }

    @Override
    public Game parse(String jsonResponse) {
        // TODO
        Gson gson = new Gson();
        return null;
    }
}
