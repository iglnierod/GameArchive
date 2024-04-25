/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.home;

import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.session.Session;
import com.iglnierod.gamearchive.view.home.HomeFrame;
import com.iglnierod.gamearchive.view.home.search.SearchPanel;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 *
 * @author rodri
 */
public class HomeController {
    
    private final HomeFrame view;
    private final Database database;
    private final Client currentClient;
    private final GameDAO gameDao;
    private final SearchPanel searchPanel;
    
    public HomeController(HomeFrame view, Database database) {
        this.view = view;
        this.database = database;
        this.gameDao = new GameDAOUnirest();
        this.currentClient = Session.getCurrentClient();
        this.searchPanel = new SearchPanel();
        addListeners();
        initiatePanels();
    }
    
    private void addListeners() {
        this.view.setUsernameLabelText(currentClient.getUsername());
        this.view.addLogOutMenuItemActionListener(addLogOutMenuItemListener());
        this.view.addQuitMenuItemActionListener(addQuitMenuItemListener());
        this.view.addSearchLabelMouseListener(addSearchLabelListener());
    }
    
    private void initiatePanels() {
        addSearchPanelListeners();
    }
    
    private void addSearchPanelListeners() {
        searchPanel.addSearchButtonActionListener(this.addSearchButtonListener());
    }
    
    private ActionListener addLogOutMenuItemListener() {
        return (ActionEvent e) -> {
            view.dispose();
            
            Session.setCurrentClient(null);
            RegisterFrame registerFrame = new RegisterFrame();
            LoginFrame loginFrame = new LoginFrame();
            MainController.savedSession.delete();
            MainController mainController = new MainController(registerFrame, loginFrame, database);
        };
    }
    
    private ActionListener addQuitMenuItemListener() {
        return (ActionEvent e) -> {
            view.dispose();
            System.exit(0);
        };
    }
    
    private MouseListener addSearchLabelListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("SearchLabel: CLICK");
                view.setCenterContent(searchPanel);
            }
        };
    }
    
    private ActionListener addSearchButtonListener() {
        return (ActionEvent e) -> {
            ArrayList<Game> gamesResult = gameDao.search(searchPanel.getSearchTextFieldText());
            System.out.println("gamesResult: " + gamesResult);
            /*for(Game g : gamesResult) {
                //System.out.println(Reference.getImage(ImageType._1080P, g.getCoverId()));
                PreviewGamePanel previewGamePanel = new PreviewGamePanel();
                previewGamePanel.setNameLabelText(g.getName());
                view.addComponentPopularGamesPanel(previewGamePanel);
            }*/
        };
    }
}
