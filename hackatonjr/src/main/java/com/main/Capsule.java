package com.main;

import com.main.Stockables;

enum Couleur{ BLEU, ROUGE, VERT}

public class Capsule {
    private float prix;
    private Couleur couleur;
    private Stockables object;

    public Capsule(float prix, Couleur couleur, Stockables object) {
        this.prix = prix;
        this.couleur = couleur;
        this.object = object;
    }

    public float getPrix() {
        return prix;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public Stockables getObject() {
        return object;
    }

    public void setObject(Stockables object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Capsule{" +
                "prix=" + prix +
                ", couleur='" + couleur + '\'' +
                ", object=" + object +
                '}';
    }

}
