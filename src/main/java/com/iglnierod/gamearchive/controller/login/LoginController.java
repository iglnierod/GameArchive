/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.login;

import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.controller.register.RegisterController;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.user.dao.UserDAO;
import com.iglnierod.gamearchive.model.user.dao.UserDAOPostgreSQL;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.view.addRegisterLabelMouseListener(this.setRegisterLabelMouseListener());
        this.view.addLoginButtonActionListener(setLoginButtonActionListener());
    }

    private ActionListener setLoginButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login button action listener");
                String username = view.getUsernameText();
                String password = view.getPasswordText();

                System.out.println("password: " + password);

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Make sure to fill all text fields",
                            "ERROR: Empty field", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean res = userDao.login(username, password);
                System.out.println("login: " + res);
                if (res) {
                    JOptionPane.showMessageDialog(view, "LOGIN CORRECTO");
                }
            }
        };
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
