package com.example.tp2monoposte.modele;


public class Monstre {
    private String nom;
    private String famille;
    private String arme;
    private int pointsVie;

    public Monstre(String nom, String famille, String arme, int pointsVie) {
        this.nom = nom;
        this.famille = famille;
        this.arme = arme;
        this.pointsVie = pointsVie;
    }

    public String getNom() { return nom; }
    public String getFamille() { return famille; }
    public String getArme() { return arme; }
    public int getPointsVie() { return pointsVie; }
}