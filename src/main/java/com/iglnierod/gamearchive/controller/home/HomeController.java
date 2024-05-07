/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.home;

import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.controller.game.GameController;
import com.iglnierod.gamearchive.model.api.igdb.ImageType;
import com.iglnierod.gamearchive.model.api.igdb.Reference;
import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.game.filter.GameFilter;
import com.iglnierod.gamearchive.model.genre.Genre;
import com.iglnierod.gamearchive.model.genre.dao.GenreDAO;
import com.iglnierod.gamearchive.model.genre.dao.GenreDAOPostgreSQL;
import com.iglnierod.gamearchive.model.session.Session;
import com.iglnierod.gamearchive.view.game.GameDialog;
import com.iglnierod.gamearchive.view.game.GamePreviewPanel;
import com.iglnierod.gamearchive.view.home.HomeFrame;
import com.iglnierod.gamearchive.view.home.search.FiltersPanel;
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
    private final GenreDAO genreDao;
    private ArrayList<Game> gamesResult;
    
    public HomeController(HomeFrame view, Database database) {
        this.view = view;
        this.database = database;
        this.gameDao = new GameDAOUnirest();
        this.genreDao = new GenreDAOPostgreSQL(database);
        this.currentClient = Session.getCurrentClient();
        this.searchPanel = new SearchPanel();
        this.gamesResult = new ArrayList<>();
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
        addGenresToFiltersPanel();
    }

    private void addSearchPanelListeners() {
        searchPanel.addSearchButtonActionListener(this.addSearchButtonListener());
        searchPanel.addFilterButtonActionListener(this.addFilterButtonListener());
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
            FiltersPanel filtersPanel = searchPanel.getFiltersPanel();
            GameFilter gameFilter = new GameFilter(
                    filtersPanel.getLimitSpinner(),
                    filtersPanel.getMinRatingSpinner(),
                    filtersPanel.isAllPlatformsSelected(),
                    filtersPanel.getSelectedGenres()
            );
            System.out.println("gameFilter: " + gameFilter);
            gamesResult = gameDao.search(input, gameFilter);
            System.out.println("gamesResult: " + gamesResult);
            displayResults(gamesResult);
        };
    }

    private ActionListener addFilterButtonListener() {
        // TODO: Display filterPanel
        return (ActionEvent e) -> {
            System.out.println("FILTER BUTTON");
            searchPanel.toggleFiltersPanelVisible();
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
            gamePanel.addPanelMouseListener(this.addGamePreviewPanelMouseListener(g));
            
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

    // Open GameDialog when click game preview on search panel
    private MouseListener addGamePreviewPanelMouseListener(Game game) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameDialog gameDialog = new GameDialog(view, true);
                GameController gameController = new GameController(gameDialog, database, game);
                gameDialog.setVisible(true);
            }
        };
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

    private void addGenresToFiltersPanel() {
        FiltersPanel filtersPanel = searchPanel.getFiltersPanel();
        for(Genre g : genreDao.getAll()) {
            filtersPanel.addGenre(g.getName());
        }
    }
}
