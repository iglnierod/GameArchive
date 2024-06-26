/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.iglnierod.gamearchive.view.client;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.ListModel;

/**
 *
 * @author iglnierod
 */
public class ClientPanel extends javax.swing.JPanel {

    /**
     * Creates new form ClientPanel
     */
    public ClientPanel() {
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

        mainTabbedPane = new javax.swing.JTabbedPane();
        informationPanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        joinedOnLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        platformsList = new javax.swing.JList<>();
        ownedPlatforms = new javax.swing.JLabel();
        joinedOnTextField = new javax.swing.JTextField();
        configureButton = new javax.swing.JButton();
        listsPanel = new javax.swing.JPanel();
        ratingsScrollPane = new javax.swing.JScrollPane();
        ratingsPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        activityPanel = new javax.swing.JPanel();
        northPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1200, 660));
        setMinimumSize(new java.awt.Dimension(1200, 660));
        setName(""); // NOI18N
        setLayout(new java.awt.BorderLayout());

        usernameLabel.setText("Username:");

        usernameTextField.setEnabled(false);

        descriptionLabel.setText("Description:");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setEnabled(false);
        jScrollPane1.setViewportView(descriptionTextArea);

        joinedOnLabel.setText("Joined on:");

        platformsList.setEnabled(false);
        jScrollPane2.setViewportView(platformsList);

        ownedPlatforms.setText("Owned Platforms");

        joinedOnTextField.setEnabled(false);

        configureButton.setText("Configure");

        javax.swing.GroupLayout informationPanelLayout = new javax.swing.GroupLayout(informationPanel);
        informationPanel.setLayout(informationPanelLayout);
        informationPanelLayout.setHorizontalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addContainerGap(375, Short.MAX_VALUE)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informationPanelLayout.createSequentialGroup()
                        .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(informationPanelLayout.createSequentialGroup()
                                .addComponent(joinedOnLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(joinedOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(174, 174, 174))
                            .addGroup(informationPanelLayout.createSequentialGroup()
                                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(descriptionLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ownedPlatforms, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(usernameTextField)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(425, 425, 425))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informationPanelLayout.createSequentialGroup()
                        .addComponent(configureButton)
                        .addGap(515, 515, 515))))
        );
        informationPanelLayout.setVerticalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(joinedOnLabel)
                    .addComponent(joinedOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ownedPlatforms))
                .addGap(18, 18, 18)
                .addComponent(configureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Information", informationPanel);

        listsPanel.setLayout(new java.awt.BorderLayout());
        mainTabbedPane.addTab("Lists", listsPanel);

        ratingsScrollPane.setBorder(null);

        ratingsPanel.setLayout(new javax.swing.BoxLayout(ratingsPanel, javax.swing.BoxLayout.PAGE_AXIS));
        ratingsScrollPane.setViewportView(ratingsPanel);

        mainTabbedPane.addTab("Ratings", ratingsScrollPane);

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        activityPanel.setLayout(new javax.swing.BoxLayout(activityPanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane3.setViewportView(activityPanel);

        mainTabbedPane.addTab("Activity", jScrollPane3);

        add(mainTabbedPane, java.awt.BorderLayout.CENTER);

        titleLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("<username> Profile");

        javax.swing.GroupLayout northPanelLayout = new javax.swing.GroupLayout(northPanel);
        northPanel.setLayout(northPanelLayout);
        northPanelLayout.setHorizontalGroup(
            northPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        northPanelLayout.setVerticalGroup(
            northPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        add(northPanel, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    public void setTitleLabelText(String title) {
        this.titleLabel.setText(title);
    }

    // INFORMATION
    public void setJoinedOnTextFieldText(String joinedOn) {
        this.joinedOnTextField.setText(joinedOn);
    }

    public void setUsernameTextFieldText(String username) {
        this.usernameTextField.setText(username);
    }

    public void setDescriptionTextAreaText(String description) {
        this.descriptionTextArea.setText(description);
    }

    public void setPlatformsListModel(ListModel<String> platforms) {
        this.platformsList.setModel(platforms);
    }

    public void setConfigureButtonVisible(boolean visible) {
        this.configureButton.setVisible(visible);
    }

    public void setConfigureButtonActionListener(ActionListener l) {
        this.configureButton.addActionListener(l);
    }

    // LISTS
    public void addListsPanel(JPanel pnl) {
        this.listsPanel.add(pnl, BorderLayout.CENTER);
    }

    
    // RATINGS
    public void addRating(JPanel pnl) {
        this.ratingsPanel.add(pnl);
    }
    
    public void emptyRatings() {
        this.ratingsPanel.removeAll();
    }
    
    // ACTIVITY
    public void addActivity(JPanel pnl) {
        this.activityPanel.add(pnl);
    }
    
    public void emptyActivity() {
        this.activityPanel.removeAll();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel activityPanel;
    private javax.swing.JButton configureButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel joinedOnLabel;
    private javax.swing.JTextField joinedOnTextField;
    private javax.swing.JPanel listsPanel;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JPanel northPanel;
    private javax.swing.JLabel ownedPlatforms;
    private javax.swing.JList<String> platformsList;
    private javax.swing.JPanel ratingsPanel;
    private javax.swing.JScrollPane ratingsScrollPane;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
