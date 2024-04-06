/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.login;

import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.controller.home.HomeController;
import com.iglnierod.gamearchive.controller.register.RegisterController;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.user.dao.UserDAO;
import com.iglnierod.gamearchive.model.user.dao.UserDAOPostgreSQL;
import com.iglnierod.gamearchive.view.home.HomeFrame;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author rodri
 */
public class LoginController {

    private final LoginFrame view;
    private final Database database;
    private UserDAO userDao;

    public LoginController(LoginFrame view, Database database) {
        this.view = view;
        this.view.setIconImage(MainController.getIconImage());
        this.database = database;
        this.userDao = new UserDAOPostgreSQL(database);
        setListeners();
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

                HomeFrame homeFrame = new HomeFrame();
                HomeController homeController = new HomeController(homeFrame, database);
                view.dispose();
                homeFrame.setVisible(true);
            }
        };
    }

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
