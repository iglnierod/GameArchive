/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.list;

import com.iglnierod.gamearchive.model.game.ExportGame;
import java.util.List;

/**
 *
 * @author iglnierod
 */
public class ExportList {

    private int id;
    private String name;
    private String description;
    private List<ExportGame> games;

    public ExportList(int id, String name, String description, List<ExportGame> games) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.games = games;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<ExportGame> getGames() {
        return games;
    }
}
