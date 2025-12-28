package com.main.hackatonjr;

enum TYPE{Voiture,Pilier,Nuage,Pieds};

public class Vehicules extends Stockables {
    private float vitesse;
    private TYPE type;

    public Vehicules(int id, float vitesse, String nom, TYPE type, float prix) {
        super(id, nom, prix);
        this.vitesse = vitesse;
        this.type = type;
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

    public void setType(TYPE type) {
        this.type = type;
    }

    @Override 
    public boolean equals(Object obj){
        if(obj instanceof Vehicules){
            Vehicules vehicules = (Vehicules) obj;
            if(vehicules.getId() == this.getId() && vehicules.getNom().equals(this.getNom()) && vehicules.getPrix() == this.getPrix() && vehicules.getType() == this.type && vehicules.getVitesse() == this.vitesse){
                return true;
            }
        }
        return false;
    }
}
