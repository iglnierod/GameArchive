/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.list;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.list.dao.ListDAO;
import com.iglnierod.gamearchive.model.list.dao.ListDAOPostgreSQL;
import com.iglnierod.gamearchive.view.home.list.dialog.AddToListDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author iglnierod
 */
public class AddToListController {

    private final AddToListDialog view;
    private final Database database;
    private Game game;
    private final ListDAO listDao;
    private final GameDAO gameDao;
    ArrayList<List> lists;

    public AddToListController(AddToListDialog view, Database database, Game game) {
        this.view = view;
        this.database = database;
        this.listDao = new ListDAOPostgreSQL(database);
        this.gameDao = new GameDAOUnirest(database);
        this.game = this.setGame(game);
        this.lists = new ArrayList<List>();

        this.view.addCancelButtonActionListener(this.addCancelButtonListener());
        this.view.addAddButtonActionListener(this.addAddButtonListener());
        this.loadListsComboBox();
        this.setViewTitle();
    }

    private Game setGame(Game game) {
        return this.gameDao.getAllInformation(game.getId());
    }
    
    private ActionListener addCancelButtonListener() {
        return (ActionEvent e) -> {
            view.dispose();
        };
    }

    private ActionListener addAddButtonListener() {
        return (ActionEvent e) -> {
            int selectedIndex = this.view.getListComboBoxSelectedIndex();
            boolean res = this.gameDao.addToList(this.game, this.lists.get(selectedIndex));
            if(res) {
                this.view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Operation failed. Try again.",
                    "ERROR: Adding game to list", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    private void loadListsComboBox() {
        this.lists = listDao.getAll();
        if (lists.isEmpty()) {
            JOptionPane.showMessageDialog(view, "You don't have any list",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (List l : lists) {
            view.addListComboBoxItem(l.getName());
        }
    }
    
    private void setViewTitle() {
        this.view.setTitle(String.format("Adding: %s", this.game.getName()));
    }
}
