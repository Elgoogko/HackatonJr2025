package com.main;

import com.main.Stockables;

public enum TYPE_VETEMENT {Tete, Haut, Bas};

public class Vetement extends Stockables {
    private float chaleur;
    private TYPE type;

    public Vetement(float chaleur, String nom, TYPE_VETEMENT type, float prix){
        super(nom, prix);
        this.chaleur = chaleur;
        this.type = type;
    }

    public float getChaleur(){
        return this.chaleur;
    }

    public void setChaleur{float chaleur}{
        this.chaleur = chaleur;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public TYPE_VETEMENT getType(){
        return this.type;
    }

    public void setType(TYPE_VETEMENT type){
        this.type = type;
    }
}