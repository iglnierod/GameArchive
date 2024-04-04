/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.register;

import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.controller.login.LoginController;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.user.User;
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
public class RegisterController {
    
    private final RegisterFrame view;
    private final Database database;
    private UserDAO userDao;
    
    public RegisterController(RegisterFrame view, Database database) {
        this.view = view;
        this.view.setIconImage(MainController.getIconImage());
        this.database = database;
        this.userDao = new UserDAOPostgreSQL(database);
        setListeners();
    }
    
    private void setListeners() {
        this.view.addLoginLabelMouseListener(setLoginLabelMouseListener());
        this.view.addRegisterButtonActionListener(setRegisterButtonActionListener());
    }
    
    private MouseListener setLoginLabelMouseListener() {
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                LoginFrame loginFrame = new LoginFrame();
                LoginController loginController = new LoginController(loginFrame, database);
                loginFrame.setVisible(true);
            }
        };
        return listener;
    }
    
    private ActionListener setRegisterButtonActionListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Register button action listener");
                String username = view.getUsernameText();
                String email = view.getEmailText();
                String password = view.getPasswordText();
                
                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Make sure to fill all text fields",
                            "ERROR: Empty field", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                userDao.add(new User(username, email, password));
            }
        };
        return listener;
    }
}
