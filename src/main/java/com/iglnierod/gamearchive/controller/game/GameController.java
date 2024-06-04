/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.game;

import com.iglnierod.gamearchive.controller.home.HomeController;
import com.iglnierod.gamearchive.model.api.igdb.ImageType;
import com.iglnierod.gamearchive.model.api.igdb.Reference;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.GameStatus;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.game.rate.GameRate;
import com.iglnierod.gamearchive.model.genre.Genre;
import com.iglnierod.gamearchive.model.platform.Platform;
import com.iglnierod.gamearchive.utils.ImageTool;
import com.iglnierod.gamearchive.view.game.GameDialog;
import com.iglnierod.gamearchive.view.game.panel.GameCoverPanel;
import com.iglnierod.gamearchive.view.game.rate.RatePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author iglnierod
 */
public class GameController {

    private final GameDialog view;
    private final Database database;
    private final GameDAO gameDao;
    private Game game;
    private ArrayList<Game> similarGames;
    private HomeController hc;
    private ArrayList<GameRate> gameRatings;

    public GameController(GameDialog view, Database database, Game game, HomeController homeController) {
        this.view = view;
        this.database = database;
        this.gameDao = new GameDAOUnirest(database);
        this.game = game;
        this.similarGames = new ArrayList<>();
        this.gameRatings = new ArrayList<>();
        this.hc = homeController;
        this.fillGameInformation();
        this.addListeners();
    }

    private void addListeners() {
        this.view.addReloadMenuItemActionListener(this.addReloadMenuItemListener());

        this.view.addWantButtonActionListener(this.addWantButtonListener());
        this.view.addPlayingButtonActionListener(this.addPlayingButtonListener());
        this.view.addPlayedButtonActionListener(this.addPlayedButtonListener());
    }

    private void fillGameInformation() {
        this.game = gameDao.getAllInformation(this.game.getId());
        view.setTitle("GameArchive - " + this.game.getName());
        view.setNameLabelText(this.game.getName());
        view.setSummaryTextArea(this.game.getSummary());

        for (Platform p : this.game.getPlatforms()) {
            System.out.println("GameController-p:" + p);
            view.addPlatformToggleButton(p.getName());
        }

        for (Genre g : this.game.getGenres()) {
            System.out.println("GameController-g:" + g);
            view.addGenreToggleButton(g.getName());
        }

        if (this.game.getCoverId() != null) {
            try {
                URL url = new URL(Reference.getImage(ImageType.COVER_BIG, this.game.getCoverId()));
                BufferedImage image = ImageIO.read(url);
                view.setCoverLabelIcon(new ImageIcon(image));
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        view.setRatingProgressBar(Math.round(this.game.getIgdbRating()));
        view.setRatingCountLabel(this.game.getRatingCount());

        this.addRatings();
        this.addSimilarGames();
        this.checkGameStatus();
    }

    private void checkGameStatus() {
        GameStatus status = gameDao.getStatus(this.game);
        if (status == null) {
            return;
        }

        // Desactivar todos los botones primero
        this.view.setWantButtonSelected(false);
        this.view.setPlayingButtonSelected(false);
        this.view.setPlayedButtonSelected(false);

        // Activar el botón correspondiente al estado
        switch (status) {
            case WANT_TO_PLAY:
                this.view.setWantButtonSelected(true);
                break;
            case PLAYING:
                this.view.setPlayingButtonSelected(true);
                break;
            case PLAYED:
                this.view.setPlayedButtonSelected(true);
                break;
        }
    }

    public void addFavouriteButtonListener(ActionListener l) {
        this.view.addFavourteButtonActionListener(l);
    }

    private void addSimilarGames() {
        this.similarGames = gameDao.getSimilar(this.game.getId());
        for (Game g : similarGames) {
            GameCoverPanel gamePanel = new GameCoverPanel(g.getId());
            gamePanel.addCoverMouseListener(this.hc.addGamePreviewPanelMouseListener(g));
            // Cargar imagen de forma asíncrona
            SwingUtilities.invokeLater(() -> {
                ImageIcon url = ImageTool.loadImageFromURL(Reference.getImage(ImageType.COVER_BIG, g.getCoverId()));
                ImageIcon imageIcon = new ImageIcon(ImageTool.getScaledImage(url.getImage(), GameCoverPanel.WIDTH, GameCoverPanel.HEIGHT));
                if (imageIcon != null) {
                    gamePanel.setGameLabelImageIcon(imageIcon);
                }
                this.view.addSimilarGame(gamePanel);
            });
        }
    }

    private void addRatings() {
        this.view.emptyRatings();
        this.gameRatings = gameDao.getRatings(this.game);
        for (GameRate r : gameRatings) {
            RatePanel panel = new RatePanel();
            panel.addUsernameLabelMouseListener(hc.addUsernameLabelListener(r.getUsername()));
            panel.setUsernameText(r.getUsername());
            panel.setRating(r.getRating());
            panel.setCommentText(r.getComment());
            this.view.addRating(panel);
        }
    }

    private ActionListener addReloadMenuItemListener() {
        return (ActionEvent e) -> {
            reload();
        };
    }

    public void reload() {
        this.fillGameInformation();
    }

    // STATUS
    private ActionListener addWantButtonListener() {
        return (e) -> {
            System.out.println("WANT");
            gameDao.setStatus(this.game, GameStatus.WANT_TO_PLAY, !view.isWantButtonSelected());
            this.view.setPlayingButtonSelected(false);
            this.view.setPlayedButtonSelected(false);
        };
    }

    private ActionListener addPlayingButtonListener() {
        return (e) -> {
            System.out.println("PLAYING");
            gameDao.setStatus(this.game, GameStatus.PLAYING, !view.isPlayingButtonSelected());
            this.view.setWantButtonSelected(false);
            this.view.setPlayedButtonSelected(false);
        };
    }

    private ActionListener addPlayedButtonListener() {
        return (e) -> {
            System.out.println("PLAYED");
            gameDao.setStatus(this.game, GameStatus.PLAYED, !view.isPlayedButtonSelected());
            this.view.setWantButtonSelected(false);
            this.view.setPlayingButtonSelected(false);
        };
    }
}
