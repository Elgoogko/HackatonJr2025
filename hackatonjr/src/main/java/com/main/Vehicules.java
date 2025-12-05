package com.main;

enum TYPE {
    Voiture, Pilier, Nuage, Pieds
};

public class Vehicules extends Stockables {
    private float vitesse;
    private String nom;
    private TYPE type;

    public Vehicules(float vitesse, String nom, TYPE type, float prix) {
        super(nom, prix);
        this.vitesse = vitesse;
    }

    public float getVitesse() {
        return this.vitesse;
    }

    public void setVitesse(float vitesse) {
        this.vitesse = vitesse;
    }

    public float getTempsTrajet(float distance) {
        if (this.vitesse == 0) {
            return Float.MAX_VALUE;
        }
        return distance / this.vitesse;
    }

    public TYPE getType() {
        return this.type;
    }

    public String getNom() {
        return this.nom;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
