/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.client.dao;

import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.platform.Platform;
import java.util.Set;

/**
 *
 * @author rodri
 */
public interface ClientDAO {
    boolean add(Client client);
    
    boolean login(String username, String password);
    
    void addPlatforms(Client client);

    Client getClient(String usernameText);

    public void updateUserDescription(String description);

    public void updateUserPlatforms(Set<Platform> selectedPlatforms);
}
