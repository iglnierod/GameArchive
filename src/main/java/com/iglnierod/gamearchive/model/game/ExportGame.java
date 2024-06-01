/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.game;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author iglnierod
 */
public class ExportGame {

    private int id;
    private String name;
    private String coverId;

    public ExportGame(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ExportGame(int id, String name, String coverId) {
        this.id = id;
        this.name = name;
        this.coverId = coverId;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }
}
