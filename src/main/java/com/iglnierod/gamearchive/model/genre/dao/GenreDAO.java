/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.iglnierod.gamearchive.model.genre.dao;

import com.iglnierod.gamearchive.model.genre.Genre;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author iglnierod
 */
public interface GenreDAO {

    public ArrayList<Genre> getAll();

    public void saveGenre(Genre genre);

    public void saveGenres(ArrayList<Genre> genres);

    public void buildRelation(int gameId, ArrayList<Genre> genres);

    public boolean genreExists(Genre genre);

    // Métodos adicionales para optimización
    public Set<Integer> getExistingGenreIds(ArrayList<Genre> genres, Connection connection) throws SQLException;

    public void saveGenresInBatch(ArrayList<Genre> genres, Set<Integer> existingGenreIds, Connection connection) throws SQLException;
}
