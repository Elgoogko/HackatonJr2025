package com.main;

import java.util.ArrayList;
import java.util.Objects;

public class Lieu {
    private int id;
    private String nom;
    private int temperature;
    private String description;
    private Evenement evenement;
    private ArrayList<Chemin> chemins;
    private Coordonnees coordonnees;

    public Lieu(int id, String nom, int temperature, String description, Evenement evenement,
            ArrayList<Chemin> chemins, Coordonnees coordonnees) {
        this.id = id;
        this.nom = nom;
        this.temperature = temperature;
        this.description = description;
        this.evenement = evenement;
        this.chemins = chemins;
        this.coordonnees = coordonnees;
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

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void addChemin(Chemin c) {
        chemins.add(c);
    }

    public void addAllChemins(ArrayList<Chemin> c) {
        chemins.addAll(c);
    }

    public void setChemins(ArrayList<Chemin> chemins) {
        this.chemins = chemins;
    }

    public ArrayList<Lieu> 

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lieu)) {
            return false;
        }
        Lieu lieu = (Lieu) o;
        return id == lieu.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
