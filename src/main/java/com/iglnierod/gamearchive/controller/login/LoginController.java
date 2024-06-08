/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.login;

import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.controller.home.HomeController;
import com.iglnierod.gamearchive.controller.register.RegisterController;
import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.client.dao.ClientDAO;
import com.iglnierod.gamearchive.model.client.dao.ClientDAOPostgreSQL;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.session.Session;
import com.iglnierod.gamearchive.view.home.HomeFrame;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author rodri
 */
public class LoginController {

    private final LoginFrame view;
    private final Database database;
    private ClientDAO userDao;
    private boolean savedSession;

    public LoginController(LoginFrame view, Database database, boolean savedSession) {
        this.view = view;
        this.database = database;
        this.userDao = new ClientDAOPostgreSQL(database);
        this.savedSession = savedSession;
        setListeners();
        isSessionSaved();
    }

    private void isSessionSaved() {
        if (savedSession) {
            navigateToHome();
        }
    }

    public LoginController(LoginFrame view, Database database) {
        this(view, database, false);
    }

    private void setListeners() {
        this.view.addRegisterLabelMouseListener(setRegisterLabelMouseListener());
        this.view.addLoginButtonActionListener(setLoginButtonActionListener());
    }

    private ActionListener setLoginButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!loginUser()) {
                    JOptionPane.showMessageDialog(view, "Invalid login",
                            "ERROR: Invalid login", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Set user as current user
                Client currentClient = userDao.getClient(view.getUsernameText());
                Session.setCurrentClient(currentClient);

                if (view.isSaveLoginSelected()) {
                    saveSession();
                }

                // Go to Home frame
                navigateToHome();
            }

        };
    }

    private void navigateToHome() {
        HomeFrame homeFrame = new HomeFrame();
        HomeController homeController = new HomeController(homeFrame, database);
        view.dispose();
        homeFrame.setVisible(true);
    }

    private void saveSession() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MainController.savedSession))) {
            oos.writeObject(Session.getCurrentClient());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // TODO: Mostrar solo un error al fallar login + campo vacio
    private boolean loginUser() {
        String username = view.getUsernameText();
        String password = view.getPasswordText();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Make sure to fill all text fields",
                    "ERROR: Empty field", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return userDao.login(username, password);
    }

    private MouseListener setRegisterLabelMouseListener() {
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                RegisterFrame registerFrame = new RegisterFrame();
                RegisterController registerController = new RegisterController(registerFrame, database);
                registerFrame.setVisible(true);
            }
        };
        return listener;
    }
}
