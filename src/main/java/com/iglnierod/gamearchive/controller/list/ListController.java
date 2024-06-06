/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.list;

import com.iglnierod.gamearchive.controller.home.HomeController;
import com.iglnierod.gamearchive.model.api.igdb.ImageType;
import com.iglnierod.gamearchive.model.api.igdb.Reference;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.list.dao.ListDAO;
import com.iglnierod.gamearchive.model.list.dao.ListDAOPostgreSQL;
import com.iglnierod.gamearchive.utils.ImageTool;
import com.iglnierod.gamearchive.view.game.panel.GameCoverPanel;
import com.iglnierod.gamearchive.view.home.list.dialog.EditListDialog;
import com.iglnierod.gamearchive.view.home.list.dialog.ExportListDialog;
import com.iglnierod.gamearchive.view.home.list.dialog.ImportListDialog;
import com.iglnierod.gamearchive.view.home.list.dialog.ListDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author iglnierod
 */
public class ListController {

    private final ListDialog view;
    private final Database database;
    private final GameDAO gameDao;
    private final ListDAO listDao;
    private final List list;
    private HomeController homeController;
    
    public ListController(ListDialog view, Database database, List list, HomeController homeController) {
        this.view = view;
        this.database = database;
        this.gameDao = new GameDAOUnirest(database);
        this.listDao = new ListDAOPostgreSQL(database);
        this.list = list;
        System.out.println("GAMES IN LIST: " + list.getGames());
        this.homeController = homeController;
        
        this.addListeners();

        this.reload();
        this.checkFavourite();
    }

    private void addListeners() {
        this.view.addReloadMenuItemActionListener(this.addReloadMenuItemListener());
        this.view.addEditMenuItemActionListener(this.addEditMenuItemListener());
        this.view.addImportMenuItemActionListener(this.addImportMenuItemListener());
        this.view.addExportMenuItemActionListener(this.addExportMenuItemListener());
        this.view.addDeleteMenuItemActionListener(this.addDeleteMenuItemListener());
    }

    private void checkFavourite() {
        if (list.isFavourite()) {
            view.disableDeleteMenuItem();
        }
    }

    private void loadInformation() {
        this.view.setTitle("GameArchive - Watching List: " + list.getName());
        this.view.setNameLabelText(list.getName());
    }

    private void loadGames() {
        // REVISAR: Cargar imagenes de manera asincrona
        ExecutorService executorService = Executors.newFixedThreadPool(5); // Número de hilos para cargar imágenes

        list.setGames(gameDao.getGamesInList(this.list.getId()));
        for (Game g : list.getGames()) {
            executorService.execute(() -> {
                System.out.println(g);
                GameCoverPanel gamePanel = new GameCoverPanel(g.getId());
                gamePanel.setToolTipText(g.getName());
                gamePanel.addCoverMouseListener(this.homeController.addGamePreviewPanelMouseListener(g));
                
                // Cargar imagen de forma asíncrona
                SwingUtilities.invokeLater(() -> {
                    ImageIcon url = ImageTool.loadImageFromURL(Reference.getImage(ImageType.COVER_BIG, g.getCoverId()));
                    ImageIcon imageIcon = new ImageIcon(ImageTool.getScaledImage(url.getImage(), GameCoverPanel.WIDTH, GameCoverPanel.HEIGHT));
                    gamePanel.setGameLabelImageIcon(imageIcon);
                    view.addGame(gamePanel);
                });
            });
        }

        executorService.shutdown(); // Apaga el ExecutorService después de que todas las tareas hayan sido completadas
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // Espera a que todas las tareas se completen
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        view.revalidaCenterPanel();
    }

    private ActionListener addEditMenuItemListener() {
        return (ActionEvent e) -> {
            EditListDialog editListDialog = new EditListDialog(null, true);
            if (list.isFavourite()) {
                editListDialog.disableInformationPanel();
            }
            EditListController editListController = new EditListController(editListDialog, database, list);
            editListDialog.setVisible(true);
            reload();
        };
    }

    private ActionListener addReloadMenuItemListener() {
        return (ActionEvent e) -> {
            reload();
        };
    }

    private ActionListener addExportMenuItemListener() {
        return (ActionEvent e) -> {
            ExportListDialog dialog = new ExportListDialog(null, true);
            ExportListController controller = new ExportListController(dialog, database, list);
            dialog.setVisible(true);
        };
    }
    
    private ActionListener addImportMenuItemListener() {
        return (ActionEvent e) -> {
            ImportListDialog dialog = new ImportListDialog(null, true);
            ImportListController controller = new ImportListController(dialog, database, list);
            dialog.setVisible(true);
            reload();
        };
    }
    
    private ActionListener addDeleteMenuItemListener() {
        return (ActionEvent e) -> {
            final int DELETE_OPTION = 0;
            int delete = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this list?",
                    "CONFIRMATION", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (delete == DELETE_OPTION) {
                listDao.delete(list);
                view.dispose();
                homeController.reloadLists();
            }
        };
    }

    private void reload() {
        view.removeAllGames();
        loadGames();
        loadInformation();
    }

}
