/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.platform;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author rodri
 */
public class Platform implements Serializable {

    private int id;
    private String checksum;
    private String abbreviation;
    private String name;
    private String logoID;

    public Platform() {
    }

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

    public static String getPlatformFilterString(Set<Platform> platformsList) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        Iterator<Platform> iterator = platformsList.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getId());
            if (iterator.hasNext()) {
                sb.append(",");
            }
        }

        sb.append(")");
        return sb.toString();
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
