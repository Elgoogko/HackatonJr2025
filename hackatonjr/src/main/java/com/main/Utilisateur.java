package com.main;

import java.util.ArrayList;

import com.main.Stockables;


public class Utilisateur {
    private String nom;
    private float argent;
    private float pv;
    private ArrayList<Stockables> inventaire;
    private float faim;
    private float temperature;

    public Utilisateur(String nom, float argent, float pv, ArrayList<Stockables> inventaire, float faim, float temperature){
        this.nom = nom;
        this.argent = argent;
        this.pv = pv;
        this.inventaire = inventaire;
        this.faim = faim;
        this.temperature = temperature;
    }

    public String getNom(){
        return this.nom;
    }
    public float getArgent(){
        return this.argent;
    }
    public float getPV(){
        return this.pv;
    }
    public ArrayList<Stockables> getInventaire(){
        return this.inventaire;
    }
    public float getFaim(){
        return this.faim;
    }
    public float getTemperature(){
        return this.temperature;
    }


    public void setNom(String nom){
        this.nom = nom;
    }
    public void setArgent(float argent){
        this.argent = argent;
    }
    public void setPV(float pv){
        this.pv = pv;
    }
    public void setInventaire(ArrayList<Stockables> inventaire){
        this.inventaire = inventaire;
    }
    public void setFaim(float faim){
        this.faim = faim;
    }
    public void setTemperature(float temperature){
        this.temperature = temperature;
    }

    public int retirerArgent(float prix){
        if(prix > this.argent){
            return 0;
        }
        this.argent = this.argent - prix;
        return 1;
    }

    public void ajoutElement(Stockables stockables){
        this.inventaire.add(stockables);
    }

    public void ajoutTousElements(ArrayList<Stockables> stockables){
        this.inventaire.addAll(stockables);
    }
}
