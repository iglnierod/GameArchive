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

    public ExportGame(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getName() {
        return name;
    }
}
