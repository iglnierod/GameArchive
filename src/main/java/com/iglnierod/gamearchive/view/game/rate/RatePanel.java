/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.iglnierod.gamearchive.view.game.rate;

import java.awt.Color;
import java.awt.event.MouseListener;

/**
 *
 * @author iglnierod
 */
public class RatePanel extends javax.swing.JPanel {

    /**
     * Creates new form RatePanel
     */
    public RatePanel() {
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

        usernameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        commentTextArea = new javax.swing.JTextArea();
        ratingProgressBar = new javax.swing.JProgressBar();
        ratingLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 10, 1));
        setMaximumSize(new java.awt.Dimension(560, 161));
        setMinimumSize(new java.awt.Dimension(560, 161));
        setPreferredSize(new java.awt.Dimension(560, 161));

        usernameLabel.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        usernameLabel.setText("username");

        commentTextArea.setEditable(false);
        commentTextArea.setColumns(20);
        commentTextArea.setLineWrap(true);
        commentTextArea.setRows(5);
        commentTextArea.setEnabled(false);
        jScrollPane1.setViewportView(commentTextArea);

        ratingProgressBar.setForeground(new java.awt.Color(51, 153, 0));
        ratingProgressBar.setToolTipText("");
        ratingProgressBar.setValue(50);

        ratingLabel.setFont(new java.awt.Font("Liberation Sans", 1, 17)); // NOI18N
        ratingLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ratingLabel.setText("50");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ratingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ratingProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ratingLabel)
                        .addComponent(ratingProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setUsernameText(String username) {
        this.usernameLabel.setText(username);
    }
    
    public void setRating(int rating) {
        this.ratingLabel.setText(String.valueOf(rating));
        this.ratingProgressBar.setValue(rating);
        if(rating < 40) {
            this.ratingProgressBar.setForeground(Color.RED);
        } else if(rating >= 40 && rating <= 60) {
            this.ratingProgressBar.setForeground(Color.ORANGE);
        } else {
            this.ratingProgressBar.setForeground(Color.GREEN);
        }
    }
    
    public void setCommentText(String comment) {
        this.commentTextArea.setText(comment);
    }
    
    public void addUsernameLabelMouseListener(MouseListener ml) {
        this.usernameLabel.addMouseListener(ml);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea commentTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JProgressBar ratingProgressBar;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}