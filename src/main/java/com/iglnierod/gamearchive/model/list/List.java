/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.list;

import com.iglnierod.gamearchive.model.game.Game;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author iglnierod
 */
public class List implements Serializable {

    private int id;
    private String name;
    private String description;
    private ArrayList<Game> games;
    private boolean favourite;

    public List() {
        this(-1, null, null, new ArrayList<>());
    }

    public List(String name, String description) {
        this(-1, name, description, new ArrayList<>());
    }

    public List(int id, String name, String description) {
        this(id, name, description, new ArrayList<>());
    }

    public List(int id, String name, String description, ArrayList<Game> games) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.games = games;
        this.favourite = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
    
    @Override
    public String toString() {
        return String.format("List{id: %d, name: %s, description: %s}", id, name, description);
    }

}
