/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.register;

import com.iglnierod.gamearchive.controller.login.LoginController;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author rodri
 */
public class RegisterController {
    private final RegisterFrame view;

    public RegisterController(RegisterFrame view) {
        this.view = view;
        this.view.addLoginLabelMouseListener(this.setLoginLabelMouseListener());
    }
    
    private MouseListener setLoginLabelMouseListener() {
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                LoginFrame loginFrame = new LoginFrame();
                LoginController loginController = new LoginController(loginFrame);
                loginFrame.setVisible(true);
            }
        };
        return listener;
    }
}
