/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.list;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.list.dao.ListDAO;
import com.iglnierod.gamearchive.model.list.dao.ListDAOPostgreSQL;
import com.iglnierod.gamearchive.view.home.list.dialog.EditListDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author iglnierod
 */
public class EditListController {

    private final EditListDialog view;
    private final Database database;
    private final ListDAO listDao;
    private final List list;

    public EditListController(EditListDialog view, Database database, List list) {
        this.view = view;
        this.database = database;
        this.listDao = new ListDAOPostgreSQL(database);
        this.list = list;
        this.view.addCancelButtonActionListener(this.addCancelButtonListener());
        this.view.addEditButtonActionListener(this.addEditButtonListener());
        this.view.addRemoveButtonActionListener(this.addRemoveGameButtonListener());

        this.view.setNameTextFieldText(this.list.getName());
        this.view.setDescriptionTextAreaText(this.list.getDescription());
        loadGamesList();
    }

    private ActionListener addCancelButtonListener() {
        return (ActionEvent e) -> {
            view.dispose();
        };
    }

    private ActionListener addEditButtonListener() {
        return (ActionEvent e) -> {
            // TODO: edit list
            if (view.getNameTextFieldText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "List name cannot be an empty string",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println("list:" + list);
            list.setName(view.getNameTextFieldText());
            list.setDescription(view.getDescriptionTextAreaText());
            System.out.println("list:" + list);

            listDao.update(list);
            view.dispose();
        };
    }

    private ActionListener addRemoveGameButtonListener() {
        return (ActionEvent e) -> {
            // TODO
            int selectedIndex = view.getGamesListSelectedIndex();
            if (selectedIndex == -1) {
                return;
            }

            Game selectedGame = list.getGames().get(selectedIndex);
            list.getGames().remove(selectedIndex);
            listDao.removeGame(list.getId(), selectedGame.getId());
            loadGamesList();
        };
    }

    private void loadGamesList() {
        DefaultListModel model = new DefaultListModel();
        for (Game g : list.getGames()) {
            model.addElement(g.getName());
        }
        this.view.setGamesListModel(model);
    }
}
