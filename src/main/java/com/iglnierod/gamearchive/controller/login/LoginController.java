/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller.login;

import com.iglnierod.gamearchive.controller.register.RegisterController;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author rodri
 */
public class LoginController {
    private final LoginFrame view;
    
    public LoginController(LoginFrame view) {
        this.view = view;
        this.view.addRegisterLabelMouseListener(this.setRegisterLabelMouseListener());
    }
    
    private MouseListener setRegisterLabelMouseListener() {
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                RegisterFrame registerFrame = new RegisterFrame();
                RegisterController registerController = new RegisterController(registerFrame);
                registerFrame.setVisible(true);
            }
        };
        return listener;
    }
}
