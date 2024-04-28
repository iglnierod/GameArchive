/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.iglnierod.gamearchive.view.home.search;

/**
 *
 * @author iglnierod
 */
public class NoGamesFoundPanel extends javax.swing.JPanel {

    /**
     * Creates new form NoGamesFoundPanel
     */
    public NoGamesFoundPanel() {
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

        noGamesFoundLabel = new javax.swing.JLabel();

        setFocusCycleRoot(true);
        setMaximumSize(new java.awt.Dimension(675, 156));
        setMinimumSize(new java.awt.Dimension(675, 156));

        noGamesFoundLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        noGamesFoundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/round_error_white_24dp.png"))); // NOI18N
        noGamesFoundLabel.setText("No games found for input:");
        noGamesFoundLabel.setMaximumSize(new java.awt.Dimension(675, 156));
        noGamesFoundLabel.setMinimumSize(new java.awt.Dimension(675, 156));
        noGamesFoundLabel.setPreferredSize(new java.awt.Dimension(675, 156));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(noGamesFoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(noGamesFoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setInput(String input) {
        this.noGamesFoundLabel.setText("No games found for input: '" + input + "'");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel noGamesFoundLabel;
    // End of variables declaration//GEN-END:variables
}