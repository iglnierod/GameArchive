/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.register;

import com.iglnierod.gamearchive.controller.MainController;
import com.iglnierod.gamearchive.controller.login.LoginController;
import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.client.dao.ClientDAO;
import com.iglnierod.gamearchive.model.client.dao.ClientDAOPostgreSQL;
import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.list.List;
import com.iglnierod.gamearchive.model.list.dao.ListDAO;
import com.iglnierod.gamearchive.model.list.dao.ListDAOPostgreSQL;
import com.iglnierod.gamearchive.model.session.Session;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.ProfileConfigFrame;
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
    private ClientDAO clientDao;
    private Client client;

    public RegisterController(RegisterFrame view, Database database) {
        this.view = view;
        this.view.setIconImage(MainController.getIconImage());
        this.database = database;
        this.clientDao = new ClientDAOPostgreSQL(database);
        setListeners();
    }

    private void setListeners() {
        this.view.addLoginLabelMouseListener(setLoginLabelMouseListener());
        this.view.addRegisterButtonActionListener(setRegisterButtonActionListener());
    }

    private MouseListener setLoginLabelMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginFrame loginFrame = new LoginFrame();
                LoginController loginController = new LoginController(loginFrame, database);
                view.dispose();
                loginFrame.setVisible(true);
            }
        };
    }

    private ActionListener setRegisterButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getUsernameText();
                String email = view.getEmailText();
                String password = view.getPasswordText();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Make sure to fill all text fields",
                            "ERROR: Empty field", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                client = new Client(username, email, password);
                if (!clientDao.add(client)) {
                    JOptionPane.showMessageDialog(view, "Username is already taken. Try a different one.",
                            "ERROR: Username already taken", JOptionPane.ERROR_MESSAGE);
                    view.setFocusUsernameTextField();
                    return;
                }

                Session.setCurrentClient(client);
                createFavouriteList();
                showConfig();
            }
        };
    }

    private void createFavouriteList() {
        List favouriteList = new List("Favourite", String.format("%s's favourite games!!",client.getUsername()));
        ListDAO listDao = new ListDAOPostgreSQL(database);
        listDao.createFavourite(favouriteList);
    }
    
    private void showConfig() {
        ProfileConfigFrame profileConfigFrame = new ProfileConfigFrame();
        ProfileConfigController profileConfigController = new ProfileConfigController(profileConfigFrame, database);
        view.setVisible(false);
        profileConfigFrame.setVisible(true);
    }

}
