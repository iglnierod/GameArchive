/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.list;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.list.dao.ListDAO;
import com.iglnierod.gamearchive.model.list.dao.ListDAOPostgreSQL;
import com.iglnierod.gamearchive.view.home.list.dialog.CreateListDialog;
import com.iglnierod.gamearchive.view.home.list.panel.ListPreviewPanel;
import com.iglnierod.gamearchive.view.home.list.ListsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author iglnierod
 */
public class CreateListController {

    private final CreateListDialog view;
    private final Database database;
    private final ListsPanel myListsPanel;
    private final ListDAO listDao;

    public CreateListController(CreateListDialog view, Database database, ListsPanel myListsPanel) {
        this.view = view;
        this.database = database;
        this.myListsPanel = myListsPanel;
        this.listDao = new ListDAOPostgreSQL(database);
        this.view.addCancelButtonActionListener(this.addCancelButtonListener());
        this.view.addCreateButtonActionListener(this.addCreateButtonListener());
    }

    private ActionListener addCancelButtonListener() {
        return (ActionEvent e) -> {
            view.dispose();
        };
    }

    private ActionListener addCreateButtonListener() {
        return (ActionEvent e) -> {
            String name = view.getNameTextFieldText();
            String description = view.getDescriptionTextAreaText();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(view, "List's name is empty. Fill it to create a new list.",
                        "ERROR: Name field is empty", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            List list = new List(name, description);
            boolean res = listDao.create(list);
            if (!res) {
                JOptionPane.showMessageDialog(view, "List couldn't be created.",
                        "ERROR: Could't create list", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            ListPreviewPanel listPreviewPanel = new ListPreviewPanel(list.getId(), list.getName(), 0);
            myListsPanel.addList(listPreviewPanel);
            view.dispose();
        };
    }
}
