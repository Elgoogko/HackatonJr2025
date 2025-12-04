package com.main;
import java.util.ArrayList;
import com.main.*;
public class Catalogue {
    public static ArrayList<Vetement> Vetements = new ArrayList<>();
    public static ArrayList<Nourriture> Nourritures = new ArrayList<>();
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

        
    }
}
