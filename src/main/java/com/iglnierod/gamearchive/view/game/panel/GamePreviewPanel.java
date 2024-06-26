/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.iglnierod.gamearchive.view.game.panel;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author iglnierod
 */
public class GamePreviewPanel extends javax.swing.JPanel {

    /**
     * Creates new form GamePreview
     */
    private final int gameId;
    private boolean favourite;
    
    public GamePreviewPanel() {
        this(-1, false);
    }

    public GamePreviewPanel(int gameId, boolean favourite) {
        this.gameId = gameId;
        this.favourite = favourite;
        initComponents();
        markFavourite(this.favourite);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        coverLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        addToButton = new javax.swing.JButton();
        rateButton = new javax.swing.JButton();
        favouriteButton = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        scrollPane = new javax.swing.JScrollPane();
        summaryTextArea = new javax.swing.JTextArea();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(80, 82, 84), 1, true));
        setForeground(new java.awt.Color(221, 221, 221));
        setMaximumSize(new java.awt.Dimension(675, 156));
        setMinimumSize(new java.awt.Dimension(675, 156));

        coverLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NoImgAvaliable.png"))); // NOI18N

        nameLabel.setFont(new java.awt.Font("Liberation Sans", 1, 17)); // NOI18N
        nameLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        nameLabel.setFocusable(false);
        nameLabel.setMaximumSize(new java.awt.Dimension(30, 150));
        nameLabel.setMinimumSize(new java.awt.Dimension(30, 150));
        nameLabel.setPreferredSize(new java.awt.Dimension(30, 150));

        addToButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/round_queue_white_24dp.png"))); // NOI18N

        rateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/round_thumbs_up_down_white_24dp.png"))); // NOI18N

        favouriteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/round_star_white_24dp.png"))); // NOI18N
        favouriteButton.setToolTipText("Add to favourite");

        separator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        summaryTextArea.setColumns(20);
        summaryTextArea.setLineWrap(true);
        summaryTextArea.setRows(5);
        summaryTextArea.setEnabled(false);
        summaryTextArea.setFocusable(false);
        scrollPane.setViewportView(summaryTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(coverLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(favouriteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addToButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(coverLabel))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(favouriteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addToButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setNameLabelText(String text) {
        this.nameLabel.setText(text);
        this.nameLabel.setToolTipText(text);
    }

    public void setSummaryTextAreaText(String text) {
        this.summaryTextArea.setText(text);
    }

    public void setCoverLabelIcon(Icon icon) {
        this.coverLabel.setIcon(icon);
        this.coverLabel.revalidate();
    }

    public int getGameId() {
        return this.gameId;
    }

    // ======= BUTTONS ======= 
    public void addFavouriteButtonActionListener(ActionListener listener) {
        this.favouriteButton.addActionListener(listener);
    }

    public void addAddToButtonActionListener(ActionListener listener) {
        this.addToButton.addActionListener(listener);
    }

    public void addRateButtonActionListener(ActionListener listener) {
        this.rateButton.addActionListener(listener);
    }

    // ====== VIEW GAME INFORMATION ======
    public void addPanelMouseListener(MouseListener listener) {
        this.addMouseListener(listener);
        this.nameLabel.addMouseListener(listener);
    }
    
    public void markFavourite(boolean fav) {
        ImageIcon notFavIcon = new ImageIcon(getClass().getResource("/icons/round_star_white_24dp.png"));
        ImageIcon favIcon = new ImageIcon(getClass().getResource("/icons/yellow_star_24dp.png"));
        if (fav) {
            favouriteButton.setIcon(favIcon);
        } else {
            favouriteButton.setIcon(notFavIcon);
        }
        this.setFavourite(fav);
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToButton;
    private javax.swing.JLabel coverLabel;
    private javax.swing.JButton favouriteButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton rateButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JSeparator separator;
    private javax.swing.JTextArea summaryTextArea;
    // End of variables declaration//GEN-END:variables
}
