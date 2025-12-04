package com.main;

import java.util.ArrayList;

public class Lieu {
    private int id;
    private String nom;
    private int temperature;
    private String description;
    private Evenement evenement;
    private ArrayList<Chemin> chemins;

    public Lieu(int id, String nom, int temperature, String description, Evenement evenement,
            ArrayList<Chemin> chemins) {
        this.id = id;
        this.nom = nom;
        this.temperature = temperature;
        this.description = description;
        this.evenement = evenement;
        this.chemins = chemins;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public ArrayList<Chemin> getChemins() {
        return chemins;
    }

    public void setChemins(ArrayList<Chemin> chemins) {
        this.chemins = chemins;
    }

    @Override
    public String toString() {
        return "Lieu{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", temperature=" + temperature +
                ", description='" + description + '\'' +
                ", evenement=" + evenement +
                '}';
    }

}
