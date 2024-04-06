/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.user.dao;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.user.User;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rodri
 */
public class UserDAOPostgreSQL implements UserDAO {

    private Database database;

    public UserDAOPostgreSQL(Database database) {
        this.database = database;
    }

    @Override
    public boolean add(User user) {
        String query = "INSERT INTO users (username, email, password) VALUES (?,?,?)";
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
        String query = "SELECT password FROM users WHERE username = ?";
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
}
