package com.main.hackatonjr;

public class Stockables {
    private int id;
    private String nom;
    private float prix;
    
    public Stockables(int id, String nom, float prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }
    public float getPrix() {
        return prix;
    }
    public int getId(){
        return id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    } 
    public void setId(int id){
        this.id = id;
    }

    @Override 
    public boolean equals(Object obj){
        if(this instanceof Vehicules && obj instanceof Vehicules){
            return ((Vehicules)this).equals((Vehicules)obj);
        }
        else if(this instanceof Vetement && obj instanceof Vetement){
            return ((Vetement)this).equals((Vetement)obj);
        }
        else if(this instanceof Nourriture && obj instanceof Nourriture){
            return ((Nourriture)this).equals((Nourriture)obj);
        }
        return false;
    }
}


