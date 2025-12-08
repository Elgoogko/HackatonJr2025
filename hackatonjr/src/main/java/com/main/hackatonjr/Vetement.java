package com.main.hackatonjr;

import com.main.hackatonjr.Stockables;

enum TYPE_VETEMENT {Tete, Haut, Bas};

public class Vetement extends Stockables {
    private float chaleur;
    private TYPE_VETEMENT type;

    public Vetement(int id, float chaleur, TYPE_VETEMENT type, String nom, float prix){
        super(id, nom, prix);
        this.chaleur = chaleur;
        this.type = type;
    }

    public float getChaleur(){
        return this.chaleur;
    }

    public void setChaleur(float chaleur){
        this.chaleur = chaleur;
    }

    public TYPE_VETEMENT getType(){
        return this.type;
    }

    public void setType(TYPE_VETEMENT type){
        this.type = type;
    }

    @Override 
    public boolean equals(Object obj){
        if(obj instanceof Vetement){
            Vetement vetement = (Vetement) obj;
            if(vetement.getId() == this.getId() && vetement.getNom().equals(this.getNom()) && vetement.getPrix() == this.getPrix() && vetement.getChaleur() == this.chaleur){
                return true;
            }
        }
        return false;
    }
}
