/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.iglnierod.gamearchive.view.home.list.dialog;

import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

/**
 *
 * @author iglnierod
 */
public class EditListDialog extends javax.swing.JDialog {

    /**
     * Creates new form CreateListDialog
     */
    public EditListDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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
        jPanel1 = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        centerTabbedPane = new javax.swing.JTabbedPane();
        informationPanel = new javax.swing.JPanel();
        nameTextField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        gamesPanel = new javax.swing.JPanel();
        removeButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        gamesList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GameArchive - Create List");
        setResizable(false);

        titleLabel.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Edit List");
        titleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 10, 1));
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(titleLabel, java.awt.BorderLayout.PAGE_START);

        cancelButton.setText("Cancel");
        cancelButton.setMaximumSize(new java.awt.Dimension(74, 24));
        cancelButton.setMinimumSize(new java.awt.Dimension(74, 24));
        cancelButton.setPreferredSize(new java.awt.Dimension(74, 24));

        editButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.background"));
        editButton.setText("Edit");
        editButton.setMaximumSize(new java.awt.Dimension(74, 24));
        editButton.setMinimumSize(new java.awt.Dimension(74, 24));
        editButton.setPreferredSize(new java.awt.Dimension(74, 24));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(461, Short.MAX_VALUE)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel.setText("Name");
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        descriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descriptionLabel.setText("Description");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(descriptionTextArea);

        javax.swing.GroupLayout informationPanelLayout = new javax.swing.GroupLayout(informationPanel);
        informationPanel.setLayout(informationPanelLayout);
        informationPanelLayout.setHorizontalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(descriptionLabel)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameTextField)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
                .addGap(144, 144, 144))
        );
        informationPanelLayout.setVerticalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addGap(18, 18, 18)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        centerTabbedPane.addTab("Information", informationPanel);

        removeButton.setBackground(new java.awt.Color(254, 65, 64));
        removeButton.setText("Remove");
        removeButton.setMaximumSize(new java.awt.Dimension(74, 24));
        removeButton.setMinimumSize(new java.awt.Dimension(74, 24));
        removeButton.setPreferredSize(new java.awt.Dimension(74, 24));

        jScrollPane3.setViewportView(gamesList);

        javax.swing.GroupLayout gamesPanelLayout = new javax.swing.GroupLayout(gamesPanel);
        gamesPanel.setLayout(gamesPanelLayout);
        gamesPanelLayout.setHorizontalGroup(
            gamesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamesPanelLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(gamesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        gamesPanelLayout.setVerticalGroup(
            gamesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamesPanelLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        centerTabbedPane.addTab("Games", gamesPanel);

        getContentPane().add(centerTabbedPane, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public String getNameTextFieldText() {
        return this.nameTextField.getText().trim();
    }

    public void setNameTextFieldText(String name) {
        this.nameTextField.setText(name);
    }

    public String getDescriptionTextAreaText() {
        return this.descriptionTextArea.getText().trim();
    }

    public void setDescriptionTextAreaText(String description) {
        this.descriptionTextArea.setText(description);
    }

    public void addCancelButtonActionListener(ActionListener l) {
        this.cancelButton.addActionListener(l);
    }

    public void addEditButtonActionListener(ActionListener l) {
        this.editButton.addActionListener(l);
    }

    public void addRemoveButtonActionListener(ActionListener l) {
        this.removeButton.addActionListener(l);
    }

    public void setGamesListModel(DefaultListModel model) {
        this.gamesList.setModel(model);
    }

    public int getGamesListSelectedIndex() {
        return this.gamesList.getSelectedIndex();
    }

    public void disableInformationPanel() {
        this.informationPanel.setEnabled(false);
        this.nameTextField.setEnabled(false);
        this.descriptionTextArea.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTabbedPane centerTabbedPane;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JButton editButton;
    private javax.swing.JList<String> gamesList;
    private javax.swing.JPanel gamesPanel;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton removeButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

}
