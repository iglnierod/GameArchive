/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.game.rate;

/**
 *
 * @author iglnierod
 */
public class GameRate {

    private String username;
    private int rating;
    private String comment;
    private String gameName;

    public GameRate() {
        this("username", 0, "no comment.", "");
    }

    public GameRate(String username, int rating, String comment) {
        this(username, rating, comment, "");
    }

    public GameRate(String username, int rating, String comment, String gameName) {
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.gameName = gameName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

}
