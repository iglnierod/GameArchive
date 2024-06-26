/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.iglnierod.gamearchive.view.home.search;

import java.awt.Component;
import javax.swing.JCheckBox;

/**
 *
 * @author iglnierod
 */
public class FiltersPanel extends javax.swing.JPanel {

    /**
     * Creates new form FiltersPanel
     */
    public FiltersPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        platformsButtonGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        limitLabel = new javax.swing.JLabel();
        limitSpinner = new javax.swing.JSpinner();
        platformsLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        allPlatformsRadioButton = new javax.swing.JRadioButton();
        ownedPlatformsRadioButton = new javax.swing.JRadioButton();
        minRatingLabel = new javax.swing.JLabel();
        minRatingSpinner = new javax.swing.JSpinner();
        genresLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        genresPanel = new javax.swing.JPanel();
        searchUsersCheckBox = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JSeparator();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        limitLabel.setText("Result Limit:");

        limitSpinner.setModel(new javax.swing.SpinnerNumberModel(30, 1, 500, 5));

        platformsLabel.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        platformsLabel.setText("Platforms");

        platformsButtonGroup.add(allPlatformsRadioButton);
        allPlatformsRadioButton.setSelected(true);
        allPlatformsRadioButton.setText("All platforms");

        platformsButtonGroup.add(ownedPlatformsRadioButton);
        ownedPlatformsRadioButton.setText("Only owned platforms");

        minRatingLabel.setText("Minimun rating:");

        minRatingSpinner.setModel(new javax.swing.SpinnerNumberModel(80, 0, 100, 5));

        genresLabel.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        genresLabel.setText("Genres");

        genresPanel.setLayout(new javax.swing.BoxLayout(genresPanel, javax.swing.BoxLayout.Y_AXIS));

        searchUsersCheckBox.setText("Search Users");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(genresPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ownedPlatformsRadioButton)
                            .addComponent(allPlatformsRadioButton)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(platformsLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genresLabel, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchUsersCheckBox)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(limitLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(limitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(minRatingLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(minRatingSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(searchUsersCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limitLabel)
                    .addComponent(limitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minRatingLabel)
                    .addComponent(minRatingSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(platformsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allPlatformsRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ownedPlatformsRadioButton)
                .addGap(18, 18, 18)
                .addComponent(genresLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genresPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public String getLimitSpinner() {
        return this.limitSpinner.getValue().toString();
    }

    public String getMinRatingSpinner() {
        return this.minRatingSpinner.getValue().toString();
    }

    public boolean isAllPlatformsSelected() {
        return this.allPlatformsRadioButton.isSelected();
    }

    public void addGenre(String genreName) {
        JCheckBox genreCheckBox = new JCheckBox(genreName);
        this.genresPanel.add(genreCheckBox);
    }

    public String getSelectedGenres() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        Component[] components = this.genresPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) components[i];
                if (checkBox.isSelected()) {
                    if (sb.length() > 1) { // Añade coma si no es el primer elemento
                        sb.append(",");
                    }
                    sb.append("\"").append(checkBox.getText()).append("\"");
                }
            }
        }
        sb.append(")");

        if (sb.toString().equals("()")) {
            return null;
        }

        return sb.toString();
    }

    public boolean isSearchUsersSelected() {
        return this.searchUsersCheckBox.isSelected();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton allPlatformsRadioButton;
    private javax.swing.JLabel genresLabel;
    private javax.swing.JPanel genresPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel limitLabel;
    private javax.swing.JSpinner limitSpinner;
    private javax.swing.JLabel minRatingLabel;
    private javax.swing.JSpinner minRatingSpinner;
    private javax.swing.JRadioButton ownedPlatformsRadioButton;
    private javax.swing.ButtonGroup platformsButtonGroup;
    private javax.swing.JLabel platformsLabel;
    private javax.swing.JCheckBox searchUsersCheckBox;
    // End of variables declaration//GEN-END:variables
}
