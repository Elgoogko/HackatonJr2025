package com.main;

import java.util.ArrayList;

import com.main.Stockables;

import com.main.Nourriture;

public class Utilisateur {
    private String nom;
    private float argent;
    private int pv;
    private ArrayList<Stockables> inventaire;
    private int faim;
    private int temperature;

    public Utilisateur(String nom, float argent, int pv, ArrayList<Stockables> inventaire, int faim, int temperature){
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
    public int getPV(){
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
    public void setPV(int pv){
        this.pv = pv;
    }
    public void setInventaire(ArrayList<Stockables> inventaire){
        this.inventaire = inventaire;
    }
    public void setFaim(int faim){
        this.faim = faim;
    }
    public void setTemperature(int temperature){
        this.temperature = temperature;
    }

    public int retirerArgent(float prix){
        if(prix > this.argent){
            return 0;
        }
        this.argent -= prix;
        return 1;
    }

    public void ajoutElement(Stockables stockables){
        this.inventaire.add(stockables);
    }

    public void ajoutTousElements(ArrayList<Stockables> stockables){
        this.inventaire.addAll(stockables);
    }

    public void reduirePV(int pv){
        this.pv -= pv;
        if(this.pv < 0){
            this.pv = 0;
        }
    }

    public void ajouterPV(int pv){
        this.pv += pv;
        if(this.pv > 100){
            this.pv = 100;
        }
    }

    public void reduireFaim(int faim){
        this.faim -= faim;
        if(this.faim < 0){
            this.faim = 0;
        }
    }

    public void ajouterFaim(int faim){
        this.faim += faim;
        if(this.faim > 100){
            this.faim = 100;
        }
    }

    public void reduireTemperature(int temperature){
        this.temperature -= temperature;
    }

    public void ajouterTemperature(int temperature){
        this.temperature += temperature;
    }

    public void manger(Nourriture nourriture){
        ajouterFaim(nourriture.getRenduFaim());
        this.inventaire.remove(nourriture);
    }
}
