/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.list.dao;

import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.ExportGame;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.GameStatus;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.list.ExportList;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.session.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        String query = "SELECT * FROM list WHERE username = ? AND favourite is FALSE";
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

    @Override
    public List getFavouriteList() {
        return getFavouriteList(Session.getCurrentClient());
    }

    @Override
    public void createFavourite(List list) {
        String query = "INSERT INTO list(name, username, description, favourite) VALUES (?,?,?,?)";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, list.getName());
            ps.setString(2, Session.getCurrentClient().getUsername());
            ps.setString(3, list.getDescription());
            ps.setBoolean(4, true);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating list failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    list.setId(generatedKeys.getInt("id"));
                } else {
                    throw new SQLException("Creating list failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Set<Integer> getAllFavouriteGameIds() {
        Set<Integer> favouriteGameIds = new HashSet<>();
        int favListId = getFavouriteList().getId();
        String query = "SELECT game_id FROM list_game WHERE list_id = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, favListId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                favouriteGameIds.add(rs.getInt("game_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return favouriteGameIds;
    }

    @Override
    public List getFavouriteList(Client client) {
        List fav = new List();
        String query = "SELECT * FROM list WHERE favourite IS TRUE AND username = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, client.getUsername());

            GameDAO gameDao = new GameDAOUnirest(database);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fav.setId(rs.getInt("id"));
                fav.setName(rs.getString("name"));
                fav.setDescription(rs.getString("description"));
                fav.setGames(gameDao.getGamesInList(fav.getId()));
                fav.setFavourite(true);
            }
            return fav;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void importGames(int listId, ExportList exList) {
        String query = "INSERT INTO list_game (list_id, game_id) VALUES (?, ?)";

        for (ExportGame g : exList.getGames()) {
            try (Connection conn = database.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
                conn.setAutoCommit(false); // Disable auto-commit mode

                ps.setInt(1, listId);
                ps.setInt(2, g.getId());

                try {
                    ps.executeUpdate(); // Execute the insert statement immediately
                    conn.commit(); // Commit successful insert
                } catch (SQLException e) {
                    conn.rollback(); // Rollback on failure
                    if (e.getSQLState().equals("23505")) { // SQL state for unique constraint violation
                        System.err.println("Duplicate key for game with ID: " + g.getId() + ", skipping...");
                    } else {
                        System.err.println("Failed to insert game with ID: " + g.getId() + ": " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List getGameByStatus(GameStatus status) {
        return this.getGameByStatus(status, Session.getCurrentClient());
    }

    @Override
    public List getGameByStatus(GameStatus status, Client client) {
        List list = new List();
        String query = "SELECT * FROM client_game WHERE username = ? AND status = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, client.getUsername());
            ps.setInt(2, status.getValue());

            GameDAO gameDao = new GameDAOUnirest(database);
            ResultSet rs = ps.executeQuery();
            ArrayList<Game> games = new ArrayList<>();
            while (rs.next()) {
                Game g = gameDao.getAllInformation(rs.getInt("game_id"));
                games.add(g);
                list.setGames(games);
            }
            list.setName(status.toString().replace("_", " "));
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
