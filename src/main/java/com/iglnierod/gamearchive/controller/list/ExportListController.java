/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.list;

import com.google.gson.*;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.game.ExportGame;
import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.dao.GameDAO;
import com.iglnierod.gamearchive.model.game.dao.GameDAOUnirest;
import com.iglnierod.gamearchive.model.list.ExportFormat;
import com.iglnierod.gamearchive.model.list.ExportList;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.view.home.list.dialog.ExportListDialog;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author iglnierod
 */
public class ExportListController {

    private final ExportListDialog view;
    private final Database database;
    private List list;
    private ArrayList<String> extensions;

    public ExportListController(ExportListDialog view, Database database, List list) {
        this.view = view;
        this.database = database;
        this.list = list;
        this.extensions = new ArrayList<>();
        this.addListeners();
    }

    private void addListeners() {
        this.view.setFormatComboBoxModel(this.setFormatComboBoxModel());
        this.view.addChooseDirectoryButtonActionListener(this.addChooseDirectoryButtonListener());
        this.view.addCancelButtonActionListener(this.addCancelButtonListener());
        this.view.addExportButtonActionListener(this.addExportButtonListener());
    }

    private DefaultComboBoxModel<String> setFormatComboBoxModel() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (ExportFormat ef : ExportFormat.values()) {
            model.addElement(ef.toString());
        }
        return model;
    }

    private ActionListener addChooseDirectoryButtonListener() {
        return (e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int comboSelectedIndex = view.getFormatComboBoxSelectedIndex();
            String extension = ExportFormat.values()[comboSelectedIndex].toString().toLowerCase();
            String fileName = String.format("%s.%s", list.getName(), extension);
            fileChooser.setSelectedFile(new File(fileName));

            int result = fileChooser.showSaveDialog(view);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                view.setDirectoryTextFieldText(selectedFile.getAbsolutePath());
            }
        };
    }

    private ActionListener addCancelButtonListener() {
        return (e) -> {
            view.dispose();
        };
    }

    private ActionListener addExportButtonListener() {
        return (e) -> {
            File file = new File(view.getDirectoryTextFieldText());
            int comboSelectedIndex = view.getFormatComboBoxSelectedIndex();
            ExportFormat format = ExportFormat.values()[comboSelectedIndex];

            try {
                GameDAO gameDao = new GameDAOUnirest(database);
                ArrayList<Game> updatedGames = new ArrayList<>(); // Nueva lista para juegos actualizados

                for (Game g : list.getGames()) {
                    Game updatedGame = gameDao.getAllInformation(g.getId());
                    updatedGames.add(updatedGame); // Añadir el juego actualizado a la nueva lista
                }

                list.setGames(updatedGames); // Actualizar la lista de juegos con la información completa
                this.export(format, file);
                JOptionPane.showMessageDialog(view, "List exported to " + format.name() + " in " + file.getAbsolutePath(),
                        "List exported", JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }

    private void export(ExportFormat format, File dir) throws IOException {
        // TODO: export file to selected format
        switch (format) {
            case JSON:
                this.exportToJSON(dir);
                break;
            case XML:
                this.exportToXML(dir);
                break;
            case HTML:
                this.exportToHTML(dir);
                break;
        }
        this.saveFile(dir);
    }

    private void exportToJSON(File dir) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder = gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        // Convertir la lista a la clase auxiliar ExportList
        ExportList exportList = new ExportList(
                list.getId(),
                list.getName(),
                list.getDescription(),
                list.getGames().stream()
                        .map(g -> new ExportGame(g.getId(), g.getName()))
                        .collect(Collectors.toList())
        );

        try (FileWriter writer = new FileWriter(dir)) {
            gson.toJson(exportList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportToXML(File dir) {
        // TODO
    }

    private void exportToHTML(File dir) {
        // TODO
    }

    private void saveFile(File dir) throws IOException {
        // TODO
        if (!dir.isFile()) {
            return;
        }

        dir.createNewFile();
    }

}
