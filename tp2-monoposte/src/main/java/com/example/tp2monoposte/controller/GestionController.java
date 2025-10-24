package com.example.tp2monoposte.controller;

import com.example.tp2monoposte.MainApplication;
import com.example.tp2monoposte.modele.Monstre;
import com.example.tp2monoposte.repository.MonstreRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionController {


    public Button btnQuitter;
    public Button btnDeconnexion;
    public Button btnEffacerForm;
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


    private final ObservableList<Monstre> monstres = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Colonnes
        ListeMonstreController.selectionnerNom(colNom, colFamille, colArme, colPv, tableMonstres, monstres);

        // ComboBox et Spinner
        familleCombo.getItems().addAll("Orc", "Démon", "Dragon", "Canidé", "Vampire");
        armeCombo.getItems().addAll("Épée", "Griffe", "Magie", "Dent", "Feu");
        pvSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));



        // Désactiver le bouton Ajouter si un champ n’est pas rempli
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
        MonstreRepository.ajouterMonstre(nouveau); // Ajout global

        effacerFormulaire();

        try {
            MainApplication.showListeDeMonstre();
        } catch (IOException ex) {
            System.out.println("Impossible d'ouvrir la fenetre!");
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
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();
            MainApplication.showLogin();
        } catch (Exception ex) {
            System.out.println("Deconnexion impossible!");
        }
    }
}
