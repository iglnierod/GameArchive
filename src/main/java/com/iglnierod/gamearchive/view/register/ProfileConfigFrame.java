/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.iglnierod.gamearchive.view.register;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JToggleButton;

/**
 *
 * @author rodri
 */
public class ProfileConfigFrame extends javax.swing.JFrame {

    /**
     * Creates new form ProfileConfigFrame
     */
    public ProfileConfigFrame() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        continueButton = new javax.swing.JButton();
        imagePanel = new javax.swing.JPanel();
        selectImageLabel = new javax.swing.JLabel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        addDescriptionLabel = new javax.swing.JLabel();
        platformsPanel = new javax.swing.JPanel();
        selectPlatformsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleLabel.setText("Now, lets customize your profile");

        continueButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        continueButton.setText("Continue");

        imagePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        selectImageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        selectImageLabel.setText("Select image");

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(selectImageLabel)
                .addGap(57, 57, 57))
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(selectImageLabel)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setText("I'm new to GameArchive!");
        descriptionScrollPane.setViewportView(descriptionTextArea);

        addDescriptionLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addDescriptionLabel.setText("Add a description");

        platformsPanel.setMaximumSize(new java.awt.Dimension(200, 200));
        platformsPanel.setMinimumSize(new java.awt.Dimension(200, 200));
        platformsPanel.setPreferredSize(new java.awt.Dimension(200, 200));

        selectPlatformsLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        selectPlatformsLabel.setText("Select your platforms");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selectPlatformsLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addDescriptionLabel)))
                    .addComponent(titleLabel)
                    .addComponent(platformsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(166, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(continueButton)
                .addGap(424, 424, 424))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addDescriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(selectPlatformsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(platformsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(continueButton)
                .addGap(42, 42, 42))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void addPlatformToPanel(JToggleButton platformToggleButton) {
        this.platformsPanel.add(platformToggleButton);
    }

    public JToggleButton getPlatformToggleButton(String text) {
        JToggleButton toggleButton = new JToggleButton(text);
        toggleButton.setPreferredSize(new Dimension(150, 50));
        toggleButton.setFont(new Font(toggleButton.getFont().getName(), Font.PLAIN, 16));
        return toggleButton;
    }

    public void addMouseListenerImagePanel(MouseListener listener) {
        this.imagePanel.addMouseListener(listener);
    }

    public void addContinueButtonActionListener(ActionListener listener) {
        this.continueButton.addActionListener(listener);
    }

    public String getDescription() {
        return this.descriptionTextArea.getText().trim();
    }

    public ArrayList<Integer> getSelectedPlatforms() {
        // TODO
        Object[] platforms = platformsPanel.getComponents();
        ArrayList<Integer> selectedPlatforms = new ArrayList<Integer>();
        for (Object o : platforms) {
            JToggleButton platformToggleButton = (JToggleButton) o;
            if (platformToggleButton.isSelected()) {
                int platformIndex = platformsPanel.getComponentZOrder(platformToggleButton);
                selectedPlatforms.add(platformIndex);
            }
        }
        return selectedPlatforms;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addDescriptionLabel;
    private javax.swing.JButton continueButton;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JPanel platformsPanel;
    private javax.swing.JLabel selectImageLabel;
    private javax.swing.JLabel selectPlatformsLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
