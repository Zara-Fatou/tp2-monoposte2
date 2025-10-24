package com.example.tp2monoposte.controller;

import com.example.tp2monoposte.MainApplication;
import com.example.tp2monoposte.modele.Monstre;
import com.example.tp2monoposte.repository.MonstreRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ListeMonstreController {


    public Button btnConnexion;
    public Button btnEffacerSelection;
    public Button btnQuitter;
    public Button btnRechercher;
    @FXML
    private TextField nomField;
    @FXML
    private ComboBox<String> familleCombo;
    @FXML
    private ComboBox<String> armeCombo;
    @FXML
    private Spinner<Integer> minPvSpinner;
    @FXML
    private Spinner<Integer> maxPvSpinner;
    @FXML
    private TableView<Monstre> tableMonstres;
    @FXML
    private TableColumn<Monstre, String> colNom;
    @FXML
    private TableColumn<Monstre, String> colFamille;
    @FXML
    private TableColumn<Monstre, String> colArme;
    @FXML
    private TableColumn<Monstre, Integer> colPv;

    private final ObservableList<Monstre> monstres = FXCollections.observableArrayList();
    private final ObservableList<Monstre> tousLesMonstres = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        // Ajout des monstres de base dans le repository (une seule fois)
        if (MonstreRepository.getMonstres().isEmpty()) {
            MonstreRepository.ajouterMonstre(new Monstre("Gorgo", "Démon", "Feu", 100));
            MonstreRepository.ajouterMonstre(new Monstre("Ragnar", "Orc", "Épée", 100));
            MonstreRepository.ajouterMonstre(new Monstre("Volkan", "Dragon", "Magie", 99));
            MonstreRepository.ajouterMonstre(new Monstre("Fenrir", "Canidé", "Dent", 80));
            MonstreRepository.ajouterMonstre(new Monstre("Nosfer", "Vampire", "Griffe", 100));
        }

        // Synchronisation des listes
        tousLesMonstres.setAll(MonstreRepository.getMonstres());
        monstres.setAll(tousLesMonstres);

        // Initialisation des colonnes
        selectionnerNom(colNom, colFamille, colArme, colPv, tableMonstres, monstres);

        // Spinner valeurs par défaut
        minPvSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        maxPvSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 100));

        // ComboBox familles et armes
        familleCombo.getItems().addAll("Orc", "Démon", "Dragon", "Canidé", "Vampire");
        armeCombo.getItems().addAll("Épée", "Griffe", "Magie", "Dent", "Feu");
    }

    static void selectionnerNom(TableColumn<Monstre, String> colNom, TableColumn<Monstre, String> colFamille, TableColumn<Monstre, String> colArme, TableColumn<Monstre, Integer> colPv, TableView<Monstre> tableMonstres, ObservableList<Monstre> monstres) {
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        colFamille.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFamille()));
        colArme.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getArme()));
        colPv.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPointsVie()).asObject());

        tableMonstres.setItems(monstres);
    }

    @FXML
    private void onRechercher(ActionEvent e) {
        String nomRecherche = nomField.getText().toLowerCase().trim();
        String familleRecherche = familleCombo.getValue();
        String armeRecherche = armeCombo.getValue();
        int minPv = minPvSpinner.getValue();
        int maxPv = maxPvSpinner.getValue();


        ObservableList<Monstre> resultat = FXCollections.observableArrayList();

        for (Monstre m : tousLesMonstres) {
            boolean correspond = true;

            if (!nomRecherche.isEmpty() && !m.getNom().toLowerCase().contains(nomRecherche)) {
                correspond = false;
            }

            if (familleRecherche != null && !m.getFamille().equals(familleRecherche)) {
                correspond = false;
            }

            if (armeRecherche != null && !m.getArme().equals(armeRecherche)) {
                correspond = false;
            }

            if (m.getPointsVie() < minPv || m.getPointsVie() > maxPv) {
                correspond = false;
            }

            if (correspond) {
                resultat.add(m);
            }
        }

        tableMonstres.setItems(resultat);
        if (resultat.isEmpty()) {
            tableMonstres.setPlaceholder(new Label("Aucun monstre trouvé."));
        }

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
