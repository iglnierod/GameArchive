/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.platform.dao;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.platform.Platform;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;

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
        // TODO
        String query = "SELECT * FROM platform";
        ArrayList<Platform> platformsList = new ArrayList<>();
        try (Statement stmt = database.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Platform platform = new Platform(
                        rs.getInt("id"),
                        rs.getString("checksum"),
                        rs.getString("abbreviation"),
                        rs.getString("name"),
                        rs.getString("logo_id")
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
