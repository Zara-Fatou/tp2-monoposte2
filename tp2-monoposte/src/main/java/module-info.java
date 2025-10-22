module com.example.tp2monoposte {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.tp2monoposte to javafx.fxml;
    opens com.example.tp2monoposte.controller to javafx.fxml; // 👈 ajoute ceci
    exports com.example.tp2monoposte;
    exports com.example.tp2monoposte.controller;
}
