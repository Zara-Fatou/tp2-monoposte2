package com.example.tp2monoposte.controller;

import com.example.tp2monoposte.MainApplication;
import com.example.tp2monoposte.modele.Monstre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ListeMonstreController {

    @FXML private ImageView logoMonstre;
    @FXML private Button btnConnexion;
    @FXML private TextField nomField;
    @FXML private ComboBox<String> familleCombo;
    @FXML private ComboBox<String> armeCombo;
    @FXML private Spinner<Integer> minPvSpinner;
    @FXML private Spinner<Integer> maxPvSpinner;
    @FXML private Button btnRechercher;
    @FXML private Button btnEffacerForm;
    @FXML private TableView<Monstre> tableMonstres;
    @FXML private TableColumn<Monstre, String> colNom;
    @FXML private TableColumn<Monstre, String> colFamille;
    @FXML private TableColumn<Monstre, String> colArme;
    @FXML private TableColumn<Monstre, Integer> colPv;
    @FXML private Button btnEffacerSelection;
    @FXML private Button btnQuitter;

    private final ObservableList<Monstre> monstres = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialisation des colonnes
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        colFamille.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFamille()));
        colArme.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getArme()));
        colPv.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPointsVie()).asObject());

        tableMonstres.setItems(monstres);

        // Spinner valeurs par défaut
        minPvSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 0));
        maxPvSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 500));

        // ComboBox familles et armes
        familleCombo.getItems().addAll("Orc", "Démon", "Dragon", "Canidé", "Vampire");
        armeCombo.getItems().addAll("Épée", "Griffe", "Magie", "Dent", "Feu");
    }

    @FXML
    private void onRechercher(ActionEvent e) {
        // Ici, tu peux filtrer la liste des monstres selon les champs saisis
        System.out.println("Recherche monstre : " + nomField.getText());
    }

    @FXML
    private void onEffacerForm(ActionEvent e) {
        nomField.clear();
        familleCombo.getSelectionModel().clearSelection();
        armeCombo.getSelectionModel().clearSelection();
        minPvSpinner.getValueFactory().setValue(0);
        maxPvSpinner.getValueFactory().setValue(500);
    }

    @FXML
    private void onEffacerSelection(ActionEvent e) {
        Monstre selected = tableMonstres.getSelectionModel().getSelectedItem();
        if (selected != null) {
            monstres.remove(selected);
        }
    }

    @FXML
    private void onQuitter(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    private void onConnexion(ActionEvent e) {
        try {
            MainApplication.showLogin();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
