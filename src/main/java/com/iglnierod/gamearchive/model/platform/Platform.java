/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.platform;

/**
 *
 * @author rodri
 */
public class Platform {

    private int id;
    private String checksum;
    private String abbreviation;
    private String name;
    private String logoID;

    public Platform(int id, String checksum, String abbreviation, String logoID, String name) {
        this.id = id;
        this.checksum = checksum;
        this.abbreviation = abbreviation;
        this.name = name;
        this.logoID = logoID;
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoID() {
        return logoID;
    }

    public void setLogoID(String logoID) {
        this.logoID = logoID;
    }

    @Override
    public String toString() {
        return "Platform{"
                + "id=" + id
                + ", abbreviation='" + abbreviation + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
