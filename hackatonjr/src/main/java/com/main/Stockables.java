package com.main;

public class Stockables {
    private String nom;
    private float prix;
    public Stockables(String nom, float prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public String Getnom() {
        return nom;
    }
    public float Getprix() {
        return prix;
    }
    public void Setnom(String nom) {
        this.nom = nom;
    }
    public void Setprix(float prix) {
        this.prix = prix;
    } 




}


