/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.community;

import java.time.LocalDateTime;

/**
 *
 * @author iglnierod
 */
public class Activity {

    private String username;
    private String gameName;
    private String coverId;
    private String listName;
    private LocalDateTime date;

    public Activity() {
    }

    public Activity(String username, String gameName, String coverId, String listName, LocalDateTime date) {
        this.username = username;
        this.gameName = gameName;
        this.coverId = coverId;
        this.listName = listName;
        this.date = date;
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
