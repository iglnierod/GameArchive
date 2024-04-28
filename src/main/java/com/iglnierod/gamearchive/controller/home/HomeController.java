/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.home;

import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.model.api.igdb.ImageType;
import com.iglnierod.gamearchive.model.api.igdb.Reference;
import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.session.Session;
import com.iglnierod.gamearchive.view.game.GamePreviewPanel;
import com.iglnierod.gamearchive.view.home.HomeFrame;
import com.iglnierod.gamearchive.view.home.search.NoGamesFoundPanel;
import com.iglnierod.gamearchive.view.home.search.SearchPanel;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
    private final GameDAO gameDao;
    private final SearchPanel searchPanel;

    public HomeController(HomeFrame view, Database database) {
        this.view = view;
        this.database = database;
        this.gameDao = new GameDAOUnirest();
        this.currentClient = Session.getCurrentClient();
        //testSessionClass();
        addListeners();
        System.out.println("user: " + Session.getCurrentClient());
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

    // SEARCH FUNCTIONS
    private ActionListener addSearchButtonListener() {
        return (ActionEvent e) -> {
            String input = searchPanel.getSearchTextFieldText();
            ArrayList<Game> gamesResult = gameDao.search(input);
            System.out.println("gamesResult.size(): " + gamesResult.size());

            System.out.println("gamesResult: " + gamesResult);
            displayResults(gamesResult);
        };
    }

    private void displayResults(ArrayList<Game> gamesResult) {
        searchPanel.emptyResults();
        if (gamesResult.isEmpty()) {
            NoGamesFoundPanel noGamesFoundPanel = new NoGamesFoundPanel();
            noGamesFoundPanel.setInput(searchPanel.getSearchTextFieldText());
            searchPanel.addToResults(noGamesFoundPanel);
        }
        for (Game g : gamesResult) {
            System.out.println("game image: " + Reference.getImage(ImageType._1080P, g.getCoverId()));
            GamePreviewPanel gamePanel = new GamePreviewPanel(g.getId());

            gamePanel.addFavouriteButtonActionListener(this.addFavouriteButtonListener());
            gamePanel.addAddToButtonActionListener(this.addAddToButtonListener());
            gamePanel.addRateButtonActionListener(this.addRateButtonListener());

            gamePanel.setNameLabelText(g.getName());
            if (g.getSummary() != null) {
                gamePanel.setSummaryTextAreaText(g.getSummary());
            }
            if (g.getCoverId() != null) {
                try {
                    URL url = new URL(Reference.getImage(ImageType.COVER_SMALL, g.getCoverId()));
                    BufferedImage image = ImageIO.read(url);
                    gamePanel.setCoverLabelIcon(new ImageIcon(image));
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            searchPanel.addToResults(gamePanel);

        }
    }

    private ActionListener addFavouriteButtonListener() {
        // TODO
        return (ActionEvent e) -> {
            System.out.println("FAVOURITE BUTTON");
        };
    }

    private ActionListener addAddToButtonListener() {
        // TODO
        return (ActionEvent e) -> {
            System.out.println("ADD TO LIST BUTTON");
        };
    }

    private ActionListener addRateButtonListener() {
        // TODO
        return (ActionEvent e) -> {
            System.out.println("RATE BUTTON");
        };
    }

}
