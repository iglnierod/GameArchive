/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.home;

import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.platform.Platform;
import com.iglnierod.gamearchive.model.session.Session;
import com.iglnierod.gamearchive.view.home.HomeFrame;

/**
 *
 * @author rodri
 */
public class HomeController {

    private final HomeFrame view;
    private final Database database;
    private final Client currentClient;

    public HomeController(HomeFrame view, Database database) {
        this.view = view;
        this.database = database;
        this.currentClient = Session.getCurrentClient();
        testSessionClass();
        System.out.println("user: " + Session.getCurrentClient());
    }

    private void testSessionClass() {
        view.setUsernameText(currentClient.getUsername());
        view.setUserDescription(currentClient.getDescription());
        String text = null;
        for (Platform p : currentClient.getPlatformsList()) {
            text += p.getAbbreviation() + " | ";
        }
        view.setUserPlatforms(text);
    }
}
