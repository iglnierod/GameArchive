/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.user.dao;

import com.iglnierod.gamearchive.model.user.User;

/**
 *
 * @author rodri
 */
public interface UserDAO {
    boolean add(User user);
    
    boolean login(String username, String password);
}
