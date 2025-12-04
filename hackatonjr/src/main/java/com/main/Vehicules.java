package com.main;

import com.main.Stockables;

enum TYPE{Voiture,Pilier,Nuage};

public class Vehicules extends Stockables {
    private float vitesse;
    private String nom;
    private TYPE type;

    public Vehicules(float vitesse, String nom, TYPE type, float prix){
        super(nom, prix);
        this.vitesse = vitesse;
    } 

    public float getVitesse(){
        return this.vitesse;
    }

    public void setVitesse(float vitesse){
        this.vitesse = vitesse;
    }

    public TYPE getType(){
        return this.type;
    }

    public void setType(TYPE type){
        this.type = type;
    }
}
