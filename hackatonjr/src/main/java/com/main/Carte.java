package com.main;

import java.util.List;

public class Carte {
    private List<Lieu> lieux;
    private List<Chemin> chemins;

    public Carte(List<Lieu> lieux, List<Chemin> chemins) {
        this.lieux = lieux;
        this.chemins = chemins;
    }
    public List<Lieu> getLieux() {
        return lieux;
    }
    public List<Chemin> getChemins() {
        return chemins;
    }
     
}
