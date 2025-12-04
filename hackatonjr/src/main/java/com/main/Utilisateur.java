package com.main;

import java.util.ArrayList;

import com.main.*;

public class Utilisateur {
    private String nom;
    private float argent;
    private int pv;
    private ArrayList<Stockables> inventaire;
    private int faim;
    private int temperature;
    private Tenue tenue;
    private Vehicules vehicule;

    public Utilisateur(String nom, float argent, int pv, ArrayList<Stockables> inventaire, int faim, int temperature, Tenue tenue, Vehicules vehicule){
        this.nom = nom;
        this.argent = argent;
        this.pv = pv;
        this.inventaire = inventaire;
        this.faim = faim;
        this.temperature = temperature;
        this.tenue = tenue;
        this.vehicule = vehicule;
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
    public Tenue getTenue(){
        return this.tenue;
    }
    public Vehicules getVehicules(){
        return this.vehicule;
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
    public void setTenue(Tenue tenue){
        this.tenue.setHaut(tenue.getHaut());
        this.tenue.setBas(tenue.getBas());
        this.tenue.setTete(tenue.getTete());
    }
    public void setVehicule(Vehicules vehicule){
        this.vehicule.setNom(vehicule.getNom());
        this.vehicule.setPrix(vehicule.getPrix());
        this.vehicule.setVitesse(vehicule.getVitesse());
        this.vehicule.setType(vehicule.getType());
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

    public int equiperVetement(Vetement vetement){
        if(vetement.getType() == TYPE_VETEMENT.Tete){
            if(this.tenue.getTete().equals(vetement)){
                return 0;
            }
            else{
                this.tenue.setTete(vetement);
                return 1;
            }
        }
        else if(vetement.getType() == TYPE_VETEMENT.Haut){
            if(this.tenue.getHaut().equals(vetement)){
                return 0;
            }
            else{
                this.tenue.setHaut(vetement);
                return 1;
            }
        }
        else if(vetement.getType() == TYPE_VETEMENT.Bas){
            if(this.tenue.getBas().equals(vetement)){
                return 0;
            }
            else{
                this.tenue.setBas(vetement);
                return 1;
            }
        }
        else{
            return 0;
        }
    }
}
