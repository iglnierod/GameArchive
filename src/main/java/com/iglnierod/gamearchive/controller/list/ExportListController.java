/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.list;

import com.google.gson.*;
import com.iglnierod.gamearchive.model.api.igdb.ImageType;
import com.iglnierod.gamearchive.model.api.igdb.Reference;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
                JOptionPane.showMessageDialog(view, "Error while exporting the list",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    private void export(ExportFormat format, File dir) throws IOException {
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
        ExportList exportList = new ExportList(
                list.getId(),
                list.getName(),
                list.getDescription(),
                list.getGames().stream()
                        .map(g -> new ExportGame(g.getId(), g.getName()))
                        .collect(Collectors.toList())
        );

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExportList.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            try (FileWriter writer = new FileWriter(dir)) {
                marshaller.marshal(exportList, writer);
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    private void exportToHTML(File dir) {
        ExportList exportList = new ExportList(
                list.getId(),
                list.getName(),
                list.getDescription(),
                list.getGames().stream()
                        .map(g -> new ExportGame(g.getId(), g.getName(), g.getCoverId()))
                        .collect(Collectors.toList())
        );

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n")
                .append("<meta charset=\"UTF-8\" />\n")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n")
                .append("<title>").append(exportList.getName()).append("</title>\n")
                .append("<style>\n")
                .append("* { padding: 0; margin: 0; box-sizing: border-box; }\n")
                .append("body { font-family: Arial, Helvetica, sans-serif; font-size: 1rem; color: white;\n")
                .append("background: linear-gradient(109.6deg, rgb(36, 45, 57) 11.2%, rgb(16, 37, 60) 51.2%, rgb(0, 0, 0) 98.6%);\n")
                .append("background-repeat: no-repeat; min-height: 100vh; }\n")
                .append(".wrapper { width: 100%; }\n")
                .append(".wrapper h1 { text-align: center; padding: 10px; }\n")
                .append(".games { display: flex; flex-direction: row; flex-wrap: wrap; justify-content: center; gap: 30px 15px; padding: 15px; }\n")
                .append(".game { display: flex; flex-direction: column; justify-content: center; text-align: center; max-width: 264px; gap: 5px; transition: 0.25s ease-in-out all; }\n")
                .append(".game:hover { transition: 0.25s ease-in-out all; scale: 0.9; }\n")
                .append(".game img { border-radius: 5px; }\n")
                .append(".game span { font-weight: 900; }\n")
                .append("footer { width: 100%; background-color: rgba(0, 0, 0, 0.3); margin-top: 30px; height: 50px; display: flex; justify-content: center; align-items: center; backdrop-filter: blur(10px); }\n")
                .append("footer a:link, a:hover { color: currentColor; font-weight: bold; text-decoration: underline; }\n")
                .append("</style>\n</head>\n<body>\n")
                .append("<div class=\"wrapper\">\n")
                .append("<h1>").append("[").append(exportList.getId()).append("] ").append(exportList.getName()).append("</h1>\n")
                .append("<section class=\"games\">\n");

        for (ExportGame game : exportList.getGames()) {
            String coverUrl = Reference.getImage(ImageType.COVER_BIG, game.getCoverId());
            htmlContent.append("<div class=\"game\">\n")
                    .append("<img src=\"").append(coverUrl).append("\" alt=\"").append(game.getName()).append(" cover\" />\n")
                    .append("<span class=\"game-name\">").append(game.getName()).append("</span>\n")
                    .append("</div>\n");
        }

        htmlContent.append("</section>\n")
                .append("<footer>\n")
                .append("<span>List made on <a href=\"https://github.com/iglnierod/GameArchive\">GameArchive</a></span>\n")
                .append("</footer>\n</div>\n</body>\n</html>");

        try (FileWriter writer = new FileWriter(dir)) {
            writer.write(htmlContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(File dir) throws IOException {
        if (!dir.isFile()) {
            return;
        }

        dir.createNewFile();
    }

}
