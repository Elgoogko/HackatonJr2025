package com.main.hackatonjr;

import java.util.ArrayList;

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
    public int getFaim(){
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

    public void ajouterArgent(float argent){
        this.argent += argent;
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
        reduireFaim(nourriture.getRenduFaim());
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
        if(this.vehicule.equals(vehicule)){
            return false;
        }
        this.vehicule.setId(vehicule.getId());
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

    public boolean estEquipe(Stockables stockable){
        if(stockable instanceof Vetement){
            Vetement vetement = (Vetement)stockable;
            if((vetement.getType() == TYPE_VETEMENT.Haut && this.getTenue().getHaut().equals(vetement))
            || (vetement.getType() == TYPE_VETEMENT.Bas && this.getTenue().getBas().equals(vetement))
            || (vetement.getType() == TYPE_VETEMENT.Tete && this.getTenue().getTete().equals(vetement))){
                return true;
            }
        }
        else if(stockable instanceof Vehicules){
            Vehicules vehicule = (Vehicules)stockable;
            if(this.getVehicules().equals(vehicule)){
                return true;
            }
        }
        return false;
    }

    public void afficherInventaire(){
        int compteur=1;
        for(Stockables stockable : this.inventaire){
            System.out.print(compteur + ". " + stockable.getNom()  + " " + stockable.getPrix() + " zénis");
            if(this.estEquipe(stockable) == true){
                System.out.println("\u001B[96m" + " (Equipé)" + "\u001B[0m");
            }
            else{
                System.out.print("\n");
            }
            compteur++;
        }
    }

    public void afficherProfil(){
        System.out.println("- Pseudo : " + "\u001B[96m" + this.nom + "\u001B[0m");
        System.out.println("- Lieu : " + "\u001B[96m" + this.actuel.getNom() + "\u001B[0m");
        System.out.println("- Argent : " + "\u001B[96m" + this.argent + "\u001B[0m");
        System.out.println("- Faim : " + "\u001B[96m" + this.faim + "%" + "\u001B[0m");
        System.out.println("- Vehicule : " + "\u001B[96m" + this.vehicule.getNom() + "\u001B[0m");
        System.out.println("- Tenue : ");
        System.out.println("--- Haut : " + "\u001B[96m" + this.tenue.getHaut().getNom() + "\u001B[0m");
        System.out.println("--- Bas : " + "\u001B[96m" + this.tenue.getBas().getNom() + "\u001B[0m");
        System.out.println("--- Tete : " + "\u001B[96m" + this.tenue.getTete().getNom() + "\u001B[0m");
    }
}
