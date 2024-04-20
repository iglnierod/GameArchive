/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.main;

import com.formdev.flatlaf.FlatDarkLaf;
import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import javax.swing.UIManager;

/**
 *
 * @author rodri
 */
public class Main {

    public static void main(String[] args) {
        changeViewSkin();
        
        // TESTING
        /*GameDAO gameDao = new GameDAOUnirest();
        gameDao.search("Hollow Knight");
        System.out.println("Reference: " + Reference.getImage(ImageType._1080P, "co1rgi"));
        System.out.println("Reference: " + Reference.getImage(ImageType._720P, "co1rgi"));
        System.out.println("Reference: " + Reference.getImage(ImageType.COVER_BIG, "co1rgi"));*/

        RegisterFrame registerFrame = new RegisterFrame();
        LoginFrame loginFrame = new LoginFrame();
        Database db = new Database();
        MainController mainController = new MainController(registerFrame, loginFrame, db);
    }

    private static void changeViewSkin() {
        FlatDarkLaf.setup();
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
