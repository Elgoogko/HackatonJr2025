package com.main;

import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        Lieu a = new Lieu(1, "A", 25, "Lieu A", null, new java.util.ArrayList<Chemin>());
        Lieu b = new Lieu(2, "B", 30, "Lieu B", null, new java.util.ArrayList<Chemin>());
        Lieu c = new Lieu(3, "C", 20, "Lieu C", null, new java.util.ArrayList<Chemin>());

        Chemin ab = new Chemin(a, b, 5f);
        Chemin bc = new Chemin(b, c, 2f);
        Chemin ac = new Chemin(a, c, 5f);

        a.addChemin(ab);
        a.addChemin(ac);
        b.addChemin(ab);
        b.addChemin(bc);
        c.addChemin(ac);
        c.addChemin(bc);
        ArrayList<Lieu> lieux = new ArrayList<Lieu>();
        lieux.add(a);
        lieux.add(b);
        lieux.add(c);
        ArrayList<Chemin> chemins = new ArrayList<Chemin>();
        chemins.add(ab);
        chemins.add(bc);
        chemins.add(ac);
        Carte carte = new Carte(lieux, chemins);
        ArrayList<Lieu> chemin = carte.plusCourtChemin(a, c);
        for (Lieu lieu : chemin) {
            System.out.println(lieu.getNom());
            
        }
    }
}
