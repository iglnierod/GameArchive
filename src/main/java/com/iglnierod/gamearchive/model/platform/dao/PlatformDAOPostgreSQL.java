/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.platform.dao;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.platform.Platform;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author rodri
 */
public class PlatformDAOPostgreSQL implements PlatformDAO {

    private Database database;

    public PlatformDAOPostgreSQL(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Platform> getAll() {
        String query = "SELECT * FROM platform";
        ArrayList<Platform> platformsList = new ArrayList<>();
        try (Statement stmt = database.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Platform platform = new Platform(
                        rs.getInt("id"),
                        rs.getString("checksum"),
                        rs.getString("abbreviation"),
                        rs.getString("logo_id"),
                        rs.getString("name")
                );

                platformsList.add(platform);
            }
            return platformsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Platform get(int id) {
        // TODO
        String query = "SELECT * FROM platform_duplicate WHERE id = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            //int id, String checksum, String abbreviation, String logoID, String name
            Platform platform = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                platform = new Platform(
                        rs.getInt("id"),
                        rs.getString("checksum"),
                        rs.getString("abbreviation"),
                        rs.getString("logo_id"),
                        rs.getString("name")
                );
            }
            return platform;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Platform getByAbbreviation(String abbreviation) {
        // TODO
        String query = "SELECT * FROM platform_duplicate WHERE abbreviation = ?";
        Platform platform = null;
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, abbreviation);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                platform = new Platform(
                        rs.getInt("id"),
                        rs.getString("checksum"),
                        rs.getString("abbreviation"),
                        rs.getString("logo_id"),
                        rs.getString("name")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return platform;
    }

    @Override
    public Set<Platform> getPlatformsByAbbreviation(Set<String> platforms) {
        // TODO
        Set<Platform> platformsSet = new LinkedHashSet<>();
        for (String platformAbbreviation : platforms) {
            Platform platform = this.getByAbbreviation(platformAbbreviation);
            if (platform != null) {
                platformsSet.add(platform);
            }
        }
        return platformsSet;
    }

    @Override
    public ArrayList<Integer> getPlatformsById(String platformsArray) {
        ArrayList<Integer> result = new ArrayList<>();

        // Eliminar los caracteres "{" y "}" y dividir la cadena por comas
        String[] parts = platformsArray.replaceAll("[{}]", "").split(",");

        // Convertir los elementos a enteros y agregarlos al ArrayList
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part.trim());
                result.add(num);
            } catch (NumberFormatException e) {
                // Manejar la excepción si no se puede convertir a entero
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public Set<Platform> getPlatformsFromArrayList(ArrayList<Integer> arrayList) {
        Set<Platform> platformsSet = new LinkedHashSet<>();
        for (Integer i : arrayList) {
            platformsSet.add(this.get(i));
        }
        return platformsSet;
    }
}
