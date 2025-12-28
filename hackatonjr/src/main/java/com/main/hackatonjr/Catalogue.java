package com.main.hackatonjr;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Catalogue {
    public ArrayList<Vetement> Vetements = new ArrayList<>();
    public ArrayList<Nourriture> Nourritures = new ArrayList<>();
    public ArrayList<Vehicules> Vehicules = new ArrayList<>();
    public Catalogue() {
        Vetements.add(new Vetement(1,5.0f, TYPE_VETEMENT.Tete, "Chapeau de Chaozu", 200.0f));
        Vetements.add(new Vetement(2,3.0f, TYPE_VETEMENT.Tete, "Echarpe", 100.0f));
        Vetements.add(new Vetement(3,7.0f, TYPE_VETEMENT.Tete, "Turban de Piccolo", 400.0f));
        Vetements.add(new Vetement(4,8.0f, TYPE_VETEMENT.Haut, "Haut de Goku", 500.0f));
        Vetements.add(new Vetement(5,9.0f, TYPE_VETEMENT.Haut, "Haut de Piccolo", 800.0f));
        Vetements.add(new Vetement(6,8.0f, TYPE_VETEMENT.Haut, "Haut de Vegeta", 600.0f));
        Vetements.add(new Vetement(7,6.0f, TYPE_VETEMENT.Bas, "Bas de Goku", 300.0f));
        Vetements.add(new Vetement(8,6.0f, TYPE_VETEMENT.Bas, "Bas de Vegeta", 400.0f));
        Vetements.add(new Vetement(9,7.0f, TYPE_VETEMENT.Bas, "Bas de Piccolo", 500.0f));

        Nourritures.add(new Nourriture(10,100, "Senzu", 1000.0f));
        Nourritures.add(new Nourriture(11,35, "Gibier", 300.0f));
        Nourritures.add(new Nourriture(12,45, "Ramen", 500.0f));
        Nourritures.add(new Nourriture(13,25, "Burger", 100.0f));

        Vehicules.add(new Vehicules(14,100.0f, "Voiture", TYPE.Voiture, 200));
        Vehicules.add(new Vehicules(15,1000.0f, "Nuage Magique", TYPE.Nuage, 1000));
        Vehicules.add(new Vehicules(16,500.0f, "Pilier", TYPE.Pilier, 400.0f));

    }
}
