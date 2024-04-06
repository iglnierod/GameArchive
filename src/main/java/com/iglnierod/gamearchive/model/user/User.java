/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.user;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rodri
 */
public class User {

    private String username;
    private String email;
    private String passwordHash;

    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.passwordHash = getPasswordHash(password);
    }

    private String getPasswordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = password;
    }
}
