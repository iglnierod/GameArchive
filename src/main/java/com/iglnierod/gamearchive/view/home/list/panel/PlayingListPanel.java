/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.iglnierod.gamearchive.view.home.list.panel;

import java.awt.event.MouseListener;
/**
 *
 * @author iglnierod
 */
public class PlayingListPanel extends javax.swing.JPanel {

    /**
     * Creates new form ListPreview
     */
    public PlayingListPanel() {
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

        iconLabel = new javax.swing.JLabel();
        createLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 153));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));
        setForeground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMaximumSize(new java.awt.Dimension(150, 188));
        setMinimumSize(new java.awt.Dimension(150, 188));
        setPreferredSize(new java.awt.Dimension(150, 188));

        iconLabel.setFont(new java.awt.Font("Liberation Sans", 1, 17)); // NOI18N
        iconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/videogame_asset_24dp.png"))); // NOI18N
        iconLabel.setToolTipText("");
        iconLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        createLabel.setBackground(new java.awt.Color(255, 255, 255));
        createLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        createLabel.setForeground(new java.awt.Color(255, 255, 255));
        createLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createLabel.setText("Playing");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(createLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(iconLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createLabel)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addMouseListeners(MouseListener l) {
        this.createLabel.addMouseListener(l);
        this.iconLabel.addMouseListener(l);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel createLabel;
    private javax.swing.JLabel iconLabel;
    // End of variables declaration//GEN-END:variables
}
