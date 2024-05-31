/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.home;

import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.controller.client.ClientController;
import com.iglnierod.gamearchive.controller.game.GameController;
import com.iglnierod.gamearchive.controller.game.rate.RateGameController;
import com.iglnierod.gamearchive.controller.list.AddToListController;
import com.iglnierod.gamearchive.controller.list.CreateListController;
import com.iglnierod.gamearchive.controller.list.ListController;
import com.iglnierod.gamearchive.model.api.igdb.ImageType;
import com.iglnierod.gamearchive.model.api.igdb.Reference;
import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.client.dao.ClientDAO;
import com.iglnierod.gamearchive.model.client.dao.ClientDAOPostgreSQL;
import com.iglnierod.gamearchive.model.community.Activity;
import com.iglnierod.gamearchive.model.community.dao.CommunityDAO;
import com.iglnierod.gamearchive.model.community.dao.CommunityDAOPostgreSQL;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.game.filter.GameFilter;
import com.iglnierod.gamearchive.model.genre.Genre;
import com.iglnierod.gamearchive.model.genre.dao.GenreDAO;
import com.iglnierod.gamearchive.model.genre.dao.GenreDAOPostgreSQL;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.list.dao.ListDAO;
import com.iglnierod.gamearchive.model.list.dao.ListDAOPostgreSQL;
import com.iglnierod.gamearchive.model.session.Session;
import com.iglnierod.gamearchive.utils.Util;
import com.iglnierod.gamearchive.view.client.ClientPanel;
import com.iglnierod.gamearchive.view.client.ClientPreviewPanel;
import com.iglnierod.gamearchive.view.game.GameDialog;
import com.iglnierod.gamearchive.view.game.panel.GamePreviewPanel;
import com.iglnierod.gamearchive.view.game.rate.RateGameDialog;
import com.iglnierod.gamearchive.view.home.HomeFrame;
import com.iglnierod.gamearchive.view.home.community.ActivityPanel;
import com.iglnierod.gamearchive.view.home.community.CommunityPanel;
import com.iglnierod.gamearchive.view.home.list.ListsPanel;
import com.iglnierod.gamearchive.view.home.list.dialog.AddToListDialog;
import com.iglnierod.gamearchive.view.home.list.dialog.CreateListDialog;
import com.iglnierod.gamearchive.view.home.list.dialog.ListDialog;
import com.iglnierod.gamearchive.view.home.list.panel.ListPreviewPanel;
import com.iglnierod.gamearchive.view.home.search.FiltersPanel;
import com.iglnierod.gamearchive.view.home.search.NoGamesFoundPanel;
import com.iglnierod.gamearchive.view.home.search.SearchPanel;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author rodri
 */
public class HomeController {

    private final HomeFrame view;
    private final Database database;
    private final Client currentClient;
    private final GameDAO gameDao;
    private final GenreDAO genreDao;
    private final ListDAO listDao;
    private final CommunityDAO communityDao;
    private final ClientDAO clientDao;

    // Search
    private final SearchPanel searchPanel;
    private ArrayList<Game> gamesResult;

    // Lists
    private final ListsPanel myListsPanel;
    private Set<Integer> favouriteGameIds;

    // Community
    private final CommunityPanel communityPanel;

    // Client
    private final ClientPanel clientPanel;

    public HomeController(HomeFrame view, Database database) {
        this.view = view;
        this.database = database;
        this.gameDao = new GameDAOUnirest(database);
        this.genreDao = new GenreDAOPostgreSQL(database);
        this.listDao = new ListDAOPostgreSQL(database);
        this.communityDao = new CommunityDAOPostgreSQL(database);
        this.clientDao = new ClientDAOPostgreSQL(database);
        this.currentClient = Session.getCurrentClient();
        this.searchPanel = new SearchPanel();
        this.gamesResult = new ArrayList<>();
        this.myListsPanel = new ListsPanel(false);
        this.communityPanel = new CommunityPanel();
        this.clientPanel = new ClientPanel();
        favouriteGameIds = new HashSet<>();
        addListeners();
        initiatePanels();
    }

    private void addListeners() {
        this.view.setUsernameLabelText(currentClient.getUsername());

        // MENU BAR
        this.view.addLogOutMenuItemActionListener(addLogOutMenuItemListener());
        this.view.addQuitMenuItemActionListener(addQuitMenuItemListener());
        this.view.addReloadListsMenuItemActionListener(addReloadListsMenuItemListener());
        this.view.addReloadActivityListsMenuItemActionListener(addReloadActivityMenuItemListener());

        // LABELS MENU
        this.view.addSearchLabelMouseListener(addSearchLabelListener());
        this.view.addMyListsLabelMouseListener(addMyListsLabelListener());
        this.view.addCommunityLabelMouseListener(addCommunityLabelListener());
        this.view.addUsernameLabelMouseListener(HomeController.this.addUsernameLabelListener());
    }

    private void initiatePanels() {
        this.addSearchPanelListeners();
        this.addGenresToFiltersPanel();

        this.addMyListsPanelListeners();
    }

    private void addSearchPanelListeners() {
        searchPanel.addSearchButtonActionListener(addSearchButtonListener());
        searchPanel.addFilterButtonActionListener(addFilterButtonListener());
    }

    private void addMyListsPanelListeners() {
        myListsPanel.addCreateListPanelMouseListener(addCreateListPanelListener());
        myListsPanel.addFavouriteListPanelMouseListener(this.addPreviewPanelListener(listDao.getFavouriteList(), true));
        this.updateListsPanel();
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

    private ActionListener addReloadListsMenuItemListener() {
        return (ActionEvent e) -> {
            reloadLists();
        };
    }

    public void reloadLists() {
        myListsPanel.emptyListPanel();
        this.updateListsPanel();
    }

    private ActionListener addReloadActivityMenuItemListener() {
        return (ActionEvent e) -> updateActivity();
    }

    // MENU
    private MouseListener addSearchLabelListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("SearchLabel: CLICK");
                view.setCenterContent(searchPanel);
            }
        };
    }

    private MouseListener addMyListsLabelListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("MyListsLabel: CLICK");
                view.setCenterContent(myListsPanel);
            }
        };
    }

    private MouseListener addCommunityLabelListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("CommunityLabel: CLICK");
                view.setCenterContent(communityPanel);
                updateActivity();
            }
        };
    }

    private MouseListener addUsernameLabelListener() {
        HomeController hc = this;
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClientController clientController = new ClientController(clientPanel, database, hc, Session.getCurrentClient());
                view.setCenterContent(clientPanel);
            }
        };
    }

    // SEARCH FUNCTIONS
    private ActionListener addSearchButtonListener() {
        return (ActionEvent e) -> {
            String input = searchPanel.getSearchTextFieldText();
            FiltersPanel filtersPanel = searchPanel.getFiltersPanel();
            if (filtersPanel.isSearchUsersSelected()) {
                ArrayList<Client> searchResult = clientDao.search(input);
                searchResult.stream().forEach(c -> {
                    ClientPreviewPanel pnl = new ClientPreviewPanel(c.getUsername());
                    pnl.setDescriptionTextAreaText(c.getDescription());
                    pnl.addPanelMouseListener(this.addUsernameLabelListener(c.getUsername()));
                    
                    searchPanel.emptyResults();
                    searchPanel.addToResults(pnl);
                });
            } else {
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
            }
        };
    }

    private ActionListener addFilterButtonListener() {
        return (ActionEvent e) -> {
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

        // Obtener todos los favoritos de una sola vez
        favouriteGameIds = listDao.getAllFavouriteGameIds();

        for (Game g : gamesResult) {
            boolean favourite = favouriteGameIds.contains(g.getId());
            GamePreviewPanel gamePanel = new GamePreviewPanel(g.getId(), favourite);

            gamePanel.addFavouriteButtonActionListener(this.addFavouriteButtonListener(g, gamePanel));
            gamePanel.addAddToButtonActionListener(this.addAddToListButtonListener(g));
            gamePanel.addRateButtonActionListener(this.addRateButtonListener(g, null));
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
    public MouseListener addGamePreviewPanelMouseListener(Game game) {
        HomeController hc = this;
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameDialog gameDialog = new GameDialog(view, false, favouriteGameIds.contains(game.getId()));
                gameDialog.addAddToListActionListener(addAddToListButtonListener(game));
                gameDialog.addFavourteButtonActionListener(addFavouriteButtonListener(game, gameDialog));
                GameController gameController = new GameController(gameDialog, database, game, hc);
                gameDialog.addRateButtonActionListener(addRateButtonListener(game, gameController));
                gameDialog.setVisible(true);
            }
        };
    }

    private ActionListener addFavouriteButtonListener(Game game, GamePreviewPanel gamePanel) {
        return (ActionEvent e) -> {
            Game g = gameDao.getAllInformation(game.getId());
            List favList = listDao.getFavouriteList();
            // Toggle favourite
            if (gamePanel.isFavourite()) {
                listDao.removeGame(favList.getId(), game.getId());
                gamePanel.markFavourite(false);
            } else {
                boolean res = gameDao.addToFavourite(g, favList.getId());
                if (res) {
                    JOptionPane.showMessageDialog(view, g.getName() + " added to your favourites",
                            "Added to Favourites", JOptionPane.INFORMATION_MESSAGE);
                    gamePanel.markFavourite(res);
                }
            }
        };
    }

    private ActionListener addFavouriteButtonListener(Game game, GameDialog gameDialog) {
        return (ActionEvent e) -> {
            Game g = gameDao.getAllInformation(game.getId());
            List favList = listDao.getFavouriteList();
            // Toggle favourite
            if (gameDialog.isFavourite()) {
                listDao.removeGame(favList.getId(), game.getId());
                gameDialog.markFavourite(false);
            } else {
                boolean res = gameDao.addToFavourite(g, favList.getId());
                if (res) {
                    JOptionPane.showMessageDialog(view, g.getName() + " added to your favourites",
                            "Added to Favourites", JOptionPane.INFORMATION_MESSAGE);
                    gameDialog.markFavourite(res);
                }
            }
        };
    }

    private ActionListener addAddToListButtonListener(Game game) {
        return (ActionEvent e) -> {
            System.out.println("ADD TO LIST BUTTON");
            AddToListDialog dialog = new AddToListDialog(view, true);
            AddToListController controller = new AddToListController(dialog, database, game);
            dialog.setVisible(true);
        };
    }

    private ActionListener addRateButtonListener(Game game, GameController gameController) {
        return (ActionEvent e) -> {
            System.out.println("RATE BUTTON");
            RateGameDialog rateGameDialog = new RateGameDialog(view, true);
            RateGameController rateGameController = new RateGameController(rateGameDialog, database, game);
            rateGameDialog.setVisible(true);
            if (gameController != null) {
                gameController.reload();
            }
        };
    }

    private void addGenresToFiltersPanel() {
        FiltersPanel filtersPanel = searchPanel.getFiltersPanel();
        for (Genre g : genreDao.getAll()) {
            filtersPanel.addGenre(g.getName());
        }
    }

    // ======= LISTS PANEL =======
    private MouseListener addCreateListPanelListener() {
        HomeController hc = this;
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CreateListDialog createListDialog = new CreateListDialog(view, true);
                CreateListController createListController = new CreateListController(createListDialog, database, hc);
                createListDialog.setVisible(true);
            }
        };
    }

    // Get lists from DB and update view
    public void updateListsPanel() {
        ArrayList<List> lists = listDao.getAll();
        if (lists.isEmpty() || lists == null) {
            return;
        }

        for (List l : lists) {
            addListToListsPanel(l, true);
        }
    }

    // Add list to view
    public void addListToListsPanel(List list, boolean isOwner) {
        addListToListsPanel(myListsPanel, list, isOwner);
    }

    public void addListToListsPanel(ListsPanel pnl, List list, boolean isOwner) {
        ListPreviewPanel listPreviewPanel = new ListPreviewPanel(list.getId(), list.getName());
        listPreviewPanel.addPanelMouseListener(this.addPreviewPanelListener(list, isOwner));
        pnl.addList(listPreviewPanel);
    }

    public MouseListener addPreviewPanelListener(List list, boolean isOwner) {
        HomeController hc = this;
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ListDialog listDialog = new ListDialog(view, false, isOwner);
                ListController listController = new ListController(listDialog, database, list, hc);
                listDialog.setVisible(true);
            }
        };
    }

    // COMMUNITY
    private void updateActivity() {
        communityPanel.emptyActivityPanel();
        ArrayList<Activity> activityList = communityDao.getLatest();

        for (Activity a : activityList) {
            communityPanel.addActivity(getActivityPanel(a));
        }
    }

    public JPanel getActivityPanel(Activity a) {
        ActivityPanel panel = new ActivityPanel(a.getUsername());
        panel.setUsernameLabelText(a.getUsername());
        panel.addUsernameLabelMouseListener(this.addUsernameLabelListener(a.getUsername()));
        String activityText = String.format("<html>Added <strong>%s</strong> to <strong>%s</strong></html>",
                Util.escapeHtml(a.getGameName()), Util.escapeHtml(a.getListName()));
        panel.setActivityLabelText(activityText);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = a.getDate().format(formatter);
        panel.setDateLabelText(formattedDate);

        SwingUtilities.invokeLater(() -> {
            if (a.getCoverId() != null) {
                try {
                    URL url = new URL(Reference.getImage(ImageType.COVER_SMALL, a.getCoverId()));
                    BufferedImage image = ImageIO.read(url);
                    panel.setCoverLabelIcon(new ImageIcon(image));
                    panel.setCoverLabelToolTip(a.getGameName());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        return panel;
    }

    public MouseListener addUsernameLabelListener(String username) {
        HomeController hc = this;
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog profileDialog = new JDialog(view);
                profileDialog.setTitle(String.format("GameArchive - %s Profile", username));
                profileDialog.setLayout(new BorderLayout());

                ClientPanel clientPanel = new ClientPanel();
                ClientController clientController = new ClientController(clientPanel, database, hc, false, username);

                profileDialog.add(clientPanel, BorderLayout.CENTER);
                profileDialog.setSize(1200, 670);
                profileDialog.setLocationRelativeTo(view);  // Centra el diálogo relativo a la ventana 'view'
                profileDialog.setVisible(true);
            }
        };
    }
}
