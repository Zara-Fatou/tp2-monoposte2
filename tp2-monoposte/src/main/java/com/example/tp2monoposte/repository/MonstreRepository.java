package com.example.tp2monoposte.repository;

import com.example.tp2monoposte.modele.Monstre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MonstreRepository {
    private static final ObservableList<Monstre> monstres = FXCollections.observableArrayList();

    public static ObservableList<Monstre> getMonstres() {
        return monstres;
    }

    public static void ajouterMonstre(Monstre monstre) {
        monstres.add(monstre);
    }

}
