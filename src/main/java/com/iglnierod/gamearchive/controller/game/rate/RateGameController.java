/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.game.rate;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.view.game.rate.RateGameDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author iglnierod
 */
public class RateGameController {
    private final RateGameDialog view;
    private final Database database;
    private final Game game;
    private final GameDAO gameDao;

    public RateGameController(RateGameDialog view, Database database, Game game) {
        this.view = view;
        this.database = database;
        this.game = game;
        this.gameDao = new GameDAOUnirest(database);
        this.view.setTitle("Rating game: " + game.getName());
        this.addListeners();
    }
    
    private void addListeners() {
        this.view.addCancelButtonActionListener(this.addCancelButtonListener());
        this.view.addRateButtonActionListener(this.addRateButtonListener());
    }
    
    private ActionListener addCancelButtonListener() {
        return (ActionEvent e) -> {
            view.dispose();
        };
    }
    
    private ActionListener addRateButtonListener() {
        return (ActionEvent e) -> {
            int rating = view.getRating();
            String comment = view.getComment();
            boolean res = gameDao.addRating(game, rating, comment);
            if(!res) {
                JOptionPane.showMessageDialog(view, "An error ocurred rating this game.",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                view.dispose();
            }
        };
    }
}
