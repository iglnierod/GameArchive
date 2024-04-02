/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.controller;

import com.iglnierod.gamearchive.controller.register.RegisterController;
import com.iglnierod.gamearchive.view.login.LoginFrame;
import com.iglnierod.gamearchive.view.register.RegisterFrame;

/**
 *
 * @author rodri
 */
public class MainController {

    private final RegisterFrame registerView;
    private final LoginFrame loginView;
    
    public MainController(RegisterFrame registerFrame, LoginFrame loginFrame) {
        this.registerView = registerFrame;
        this.loginView = loginFrame;
        this.showRegisterView();
    }
    
    private void showRegisterView() {
        RegisterController registerController = new RegisterController(registerView);
        this.registerView.setVisible(true);
    }
}
