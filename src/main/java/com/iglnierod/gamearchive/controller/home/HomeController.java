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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        //testSessionClass();
        addListeners();
        System.out.println("user: " + Session.getCurrentClient());
    }

    private void addListeners() {

    }

    private ActionListener setLogOutMenuItemListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        };
    }

    private ActionListener setQuitMenuItemListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
                view.dispose();
            }
        };
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
