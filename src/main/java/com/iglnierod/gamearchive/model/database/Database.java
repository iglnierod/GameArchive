/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rodri
 */
public class Database {
    private final String HOST;
    private final String NAME;
    private final String PORT;
    private final String USER;
    private final String PASSWORD;
    private String url;
    
    public Database() {
        this.HOST = System.getenv("DB_HOST");
        this.NAME = System.getenv("DB_NAME");
        this.PORT = System.getenv("DB_PORT");
        this.USER = System.getenv("DB_USER");
        this.PASSWORD = System.getenv("DB_PASSWORD");
        this.url = String.format("jdbc:postgresql://%s:%s/%s", HOST, PORT, NAME);
    }
    
    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
