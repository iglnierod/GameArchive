/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.genre.dao;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.genre.Genre;
import java.sql.PreparedStatement;
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
        try (Statement stmt = database.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
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

    @Override
    public void saveGenre(Genre genre) {
        String query = "INSERT INTO genre VALUES (?,?,?)";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, genre.getId());
            ps.setString(2, genre.getChecksum());
            ps.setString(3, genre.getName());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("GENRE ALREADY IN DB: " + ex.getMessage());
        }
    }

    @Override
    public void saveGenres(ArrayList<Genre> genres) {
        for (Genre g : genres) {
            saveGenre(g);
        }
    }

    @Override
    public void buildRelation(int gameId, ArrayList<Genre> genres) {
        String query = "INSERT INTO genre_game VALUES (?,?)";

        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            for (Genre genre : genres) {
                ps.setInt(1, gameId);
                ps.setInt(2, genre.getId());
                ps.addBatch(); // Agrega la consulta al lote de ejecución
            }
            ps.executeBatch(); // Ejecuta todas las consultas en el lote de una vez
        } catch (SQLException ex) {
            System.err.println("ERROR BUILDING RELATION GENRE->GAME: " + ex.getMessage());
        }
    }

}
