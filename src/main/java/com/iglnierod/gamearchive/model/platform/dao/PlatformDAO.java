/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.iglnierod.gamearchive.model.platform.dao;

import com.iglnierod.gamearchive.model.platform.Platform;
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
}
