/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.iglnierod.gamearchive.view.game.panel;

import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

/**
 *
 * @author iglnierod
 */
public class GameCoverPanel extends javax.swing.JPanel {

    /**
     * Creates new form GameCoverPanel
     */
    // 165, 225
    public static final int WIDTH = 165;
    public static final int HEIGHT = 225;

    private final int gameId;
            
    public GameCoverPanel() {
        this(-1);
    }
    
    public GameCoverPanel(int gameId) {
        this.gameId = gameId;
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

        gameLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(165, 225));
        setMinimumSize(new java.awt.Dimension(165, 225));
        setLayout(new java.awt.BorderLayout());

        gameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        gameLabel.setMaximumSize(new java.awt.Dimension(165, 225));
        gameLabel.setMinimumSize(new java.awt.Dimension(165, 225));
        gameLabel.setPreferredSize(new java.awt.Dimension(165, 225));
        add(gameLabel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public void setGameLabelImageIcon(ImageIcon cover) {
        this.gameLabel.setIcon(cover);
    }
    
    public void addCoverMouseListener(MouseListener l) {
        this.addMouseListener(l);
        this.gameLabel.addMouseListener(l);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel gameLabel;
    // End of variables declaration//GEN-END:variables
}