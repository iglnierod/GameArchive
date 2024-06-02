/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.list;

import com.google.gson.Gson;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.list.ExportFormat;
import com.iglnierod.gamearchive.model.list.ExportList;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.list.dao.ListDAO;
import com.iglnierod.gamearchive.model.list.dao.ListDAOPostgreSQL;
import com.iglnierod.gamearchive.view.home.list.dialog.ImportListDialog;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.xml.bind.*;

/**
 *
 * @author iglnierod
 */
public class ImportListController {

    private final ImportListDialog view;
    private final Database database;
    private final ListDAO listDao;
    private List list;

    public ImportListController(ImportListDialog view, Database database, List list) {
        this.view = view;
        this.database = database;
        this.list = list;

        this.listDao = new ListDAOPostgreSQL(database);

        this.addListeners();
    }

    private void addListeners() {
        this.view.setFormatComboBoxModel(this.setFormatComboBoxModel());
        this.view.addChooseDirectoryButtonActionListener(this.addChooseDirectoryButtonListener());
        this.view.addCancelButtonActionListener(this.addCancelButtonListener());
        this.view.addImportButtonActionListener(this.addImportButtonListener());
    }

    private DefaultComboBoxModel<String> setFormatComboBoxModel() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (ExportFormat ef : ExportFormat.values()) {
            if (ef.canImport()) {
                model.addElement(ef.name());
            }
        }
        return model;
    }

    private ActionListener addCancelButtonListener() {
        return (e) -> {
            view.dispose();
        };
    }

    private ActionListener addChooseDirectoryButtonListener() {
        return (e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int comboSelectedIndex = view.getFormatComboBoxSelectedIndex();
            ExportFormat selectedFormat = ExportFormat.values()[comboSelectedIndex];
            String extension = selectedFormat.getExtension();

            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith("." + extension);
                }

                @Override
                public String getDescription() {
                    return extension.toUpperCase() + " Files";
                }
            });

            int result = fileChooser.showOpenDialog(view);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                view.setDirectoryTextFieldText(selectedFile.getAbsolutePath());
            }
        };
    }

    private ActionListener addImportButtonListener() {
        return (e) -> {

            String filePath = view.getDirectoryTextFieldText();
            File file = new File(filePath);
            int comboSelectedIndex = view.getFormatComboBoxSelectedIndex();
            ExportFormat format = ExportFormat.values()[comboSelectedIndex];

            if (file.exists() && format.canImport()) {
                try {
                    switch (format) {
                        case JSON:
                            importFromJSON(file);
                            break;
                        case XML:
                            importFromXML(file);
                            break;
                    }
                    JOptionPane.showMessageDialog(view, "List imported successfully from " + format.name(),
                            "Import Successful", JOptionPane.INFORMATION_MESSAGE);
                    view.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(view, "Failed to import list: " + ex.getMessage(),
                            "Import Failed", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Invalid file or unsupported format",
                        "Import Failed", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    private void importFromJSON(File file) {
        // Implementación para importar desde JSON
        Gson gson = new Gson();

        String jsonString = readFile(file);

        ExportList exList = gson.fromJson(jsonString, ExportList.class);

        listDao.importGames(this.list.getId(), exList);
    }

    private void importFromXML(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExportList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ExportList exList = (ExportList) jaxbUnmarshaller.unmarshal(file);

            listDao.importGames(this.list.getId(), exList);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to import from XML: " + e.getMessage());
        }
    }

    private String readFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content.toString();
    }
}
