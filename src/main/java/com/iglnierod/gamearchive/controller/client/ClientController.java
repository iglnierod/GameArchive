/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.client;

import com.iglnierod.gamearchive.controller.home.HomeController;
import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.client.dao.ClientDAO;
import com.iglnierod.gamearchive.model.client.dao.ClientDAOPostgreSQL;
import com.iglnierod.gamearchive.model.community.dao.CommunityDAO;
import com.iglnierod.gamearchive.model.community.dao.CommunityDAOPostgreSQL;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.GameStatus;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.list.dao.ListDAO;
import com.iglnierod.gamearchive.model.list.dao.ListDAOPostgreSQL;
import com.iglnierod.gamearchive.model.platform.Platform;
import com.iglnierod.gamearchive.view.client.ClientPanel;
import com.iglnierod.gamearchive.view.game.rate.RatePanel;
import com.iglnierod.gamearchive.view.home.list.ListsPanel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 *
 * @author iglnierod
 */
public class ClientController {

    private final ClientPanel view;
    private final Database database;
    private final HomeController hc;
    private final ClientDAO clientDao;
    private final ListDAO listDao;
    private final CommunityDAO communityDao;
    private final GameDAO gameDao;
    private boolean isCurrentUser;
    private String clientUsername;
    private Client client;
    private ListsPanel listsPanel;

    public ClientController(ClientPanel view, Database database, HomeController hc, boolean isCurrentUser, String clientUsername) {
        System.out.println("ClientController");
        this.view = view;
        this.database = database;
        this.hc = hc;
        this.clientDao = new ClientDAOPostgreSQL(database);
        this.listDao = new ListDAOPostgreSQL(database);
        this.communityDao = new CommunityDAOPostgreSQL(database);
        this.gameDao = new GameDAOUnirest(database);
        this.isCurrentUser = isCurrentUser;
        this.clientUsername = clientUsername;
        this.listsPanel = new ListsPanel(true);

        this.loadPanels();
    }

    public ClientController(ClientPanel view, Database database, HomeController hc, Client currentClient) {
        this(view, database, hc, true, currentClient.getUsername());
    }

    public void loadPanels() {
        this.loadInformationPanel();
        this.loadListsPanel();
        this.loadRatingsPanel();
        this.loadActivityPanel();
    }

    private void loadInformationPanel() {
        this.client = clientDao.getClient(clientUsername);
        this.view.setTitleLabelText(String.format("%s Profile", client.getUsername()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.view.setJoinedOnTextFieldText(client.getJoinedOn().format(formatter));
        this.view.setUsernameTextFieldText(client.getUsername());
        this.view.setDescriptionTextAreaText(client.getDescription());
        this.view.setPlatformsListModel(this.getPlatformsListModel());
        this.view.setConfigureButtonVisible(this.isCurrentUser);
        this.view.setConfigureButtonActionListener(this.setConfigureButtonListener());
    }

    private ListModel<String> getPlatformsListModel() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for(Platform p : client.getPlatformsList()) {
            model.addElement(p.getName());
        }
        return model;
    }

    private ActionListener setConfigureButtonListener() {
        return (e) -> {
            // TODO
            /*ProfileConfigFrame profileConfigFrame = new ProfileConfigFrame();
            ProfileConfigController controller = new ProfileConfigController(profileConfigFrame, database);
            profileConfigFrame.setVisible(true);*/
        };
    }

    private void loadListsPanel() {
        this.view.addListsPanel(listsPanel);
        this.listsPanel.addFavouriteListPanelMouseListener(hc.addPreviewPanelListener(listDao.getFavouriteList(client), false));
        this.listsPanel.addWantToPlayListPanelMouseListener(
                hc.addStatusListPreviewPanelListener(listDao.getGameByStatus(GameStatus.WANT_TO_PLAY), GameStatus.WANT_TO_PLAY, client)
        );
        this.listsPanel.addPlayingListPanelMouseListener(
                hc.addStatusListPreviewPanelListener(listDao.getGameByStatus(GameStatus.PLAYING), GameStatus.PLAYING, client)
        );

        this.listsPanel.addPlayedListPanelMouseListener(
                hc.addStatusListPreviewPanelListener(listDao.getGameByStatus(GameStatus.PLAYED), GameStatus.PLAYED, client)
        );
        listDao.getAll(client).stream().forEach(l -> hc.addListToListsPanel(listsPanel, l, false));
    }

    public void addFavouriteListActionListener(MouseListener l) {
        this.listsPanel.addFavouriteListPanelMouseListener(l);
    }

    private void loadRatingsPanel() {
        this.view.emptyRatings();
        this.gameDao.getRatings(client.getUsername()).stream().forEach(r -> {
            RatePanel panel = new RatePanel();
            panel.setUsernameText(r.getUsername());
            panel.setRating(r.getRating());
            panel.setCommentText(r.getComment());
            panel.setGameNameLabelText(r.getGameName());
            this.view.addRating(panel);
        });
    }

    private void loadActivityPanel() {
        this.view.emptyActivity();
        this.communityDao.getLatest(client.getUsername()).stream().forEach(a -> {
            this.view.addActivity(hc.getActivityPanel(a));
        });
    }

}
