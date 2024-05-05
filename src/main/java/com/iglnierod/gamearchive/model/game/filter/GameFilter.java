/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.game.filter;

/**
 *
 * @author iglnierod
 */
public class GameFilter {

    private String limit;
    private String minRating;
    private boolean allPlatforms;
    private String genres;

    public GameFilter(String limit, String minRating, boolean allPlatforms, String genres) {
        this.limit = limit;
        this.minRating = minRating;
        this.allPlatforms = allPlatforms;
        this.genres = genres;
    }

    public GameFilter() {
        this("5", "0.0", true, "");
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getMinRating() {
        return minRating;
    }

    public void setMinRating(String minRating) {
        this.minRating = minRating;
    }

    public boolean isAllPlatforms() {
        return allPlatforms;
    }

    public void setAllPlatforms(boolean allPlatforms) {
        this.allPlatforms = allPlatforms;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return String.format("GameFilter{limit:%s;minRating:%s;allPlatforms:%s}", limit, minRating, allPlatforms);
    }
}
