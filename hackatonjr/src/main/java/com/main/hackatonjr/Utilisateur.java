package com.main.hackatonjr;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

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
    private Lieu actuel;

    public Utilisateur(String nom, float argent, int pv, ArrayList<Stockables> inventaire, int faim, int temperature, Tenue tenue, Vehicules vehicule, Lieu lieu){
        this.nom = nom;
        this.argent = argent;
        this.pv = pv;
        this.inventaire = inventaire;
        this.faim = faim;
        this.temperature = temperature;
        this.tenue = tenue;
        this.vehicule = vehicule;
        this.actuel = lieu;
    }

    public Lieu getLieuActuel(){
        return this.actuel;
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

    public void setLieuActuel(Lieu lieu){
        this.actuel = lieu;
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

    public boolean retirerArgent(float prix){
        if(prix > this.argent){
            return false;
        }
        this.argent -= prix;
        return true;
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

    public boolean equiperVetement(Vetement vetement){
        if(vetement.getType() == TYPE_VETEMENT.Tete){
            if(this.tenue.getTete().equals(vetement)){
                return false;
            }
            else{
                this.tenue.setTete(vetement);
                return true;
            }
        }
        else if(vetement.getType() == TYPE_VETEMENT.Haut){
            if(this.tenue.getHaut().equals(vetement)){
                return false;
            }
            else{
                this.tenue.setHaut(vetement);
                return true;
            }
        }
        else if(vetement.getType() == TYPE_VETEMENT.Bas){
            if(this.tenue.getBas().equals(vetement)){
                return false;
            }
            else{
                this.tenue.setBas(vetement);
                return true;
            }
        }
        else{
            return false;
        }
    }

    public boolean equiperVehicule(Vehicules vehicule){
        if(this.vehicule.getNom() == vehicule.getNom() && this.vehicule.getPrix() == vehicule.getPrix() && this.vehicule.getType() == vehicule.getType() && this.vehicule.getVitesse() == vehicule.getVitesse()){
            return false;
        }
        this.vehicule.setNom(vehicule.getNom());
        this.vehicule.setType(vehicule.getType());
        this.vehicule.setPrix(vehicule.getPrix());
        this.vehicule.setVitesse(vehicule.getVitesse());
        return true;
    }

    public boolean equiper(Stockables stockable){
        boolean verif;
        if(stockable instanceof Nourriture){
            manger((Nourriture)stockable);
            return true;
        }
        else if(stockable instanceof Vetement){
            verif = equiperVetement((Vetement)stockable);
        }
        else{
            verif = equiperVehicule((Vehicules)stockable);
        }
        return verif;
    }

    public void afficherInventaire(){
        int compteur=1;
        System.out.println("Inventaire (" + this.inventaire.size() + " éléments) : ");
        for(Stockables stockable : this.inventaire){
            System.out.println(compteur + ". " + stockable.getNom()  + " " + stockable.getPrix() + " zénis");
            compteur++;
        }
    }

    public void afficherProfil(){
        System.out.println("Pseudo : " + this.nom);
        System.out.println("Lieu : " + this.actuel.getNom());
        System.out.println("Argent : " + this.argent);
        System.out.println("Faim : " + this.faim + "%");
        System.out.println("Vehicule : " + this.vehicule.getNom());
        System.out.println("Tenue : ");
        System.out.println("-> Haut : " + this.tenue.getHaut().getNom());
        System.out.println("-> Bas : " + this.tenue.getBas().getNom());
        System.out.println("-> Tete : " + this.tenue.getTete().getNom());
    }
}
