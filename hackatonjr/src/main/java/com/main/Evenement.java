package com.main;

public class Evenement {
    private float probaEvent;
    private String nom;

    public Evenement(float probaEvent, String nom){
        this.probaEvent = probaEvent;
        this.nom = nom;
    }

    public float getProba(){
        return this.probaEvent;
    }
    public String getNom(){
        return this.nom;
    }

    public void setProba(float probaEvent){
        this.probaEvent = probaEvent;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
}