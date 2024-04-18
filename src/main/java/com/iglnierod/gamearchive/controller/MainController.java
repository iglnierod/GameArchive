/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller;

import com.iglnierod.gamearchive.controller.register.RegisterController;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.session.Session;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author rodri
 */
public class MainController {

    private final RegisterFrame registerView;
    private final LoginFrame loginView;
    private final Database database;
    
    public MainController(RegisterFrame registerFrame, LoginFrame loginFrame, Database database) {
        this.registerView = registerFrame;
        this.loginView = loginFrame;
        this.database = database;
        this.showRegisterView();
    }
    
    private void showRegisterView() {
        RegisterController registerController = new RegisterController(registerView, database);
        this.registerView.setVisible(true);
    }
    
    public static Image getIconImage() {
        String project = new File("").getAbsolutePath();
        return new ImageIcon(project + "\\src\\main\\resources\\icon-256.png").getImage();
    }
}
