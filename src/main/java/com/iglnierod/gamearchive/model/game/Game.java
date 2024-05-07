/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.game;

import com.iglnierod.gamearchive.model.genre.Genre;
import com.iglnierod.gamearchive.model.platform.Platform;
import java.util.ArrayList;

/**
 *
 * @author iglnierod
 */
public class Game {

    private int id;
    private String checksum;
    private String name;
    private float igdbRating;
    private int ratingCount;
    private String summary;
    private ArrayList<String> artworkId;
    private String coverId;
    private ArrayList<Platform> platforms;
    private ArrayList<Genre> genres;

    public Game() {
        artworkId = new ArrayList<>();
        platforms = new ArrayList<>();
        genres = new ArrayList<>();
    }

    public Game(int id, String checksum, String name, float igdbRating, int ratingCount, String summary, ArrayList<String> artworkId, String coverId) {
        this.id = id;
        this.checksum = checksum;
        this.name = name;
        this.igdbRating = igdbRating;
        this.ratingCount = ratingCount;
        this.summary = summary;
        this.artworkId = artworkId;
        this.coverId = coverId;
        platforms = new ArrayList<>();
        genres = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getIgdbRating() {
        return igdbRating;
    }

    public void setIgdbRating(float igdbRating) {
        this.igdbRating = igdbRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList<String> getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(ArrayList<String> artworkId) {
        this.artworkId = artworkId;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<Platform> platforms) {
        this.platforms = platforms;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return String.format("Game={id=%d,name=%s,coverId=%s}", id, name, coverId);
    }
}
