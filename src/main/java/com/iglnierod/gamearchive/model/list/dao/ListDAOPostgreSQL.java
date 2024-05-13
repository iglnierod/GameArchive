/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.list.dao;

import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.session.Session;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author iglnierod
 */
public class ListDAOPostgreSQL implements ListDAO {

    private final Database database;

    public ListDAOPostgreSQL(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<List> getAll() {
        return this.getAll(Session.getCurrentClient());
    }

    @Override
    public ArrayList<List> getAll(Client client) {
        ArrayList<List> lists = new ArrayList<>();
        String query = "SELECT * FROM list WHERE username = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, client.getUsername());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List l = new List(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                lists.add(l);
            }

            return lists;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean create(List list) {
        String query = "INSERT INTO list(name, username, description) VALUES (?,?,?)";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, list.getName());
            ps.setString(2, Session.getCurrentClient().getUsername());
            ps.setString(3, list.getDescription());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating list failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    list.setId(generatedKeys.getInt("id"));
                    return true;
                } else {
                    throw new SQLException("Creating list failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(List list) {
        String query = "UPDATE list SET name = ?, description = ? WHERE id = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, list.getName());
            ps.setString(2, list.getDescription());
            ps.setInt(3, list.getId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("NO SE HA PODIDO ACTUALIZAR LA INFORMACION DEL JUEGO");
        }
    }

    @Override
    public void removeGame(int listId, int gameId) {
        String query = "DELETE FROM list_game WHERE list_id = ? AND game_id = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, listId);
            ps.setInt(2, gameId);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("NO SE HA PODIDO ELIMINAR EL JUEGO DE LA LISTA");
        }
    }

    @Override
    public void delete(List list) {
        String query = "DELETE FROM list WHERE id = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, list.getId());
 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("NO SE HA PODIDO ELIMINAR LA LISTA");
        }
    }

}
