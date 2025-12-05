package com.main.hackatonjr;
import java.util.ArrayList;
import java.util.random.RandomGenerator;

import org.springframework.stereotype.Component;

import com.main.*;
@Component
public class Catalogue {
    public static ArrayList<Vetement> Vetements = new ArrayList<>();
    public static ArrayList<Nourriture> Nourritures = new ArrayList<>();
    public static ArrayList<Vehicules> Vehicules = new ArrayList<>();
    public Catalogue() {
        Vetements.add(new Vetement(5.0f, TYPE_VETEMENT.Tete, "Chapeau de Chaozu", 200.0f));
        Vetements.add(new Vetement(3.0f, TYPE_VETEMENT.Tete, "Echarpe", 100.0f));
        Vetements.add(new Vetement(7.0f, TYPE_VETEMENT.Tete, "Turban de Piccolo", 400.0f));
        Vetements.add(new Vetement(8.0f, TYPE_VETEMENT.Haut, "Haut de Goku", 500.0f));
        Vetements.add(new Vetement(9.0f, TYPE_VETEMENT.Haut, "Haut de Piccolo", 800.0f));
        Vetements.add(new Vetement(8.0f, TYPE_VETEMENT.Haut, "Haut de Vegeta", 600.0f));
        Vetements.add(new Vetement(6.0f, TYPE_VETEMENT.Bas, "Bas de Goku", 300.0f));
        Vetements.add(new Vetement(6.0f, TYPE_VETEMENT.Bas, "Bas de Vegeta", 400.0f));
        Vetements.add(new Vetement(7.0f, TYPE_VETEMENT.Bas, "Bas de Piccolo", 500.0f));

        Nourritures.add(new Nourriture(100, "Senzu", 1000.0f));
        Nourritures.add(new Nourriture(35, "Gibier", 300.0f));
        Nourritures.add(new Nourriture(45, "Ramen", 500.0f));
        Nourritures.add(new Nourriture(25, "Burger", 100.0f));

        Vehicules.add(new Vehicules(100, "Voiture", TYPE.Voiture, 200));
        Vehicules.add(new Vehicules(1000, "Nuage Magique", TYPE.Nuage, 1000));
        Vehicules.add(new Vehicules(500, "Pilier", TYPE.Pilier, 400));
    }
}
