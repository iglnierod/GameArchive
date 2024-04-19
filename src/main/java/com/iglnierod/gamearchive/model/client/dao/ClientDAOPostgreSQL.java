/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.client.dao;

import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.platform.Platform;
import com.iglnierod.gamearchive.model.platform.dao.PlatformDAO;
import com.iglnierod.gamearchive.model.platform.dao.PlatformDAOPostgreSQL;
import com.iglnierod.gamearchive.model.session.Session;
import java.sql.*;
import java.util.Set;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rodri
 */
public class ClientDAOPostgreSQL implements ClientDAO {

    private Database database;

    public ClientDAOPostgreSQL(Database database) {
        this.database = database;
    }

    @Override
    public boolean add(Client user) {
        String query = "INSERT INTO client (username, email, password) VALUES (?,?,?)";
        //String query = "INSERT INTO client_duplicate (username, email, password) VALUES (?,?,?)";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            //ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean login(String username, String password) {
        String query = "SELECT password FROM client WHERE username = ?";
        //String query = "SELECT password FROM client_duplicate WHERE username = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            String hashedPassword = null;
            while (rs.next()) {
                hashedPassword = rs.getString("password");
            }
            return BCrypt.checkpw(password, hashedPassword);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void addPlatforms(Client client) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Client getClient(String username) {
        String query = "SELECT * FROM client WHERE username = ?";
        //String query = "SELECT * FROM client_duplicate WHERE username = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, username);

            Client client = null;
            PlatformDAO platformDao = new PlatformDAOPostgreSQL(database);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                client = new Client();
                client.setUsername(rs.getString("username"));
                client.setEmail(rs.getString("email"));
                client.setPassword(rs.getString("password"));
                client.setDescription(rs.getString("description"));
                client.setPlatformsList(platformDao.getPlatformsByUser(username));
            }

            return client;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateUserDescription(String description) {
        String currentUsername = Session.getCurrentClient().getUsername();
        String query = "UPDATE client SET description = ? WHERE username = ?";
        //String query = "UPDATE client_duplicate SET description = ? WHERE username = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, description);
            ps.setString(2, currentUsername);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateUserPlatforms(Set<Platform> selectedPlatforms) {
        String query = "INSERT INTO client_platform (platform_id, username) VALUES (?, ?)";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            for (Platform p : selectedPlatforms) {
                ps.setInt(1, p.getId());
                ps.setString(2, Session.getCurrentClient().getUsername());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
