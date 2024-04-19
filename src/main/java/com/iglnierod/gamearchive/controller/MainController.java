/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller;

import com.iglnierod.gamearchive.controller.login.LoginController;
import com.iglnierod.gamearchive.controller.register.RegisterController;
import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.session.Session;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author rodri
 */
public class MainController {

    public static File savedSession = new File("session.bin");

    private final RegisterFrame registerView;
    private final LoginFrame loginView;
    private final Database database;

    public MainController(RegisterFrame registerFrame, LoginFrame loginFrame, Database database) {
        this.registerView = registerFrame;
        this.loginView = loginFrame;
        this.database = database;
        start();
    }

    private void start() {
        if (isSessionSaved()) {
            showLoginView(false);
        } else {
            showRegisterView();
        }
    }

    private boolean isSessionSaved() {
        if (!savedSession.exists()) {
            return false;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedSession))) {
            Client currentClient = (Client) ois.readObject();
            Session.setCurrentClient(currentClient);
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    private void showRegisterView() {
        RegisterController registerController = new RegisterController(registerView, database);
        this.registerView.setVisible(true);
    }

    private void showLoginView(boolean visible) {
        LoginController loginController = new LoginController(loginView, database, true);
        this.loginView.setVisible(visible);
    }

    public static Image getIconImage() {
        String project = new File("").getAbsolutePath();
        return new ImageIcon(project + "\\src\\main\\resources\\icon-256.png").getImage();
    }

}
