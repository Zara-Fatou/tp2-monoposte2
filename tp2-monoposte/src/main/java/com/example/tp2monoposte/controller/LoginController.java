package com.example.tp2monoposte.controller;

import com.example.tp2monoposte.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class LoginController {

    public Button btnLogin;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void initialize() {
        errorLabel.setVisible(false);
    }

    @FXML
    private void onLoginClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("zaraf".equals(username) && "1234.".equals(password)) {
            try {
                MainApplication.showGestionMonstre();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorLabel.setText("Nom d'utilisateur ou mot de passe invalide !");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void onCancelClicked() {
        usernameField.clear();
        passwordField.clear();
        errorLabel.setVisible(false);
    }



}
