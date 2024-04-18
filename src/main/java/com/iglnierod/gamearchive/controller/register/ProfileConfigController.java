/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.register;

import com.iglnierod.gamearchive.controller.home.HomeController;
import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.client.dao.ClientDAO;
import com.iglnierod.gamearchive.model.client.dao.ClientDAOPostgreSQL;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.platform.Platform;
import com.iglnierod.gamearchive.model.platform.dao.PlatformDAO;
import com.iglnierod.gamearchive.model.platform.dao.PlatformDAOPostgreSQL;
import com.iglnierod.gamearchive.model.session.Session;
import com.iglnierod.gamearchive.view.home.HomeFrame;
import com.iglnierod.gamearchive.view.register.ProfileConfigFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JToggleButton;

/**
 *
 * @author rodri
 */
public class ProfileConfigController {

    private final ProfileConfigFrame view;
    private final Database database;
    private final PlatformDAO platformDao;
    private final ClientDAO clientDao;
    // private final ImageChooserDialog imageChooserDialog;

    public ProfileConfigController(ProfileConfigFrame view, Database database) {
        this.view = view;
        this.database = database;
        this.platformDao = new PlatformDAOPostgreSQL(database);
        this.clientDao = new ClientDAOPostgreSQL(database);
        loadPlatforms();
        addListeners();
        // this.imageChooserDialog = new ImageChooserDialog(view, true);
    }

    private void addListeners() {
        /*MAIN VIEW*/
        this.view.addMouseListenerImagePanel(setImagePanelMouseListener());
        this.view.addContinueButtonActionListener(setContinueButtonActionListener());

        /*ImageChooserDialog*/
    }

    private MouseAdapter setImagePanelMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO
                /*try {
                    URL url = new URL("https://i.imgur.com/NdgyJag.png");
                    BufferedImage image = ImageIO.read(url);
                    JLabel label = new JLabel(new ImageIcon(image));
                    imageChooserDialog.addImageToPanel(label);
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
                imageChooserDialog.setVisible(true);*/
            }
        };
    }

    private ActionListener setContinueButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
                Set<Platform> selectedPlatforms = platformDao.getPlatformsByAbbreviation(view.getSelectedPlatforms());
                Client client = Session.getCurrentClient();
                clientDao.updateUserDescription(view.getDescription());
                clientDao.updateUserPlatforms(selectedPlatforms);
                client = clientDao.getClient(client.getUsername());
                Session.setCurrentClient(client);

                HomeFrame homeFrame = new HomeFrame();
                HomeController homeController = new HomeController(homeFrame, database);
                view.dispose();
                homeFrame.setVisible(true);
            }
        };
    }

    private void loadPlatforms() {
        ArrayList<Platform> platforms = platformDao.getAll();
        for (Platform p : platforms) {
            JToggleButton platformToggleButton = view.getPlatformToggleButton(p.getAbbreviation());
            platformToggleButton.setToolTipText(p.getName());
            this.view.addPlatformToPanel(platformToggleButton);
        }
    }
}
