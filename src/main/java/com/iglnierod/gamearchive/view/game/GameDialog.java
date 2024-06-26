/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.iglnierod.gamearchive.view.game;

import com.iglnierod.gamearchive.view.game.panel.GameCoverPanel;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JToggleButton;

/**
 *
 * @author iglnierod
 */
public class GameDialog extends javax.swing.JDialog {

    private boolean favourite;

    /**
     * Creates new form GameDialog
     */
    public GameDialog(java.awt.Frame parent, boolean modal, boolean favourite) {
        super(parent, modal);
        initComponents();
        this.favourite = favourite;
        markFavourite(this.favourite);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        centerPanel = new javax.swing.JPanel();
        coverLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        buttonsPanel = new javax.swing.JPanel();
        addToListButton = new javax.swing.JButton();
        favouriteButton = new javax.swing.JButton();
        rateButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        similarGamesPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ratingsPanel = new javax.swing.JPanel();
        westPanel = new javax.swing.JPanel();
        ratingProgressBar = new javax.swing.JProgressBar();
        ratingNumberLabel = new javax.swing.JLabel();
        maximumRatingLabel = new javax.swing.JLabel();
        ratingCountLabel = new javax.swing.JLabel();
        genresTitleLabel = new javax.swing.JLabel();
        genresPanel = new javax.swing.JPanel();
        platformsTitleLabel = new javax.swing.JLabel();
        platformsPanel = new javax.swing.JPanel();
        wantButton = new javax.swing.JToggleButton();
        playedButton = new javax.swing.JToggleButton();
        playingButton = new javax.swing.JToggleButton();
        menuBar = new javax.swing.JMenuBar();
        optionsMenu = new javax.swing.JMenu();
        reloadMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        coverLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NoImgAvailable_Large.png"))); // NOI18N

        nameLabel.setFont(new java.awt.Font("Liberation Sans", 1, 17)); // NOI18N
        nameLabel.setText("Game Name");

        descriptionTextArea.setEditable(false);
        descriptionTextArea.setColumns(20);
        descriptionTextArea.setForeground(new java.awt.Color(255, 255, 255));
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setText("<This game does not have a summary>");
        descriptionTextArea.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        descriptionTextArea.setEnabled(false);
        jScrollPane1.setViewportView(descriptionTextArea);

        addToListButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/queue_32dp.png"))); // NOI18N
        addToListButton.setToolTipText("Add to list");
        addToListButton.setMaximumSize(new java.awt.Dimension(46, 38));
        addToListButton.setMinimumSize(new java.awt.Dimension(46, 38));
        addToListButton.setPreferredSize(new java.awt.Dimension(46, 38));

        favouriteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/star_32dp.png"))); // NOI18N
        favouriteButton.setToolTipText("Favourite");

        rateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/thumbs_up_down_32dp.png"))); // NOI18N
        rateButton.setToolTipText("Rate");
        rateButton.setMaximumSize(new java.awt.Dimension(46, 38));
        rateButton.setMinimumSize(new java.awt.Dimension(46, 38));
        rateButton.setPreferredSize(new java.awt.Dimension(46, 38));

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(favouriteButton)
                .addGap(18, 18, 18)
                .addComponent(addToListButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addToListButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(favouriteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Similar games"));
        jScrollPane3.setMaximumSize(new java.awt.Dimension(521, 215));
        jScrollPane3.setMinimumSize(new java.awt.Dimension(521, 215));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(521, 215));

        similarGamesPanel.setMinimumSize(new java.awt.Dimension(521, 215));
        similarGamesPanel.setName(""); // NOI18N
        similarGamesPanel.setLayout(new javax.swing.BoxLayout(similarGamesPanel, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane3.setViewportView(similarGamesPanel);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("GameArchive Ratings"));
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        ratingsPanel.setLayout(new javax.swing.BoxLayout(ratingsPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane4.setViewportView(ratingsPanel);

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(coverLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap())))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(coverLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jScrollPane2.setViewportView(centerPanel);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        ratingProgressBar.setValue(50);

        ratingNumberLabel.setFont(new java.awt.Font("Liberation Sans", 0, 28)); // NOI18N
        ratingNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ratingNumberLabel.setText("No rate");

        maximumRatingLabel.setFont(new java.awt.Font("Liberation Sans", 2, 14)); // NOI18N
        maximumRatingLabel.setText("/100");

        ratingCountLabel.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        ratingCountLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/group_24dp.png"))); // NOI18N
        ratingCountLabel.setText("0");

        genresTitleLabel.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        genresTitleLabel.setText("Genres");

        genresPanel.setMaximumSize(new java.awt.Dimension(215, 32767));
        genresPanel.setMinimumSize(new java.awt.Dimension(215, 34));
        genresPanel.setPreferredSize(new java.awt.Dimension(215, 34));

        platformsTitleLabel.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        platformsTitleLabel.setText("Platforms");

        platformsPanel.setMaximumSize(new java.awt.Dimension(215, 32767));
        platformsPanel.setMinimumSize(new java.awt.Dimension(215, 100));
        platformsPanel.setName(""); // NOI18N
        platformsPanel.setPreferredSize(new java.awt.Dimension(215, 120));

        wantButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/more_time_32dp.png"))); // NOI18N
        wantButton.setText("Want");

        playedButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/videogame_asset_off_32dp.png"))); // NOI18N
        playedButton.setText("Played");

        playingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/videogame_asset_32dp.png"))); // NOI18N
        playingButton.setText("Playing");

        javax.swing.GroupLayout westPanelLayout = new javax.swing.GroupLayout(westPanel);
        westPanel.setLayout(westPanelLayout);
        westPanelLayout.setHorizontalGroup(
            westPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(westPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(westPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, westPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(westPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, westPanelLayout.createSequentialGroup()
                                .addComponent(ratingNumberLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maximumRatingLabel)
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, westPanelLayout.createSequentialGroup()
                                .addGroup(westPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(wantButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ratingCountLabel)
                                    .addComponent(ratingProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(playingButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(playedButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, westPanelLayout.createSequentialGroup()
                        .addGroup(westPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(platformsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(westPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(westPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, westPanelLayout.createSequentialGroup()
                                        .addComponent(platformsTitleLabel)
                                        .addGap(68, 68, 68))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, westPanelLayout.createSequentialGroup()
                                        .addComponent(genresTitleLabel)
                                        .addGap(75, 75, 75))))
                            .addComponent(genresPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        westPanelLayout.setVerticalGroup(
            westPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(westPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(westPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ratingNumberLabel)
                    .addComponent(maximumRatingLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ratingProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ratingCountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wantButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playedButton)
                .addGap(18, 18, 18)
                .addComponent(genresTitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genresPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(platformsTitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(platformsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(westPanel, java.awt.BorderLayout.EAST);

        optionsMenu.setText("Options");

        reloadMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        reloadMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sync_24dp.png"))); // NOI18N
        reloadMenuItem.setText("Reload");
        optionsMenu.add(reloadMenuItem);

        menuBar.add(optionsMenu);

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void setNameLabelText(String name) {
        this.nameLabel.setText(name);
        this.nameLabel.setToolTipText(name);
    }

    public void setCoverLabelIcon(ImageIcon cover) {
        this.coverLabel.setIcon(cover);
    }

    public void setSummaryTextArea(String description) {
        this.descriptionTextArea.setText(description);
    }

    public void setRatingProgressBar(int rating) {
        this.ratingProgressBar.setValue(rating);
        this.ratingNumberLabel.setText(String.valueOf(rating));
    }

    public void setRatingCountLabel(int ratingCount) {
        this.ratingCountLabel.setText(String.valueOf(ratingCount));
    }

    public void addPlatformToggleButton(String platformName) {
        JToggleButton platform = new JToggleButton(platformName);
        platform.setEnabled(false);
        platformsPanel.add(platform);
        platformsPanel.revalidate();
    }

    public void addGenreToggleButton(String genreName) {
        JToggleButton genre = new JToggleButton(genreName);
        genre.setEnabled(false);
        genresPanel.add(genre);
        genresPanel.revalidate();
    }

    public void addAddToListActionListener(ActionListener l) {
        this.addToListButton.addActionListener(l);
    }

    public void addFavourteButtonActionListener(ActionListener l) {
        this.favouriteButton.addActionListener(l);
    }

    public void addRateButtonActionListener(ActionListener l) {
        this.rateButton.addActionListener(l);
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public void markFavourite(boolean fav) {
        ImageIcon notFavIcon = new ImageIcon(getClass().getResource("/icons/star_32dp.png"));
        ImageIcon favIcon = new ImageIcon(getClass().getResource("/icons/yellow_star_32dp.png"));
        if (fav) {
            favouriteButton.setIcon(favIcon);
        } else {
            favouriteButton.setIcon(notFavIcon);
        }
        this.setFavourite(fav);
    }

    public void addSimilarGame(GameCoverPanel gamePanel) {
        this.similarGamesPanel.add(gamePanel);
        this.revalidate();
        this.repaint();
    }

    public void addRating(JComponent rating) {
        this.ratingsPanel.add(rating);
        this.ratingsPanel.revalidate();
        this.ratingsPanel.repaint();
    }

    public void emptyRatings() {
        this.ratingsPanel.removeAll();
    }

    public void addReloadMenuItemActionListener(ActionListener l) {
        this.reloadMenuItem.addActionListener(l);
    }

    // Game status
    public void addWantButtonActionListener(ActionListener l) {
        this.wantButton.addActionListener(l);
    }

    public void setWantButtonSelected(boolean selected) {
        this.wantButton.setSelected(selected);
    }

    public boolean isWantButtonSelected() {
        return wantButton.isSelected();
    }

    public void addPlayingButtonActionListener(ActionListener l) {
        this.playingButton.addActionListener(l);
    }

    public void setPlayingButtonSelected(boolean selected) {
        this.playingButton.setSelected(selected);
    }

    public boolean isPlayingButtonSelected() {
        return playingButton.isSelected();
    }

    public void addPlayedButtonActionListener(ActionListener l) {
        this.playedButton.addActionListener(l);
    }

    public void setPlayedButtonSelected(boolean selected) {
        this.playedButton.setSelected(selected);
    }

    public boolean isPlayedButtonSelected() {
        return playedButton.isSelected();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToListButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel coverLabel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JButton favouriteButton;
    private javax.swing.JPanel genresPanel;
    private javax.swing.JLabel genresTitleLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel maximumRatingLabel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JMenu optionsMenu;
    private javax.swing.JPanel platformsPanel;
    private javax.swing.JLabel platformsTitleLabel;
    private javax.swing.JToggleButton playedButton;
    private javax.swing.JToggleButton playingButton;
    private javax.swing.JButton rateButton;
    private javax.swing.JLabel ratingCountLabel;
    private javax.swing.JLabel ratingNumberLabel;
    private javax.swing.JProgressBar ratingProgressBar;
    private javax.swing.JPanel ratingsPanel;
    private javax.swing.JMenuItem reloadMenuItem;
    private javax.swing.JPanel similarGamesPanel;
    private javax.swing.JToggleButton wantButton;
    private javax.swing.JPanel westPanel;
    // End of variables declaration//GEN-END:variables
}
