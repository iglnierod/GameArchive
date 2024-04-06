/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.register;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.view.register.ProfileConfigDialog;

/**
 *
 * @author rodri
 */
public class ProfileConfigController {
    private final ProfileConfigDialog view;
    private final Database database;

    public ProfileConfigController(ProfileConfigDialog view, Database database) {
        this.view = view;
        this.database = database;
    }
    
    
}
