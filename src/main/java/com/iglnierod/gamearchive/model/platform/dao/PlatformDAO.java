/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.iglnierod.gamearchive.model.platform.dao;

import com.iglnierod.gamearchive.model.platform.Platform;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author rodri
 */
public interface PlatformDAO {

    ArrayList<Platform> getAll();

    Platform get(int id);

    Platform getByAbbreviation(String abbreviation);

    Set<Platform> getPlatformsByAbbreviation(Set<String> platforms);

    ArrayList<Integer> getPlatformsById(String platformsArray);

    Set<Platform> getPlatformsByUser(String username);

    void savePlatforms(ArrayList<Platform> platforms);

    void savePlatform(Platform platform);

    public void buildRelation(int gameId, ArrayList<Platform> platforms);

    public boolean platformExists(Platform platform);

    // Métodos adicionales para optimización
    public Set<Integer> getExistingPlatformIds(ArrayList<Platform> platforms, Connection connection) throws SQLException;

    public void savePlatformsInBatch(ArrayList<Platform> platforms, Set<Integer> existingPlatformIds, Connection connection) throws SQLException;
}
