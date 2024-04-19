/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.client;

import com.iglnierod.gamearchive.model.platform.Platform;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rodri
 */
public class Client implements Serializable {

    private String username;
    private String email;
    private String passwordHash;
    private String description;
    private Set<Platform> platformsList;

    public Client() {
    }

    public Client(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.passwordHash = getPasswordHash(password);
        this.platformsList = new LinkedHashSet<>();
    }

    public Client(String username, String email, String password, String description, Set<Platform> platformsList) {
        this.username = username;
        this.email = email;
        this.passwordHash = getPasswordHash(password);
        this.description = description;
        this.platformsList = platformsList;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Platform> getPlatformsList() {
        return platformsList;
    }

    public void setPlatformsList(Set<Platform> platformsList) {
        this.platformsList = platformsList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Client{"
                + "username='" + username + '\''
                + ", email='" + email + '\''
                + ", passwordHash='" + passwordHash + '\''
                + ", description='" + description + '\''
                + ", platformsList=" + platformsList
                + '}';
    }
}
