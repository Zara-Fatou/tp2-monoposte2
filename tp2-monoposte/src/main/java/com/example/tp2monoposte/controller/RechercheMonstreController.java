package com.example.tp2monoposte.controller;

import com.example.tp2monoposte.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class RechercheMonstreController {
    @FXML public ImageView logoMonstre;
    @FXML public Button btnDeconnexion;
    @FXML public TextField nomField;
    @FXML public TextField familleField;
    @FXML public TextField armeField;
    @FXML public Spinner pvSpinner;
    @FXML public Button btnAjouter;
    @FXML public Button btnEffacerForm;
    @FXML public TableView tableMonstres;
    @FXML public TableColumn colNom;
    @FXML public TableColumn colFamille;
    @FXML public TableColumn colArme;
    @FXML public TableColumn colPv;
    @FXML public Button btnQuitter;

    @FXML
    private void onAjouter(ActionEvent e) {
        System.out.println("Ajout du monstre : " + nomField.getText());
        // Ici tu pourrais ajouter une logique pour insérer dans la TableView
    }

    @FXML
    private void onEffacerForm(ActionEvent e) {
        nomField.clear();
        familleField.clear();
        armeField.clear();
        pvSpinner.getValueFactory().setValue(0);
    }

    @FXML
    private void onQuitter(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    private void onDeconnexion(ActionEvent e) {
        try {
            MainApplication.showLogin();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
