/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.main;

import com.formdev.flatlaf.FlatDarkLaf;
import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import javax.swing.UIManager;

/**
 *
 * @author rodri
 */
public class Main {

    public static void main(String[] args) {
        changeViewsSkin();
        RegisterFrame registerFrame = new RegisterFrame();
        LoginFrame loginFrame = new LoginFrame();
        MainController mainController = new MainController(registerFrame, loginFrame);
    }

    private static void changeViewsSkin() {
        FlatDarkLaf.setup();
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
