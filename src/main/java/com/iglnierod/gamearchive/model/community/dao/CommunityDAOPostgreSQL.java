/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.community.dao;

import com.iglnierod.gamearchive.model.community.Activity;
import com.iglnierod.gamearchive.model.database.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author iglnierod
 */
public class CommunityDAOPostgreSQL implements CommunityDAO {

    private final Database database;

    public CommunityDAOPostgreSQL(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Activity> getLatest() {
        ArrayList<Activity> activityList = new ArrayList<>();
        String query = "SELECT l.username, g.name as game_name, g.cover_id, l.name as list_name, lg.created_at as date "
                + "FROM list_game lg, game g, list l "
                + "WHERE lg.game_id = g.id AND lg.list_id = l.id "
                + "ORDER BY lg.created_at DESC "
                + "LIMIT 50";
        try (Statement stmt = database.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Activity activity = new Activity(
                        rs.getString("username"),
                        rs.getString("game_name"),
                        rs.getString("cover_id"),
                        rs.getString("list_name"),
                        rs.getObject("date", LocalDateTime.class)
                );
                activityList.add(activity);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return activityList;
    }

    @Override
    public ArrayList<Activity> getLatest(String username) {
        ArrayList<Activity> activityList = new ArrayList<>();
        String query = "SELECT l.username, g.name as game_name, g.cover_id, l.name as list_name, lg.created_at as date "
                + "FROM list_game lg, game g, list l "
                + "WHERE lg.game_id = g.id AND lg.list_id = l.id AND l.username = ? "
                + "ORDER BY lg.created_at DESC "
                + "LIMIT 50";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Activity activity = new Activity(
                        rs.getString("username"),
                        rs.getString("game_name"),
                        rs.getString("cover_id"),
                        rs.getString("list_name"),
                        rs.getObject("date", LocalDateTime.class)
                );
                activityList.add(activity);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return activityList;
    }

}
