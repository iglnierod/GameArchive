/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.register;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.platform.Platform;
import com.iglnierod.gamearchive.model.platform.dao.PlatformDAO;
import com.iglnierod.gamearchive.model.platform.dao.PlatformDAOPostgreSQL;
import com.iglnierod.gamearchive.view.register.ProfileConfigDialog;
import java.util.ArrayList;
import javax.swing.JToggleButton;

/**
 *
 * @author rodri
 */
public class ProfileConfigController {

    private final ProfileConfigDialog view;
    private final Database database;
    private final PlatformDAO platformDao;

    public ProfileConfigController(ProfileConfigDialog view, Database database) {
        this.view = view;
        this.database = database;
        this.platformDao = new PlatformDAOPostgreSQL(database);
        loadPlatforms();
    }

    private void loadPlatforms() {
        ArrayList<Platform> platforms = platformDao.getAll();
        for (Platform p : platforms) {
            JToggleButton platformToggleButton = view.getPlatformToggleButton(p.getAbbreviation());
            this.view.addPlatformToPanel(platformToggleButton);
        }
    }
}