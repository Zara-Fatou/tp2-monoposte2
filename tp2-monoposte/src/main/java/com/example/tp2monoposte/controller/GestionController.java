package com.example.tp2monoposte.controller;

import com.example.tp2monoposte.MainApplication;
import com.example.tp2monoposte.modele.Monstre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionController {

    @FXML private TextField nomField;
    @FXML private ComboBox<String> familleCombo;
    @FXML private ComboBox<String> armeCombo;
    @FXML private Spinner<Integer> pvSpinner;
    @FXML private TableView<Monstre> tableMonstres;
    @FXML private TableColumn<Monstre, String> colNom;
    @FXML private TableColumn<Monstre, String> colFamille;
    @FXML private TableColumn<Monstre, String> colArme;
    @FXML private TableColumn<Monstre, Integer> colPv;
    @FXML private Button btnAjouter;
    @FXML private Button btnEffacerForm;
    @FXML private Button btnQuitter;

    private final ObservableList<Monstre> monstres = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Colonnes
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        colFamille.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFamille()));
        colArme.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getArme()));
        colPv.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPointsVie()).asObject());

        tableMonstres.setItems(monstres);

        // ComboBox et Spinner
        familleCombo.getItems().addAll("Orc", "Démon", "Dragon", "Canidé", "Vampire");
        armeCombo.getItems().addAll("Épée", "Griffe", "Magie", "Dent", "Feu");
        pvSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 50));

        //Ajout de 5 monstres de base
        monstres.addAll(
                new Monstre("Gorgo", "Démon", "Feu", 320),
                new Monstre("Ragnar", "Orc", "Épée", 250),
                new Monstre("Volkan", "Dragon", "Magie", 450),
                new Monstre("Fenrir", "Canidé", "Dent", 180),
                new Monstre("Nosfer", "Vampire", "Griffe", 275)
        );
        btnAjouter.disableProperty().bind(
                nomField.textProperty().isEmpty()
                        .or(familleCombo.valueProperty().isNull())
                        .or(armeCombo.valueProperty().isNull())
        );

    }

    @FXML
    private void onAjouter(ActionEvent e) {
        String nom = nomField.getText();
        String famille = familleCombo.getValue();
        String arme = armeCombo.getValue();
        int pv = pvSpinner.getValue();

        if (nom.isEmpty() || famille == null || arme == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs avant d’ajouter un monstre.");
            alert.showAndWait();
            return;
        }

        Monstre nouveau = new Monstre(nom, famille, arme, pv);
        monstres.add(nouveau);
        effacerFormulaire();

        // 🔁 Ouvrir la fenêtre de recherche après ajout
        try {
            MainApplication.showRechercheMonstre();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    private void onEffacerForm(ActionEvent e) {
        effacerFormulaire();
    }

    private void effacerFormulaire() {
        nomField.clear();
        familleCombo.getSelectionModel().clearSelection();
        armeCombo.getSelectionModel().clearSelection();
        pvSpinner.getValueFactory().setValue(50);
    }

    @FXML
    private void onQuitter(ActionEvent e) {
        System.exit(0);
    }
    @FXML
    private void onDeconnexion(ActionEvent e) {
        try {
            // Fermer la fenêtre actuelle
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();

            // Ouvrir la page de connexion
            MainApplication.showLogin();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
