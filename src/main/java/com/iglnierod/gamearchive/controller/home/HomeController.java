/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.home;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.view.home.HomeFrame;

/**
 *
 * @author rodri
 */
public class HomeController {
    private final HomeFrame view;
    private final Database database;

    public HomeController(HomeFrame view, Database database) {
        this.view = view;
        this.database = database;
    }
    
    
}
