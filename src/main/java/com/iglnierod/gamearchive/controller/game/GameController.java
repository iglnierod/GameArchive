/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.game;

import com.iglnierod.gamearchive.model.api.igdb.ImageType;
import com.iglnierod.gamearchive.model.api.igdb.Reference;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.genre.Genre;
import com.iglnierod.gamearchive.model.platform.Platform;
import com.iglnierod.gamearchive.utils.ImageTool;
import com.iglnierod.gamearchive.view.game.GameDialog;
import com.iglnierod.gamearchive.view.game.panel.GameCoverPanel;
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

    public GameController(GameDialog view, Database database, Game game) {
        this.view = view;
        this.database = database;
        this.gameDao = new GameDAOUnirest(database);
        this.game = game;
        this.similarGames = new ArrayList<>();
        this.fillGameInformation();
        this.addListeners();
    }

    private void addListeners() {
        // TODO
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
        
        this.addSimilarGames();
    }
    
    public void addFavouriteButtonListener(ActionListener l) {
        this.view.addFavourteButtonActionListener(l);
    }

    private void addSimilarGames() {
        //TODO
        this.similarGames = gameDao.getSimilar(this.game.getId());
        for(Game g : similarGames) {
            GameCoverPanel gamePanel = new GameCoverPanel(g.getId());
            // Cargar imagen de forma asíncrona
                SwingUtilities.invokeLater(() -> {
                    ImageIcon url = ImageTool.loadImageFromURL(Reference.getImage(ImageType.COVER_BIG, g.getCoverId()));
                    ImageIcon imageIcon = new ImageIcon(ImageTool.getScaledImage(url.getImage(), GameCoverPanel.WIDTH, GameCoverPanel.HEIGHT));
                    gamePanel.setGameLabelImageIcon(imageIcon);
                    this.view.addSimilarGame(gamePanel);
                });
        }
    }
}
