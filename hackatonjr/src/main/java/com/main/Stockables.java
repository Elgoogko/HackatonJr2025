package com.main;

public class Stockables {
    private String nom;
    private float prix;
    public Stockables(String nom, float prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }
    public float getPrix() {
        return prix;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    } 




}


