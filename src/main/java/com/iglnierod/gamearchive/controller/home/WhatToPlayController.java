/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.home;

import com.iglnierod.gamearchive.model.api.igdb.ImageType;
import com.iglnierod.gamearchive.model.api.igdb.Reference;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.game.filter.GameFilter;
import com.iglnierod.gamearchive.model.genre.Genre;
import com.iglnierod.gamearchive.model.genre.dao.GenreDAO;
import com.iglnierod.gamearchive.model.genre.dao.GenreDAOPostgreSQL;
import com.iglnierod.gamearchive.utils.ImageTool;
import com.iglnierod.gamearchive.view.game.panel.GameCoverPanel;
import com.iglnierod.gamearchive.view.home.dialog.WhatToPlayDialog;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;

/**
 *
 * @author iglnierod
 */
public class WhatToPlayController {

    private final WhatToPlayDialog view;
    private final Database database;
    private final GenreDAO genreDao;
    private final GameDAO gameDao;
    private ArrayList<Genre> genres;
    private HomeController hc;

    public WhatToPlayController(WhatToPlayDialog view, Database database, HomeController hc) {
        this.view = view;
        this.database = database;
        this.hc = hc;
        this.genreDao = new GenreDAOPostgreSQL(database);
        this.gameDao = new GameDAOUnirest(database);
        genres = new ArrayList<>();

        this.addListeners();
    }

    private void addListeners() {
        this.view.addGetGameButtonActionListener(this.addGetGameButtonListener());
        this.addGenres();
    }

    private ActionListener addGetGameButtonListener() {
        return (e) -> {
            String selectedGenres = this.getSelectedGenres();
            boolean allPlatfoms = this.view.isAllPlatformsSelected();
            int minRating = this.view.getMinRatingSpinnerValue();
            GameFilter filter = new GameFilter(
                    "30",
                    String.valueOf(minRating),
                    allPlatfoms,
                    selectedGenres
            );
            ArrayList<Game> games = gameDao.getRandom(filter);
            Random r = new Random();
            Game randomGame = games.get(r.nextInt(games.size() - 1));
            GameCoverPanel coverPanel = new GameCoverPanel(randomGame.getId());
            coverPanel.addCoverMouseListener(hc.addGamePreviewPanelMouseListener(randomGame));
            if (randomGame.getCoverId() != null) {
                ImageIcon url = ImageTool.loadImageFromURL(Reference.getImage(ImageType.COVER_BIG, randomGame.getCoverId()));
                Image image = ImageTool.getScaledImage(url.getImage(), GameCoverPanel.WIDTH, GameCoverPanel.HEIGHT);
                ImageIcon imageIcon = new ImageIcon(image);

                SwingUtilities.invokeLater(() -> {
                    if (imageIcon != null) {
                        coverPanel.setGameLabelImageIcon(imageIcon);
                    }
                    this.view.emptyResultPanel();
                    this.view.addGameResult(coverPanel);
                });
            }
        };
    }

    private void addGenres() {
        this.genres = genreDao.getAll();
        for (Genre g : genres) {
            JCheckBox genreCheckBox = new JCheckBox(g.getName());
            this.view.addGenre(genreCheckBox);
        }
    }

    public String getSelectedGenres() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        Component[] components = this.view.getGenresPanelComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) components[i];
                if (checkBox.isSelected()) {
                    if (sb.length() > 1) { // Añade coma si no es el primer elemento
                        sb.append(",");
                    }
                    sb.append("\"").append(checkBox.getText()).append("\"");
                }
            }
        }
        sb.append(")");

        if (sb.toString().equals("()")) {
            return null;
        }

        return sb.toString();
    }
}
