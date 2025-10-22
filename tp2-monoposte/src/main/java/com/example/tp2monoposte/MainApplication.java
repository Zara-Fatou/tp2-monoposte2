package com.example.tp2monoposte;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        showLogin(); // Démarre avec la page de connexion
    }

    /** Affiche la page de connexion */
    public static void showLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/com/example/tp2monoposte/Login.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        scene.getStylesheets().add(MainApplication.class.getResource("/fichiercss/Style.css").toExternalForm());

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
        scene.getStylesheets().add(MainApplication.class.getResource("/fichiercss/Style.css").toExternalForm());

        primaryStage.setTitle("Gestion des monstres");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    /** Affiche la page de recherche/ajout de monstre */
    public static void showRechercheMonstre() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/com/example/tp2monoposte/RechercheMonstre.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        scene.getStylesheets().add(MainApplication.class.getResource("/fichiercss/Style.css").toExternalForm());

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
