/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.list;

import com.iglnierod.gamearchive.model.api.igdb.ImageType;
import com.iglnierod.gamearchive.model.api.igdb.Reference;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.utils.ImageTool;
import com.iglnierod.gamearchive.view.game.GameCoverPanel;
import com.iglnierod.gamearchive.view.home.list.dialog.ListDialog;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author iglnierod
 */
public class ListController {

    private final ListDialog view;
    private final Database database;
    private final GameDAO gameDao;
    private final List list;

    public ListController(ListDialog view, Database database, List list) {
        this.view = view;
        this.database = database;
        this.gameDao = new GameDAOUnirest(database);
        this.list = list;

        this.loadInformation();
    }

    private void loadInformation() {
        this.view.setTitle("GameArchive - Watching List: " + list.getName());
        this.view.setNameLabelText(list.getName());
        this.loadGames();
    }

    private void loadGames() {
        // TODO: load games with GameCoverPanel
        ExecutorService executorService = Executors.newFixedThreadPool(5); // Número de hilos para cargar imágenes

        for (Game g : gameDao.getGamesInList(this.list.getId())) {
            executorService.execute(() -> {
                System.out.println(g);
                GameCoverPanel gamePanel = new GameCoverPanel(g.getId());
                gamePanel.setToolTipText(g.getName());

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
}
