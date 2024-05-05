/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.genre.dao;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.genre.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author iglnierod
 */
public class GenreDAOPostgreSQL implements GenreDAO {
    private Database database;

    public GenreDAOPostgreSQL(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Genre> getAll() {
        ArrayList<Genre> genres = new ArrayList<>();
        
        String query = "SELECT * FROM genre";
        try(Statement stmt = database.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Genre genre = new Genre(
                        rs.getInt("id"),
                        rs.getString("checksum"),
                        rs.getString("name")
                );
                genres.add(genre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return genres;
    }
}
