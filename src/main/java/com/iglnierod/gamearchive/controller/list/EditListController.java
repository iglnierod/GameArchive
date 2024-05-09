/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.list;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.list.dao.ListDAO;
import com.iglnierod.gamearchive.model.list.dao.ListDAOPostgreSQL;
import com.iglnierod.gamearchive.view.home.list.ListsPanel;
import com.iglnierod.gamearchive.view.home.list.dialog.CreateListDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author iglnierod
 */
public class EditListController {

    private final CreateListDialog view;
    private final Database database;
    private final ListsPanel myListsPanel;
    private final ListDAO listDao;
    private final List list;

    public EditListController(CreateListDialog view, Database database, ListsPanel myListsPanel, List list) {
        this.view = view;
        this.database = database;
        this.myListsPanel = myListsPanel;
        this.listDao = new ListDAOPostgreSQL(database);
        this.list = list;
        this.view.addCancelButtonActionListener(this.addCancelButtonListener());
        this.view.addCreateButtonActionListener(this.addEditButtonListener());
    }

    private ActionListener addCancelButtonListener() {
        return (ActionEvent e) -> {
            view.dispose();
        };
    }

    private ActionListener addEditButtonListener() {
        return (ActionEvent e) -> {
            // TODO: edit list
        };
    }
}
