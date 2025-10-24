package com.example.tp2monoposte;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        showListeDeMonstre(); // Démarre avec la page de connexion
    }

    /** Affiche la page de connexion */
    public static void showLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/com/example/tp2monoposte/Login.fxml"));
        Scene scene = new Scene(loader.load(), 900, 900);
        scene.getStylesheets().add(
                Objects.requireNonNull(MainApplication.class.getResource("/fichiercss/Style.css")).toExternalForm()
        );

        primaryStage.setTitle("Connexion");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    /** Affiche la page de gestion des monstres */
    public static void showGestionMonstre() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/com/example/tp2monoposte/GestionMonstre.fxml"));
        Scene scene = new Scene(loader.load(), 1000, 700);
        scene.getStylesheets().add(
                Objects.requireNonNull(MainApplication.class.getResource("/fichiercss/Style.css")).toExternalForm()
        );

        primaryStage.setTitle("Gestion des monstres");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    /** Affiche la page de recherche/ajout de monstre */
    public static void showListeDeMonstre() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/com/example/tp2monoposte/ListeDeMonstre.fxml"));
        Scene scene = new Scene(loader.load(), 900, 800);
        scene.getStylesheets().add(
                Objects.requireNonNull(MainApplication.class.getResource("/fichiercss/Style.css")).toExternalForm()
        );

        primaryStage.setTitle("Recherche de monstres");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
