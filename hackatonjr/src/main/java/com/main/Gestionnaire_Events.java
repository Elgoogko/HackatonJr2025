package com.main;

import java.util.ArrayList;
import java.util.List;

public class Gestionnaire_Events {

    private List<Evenement> evenements = new ArrayList<>();

    public void ajouter(Evenement e) {
        evenements.add(e);
    }

    public void update(int tempsSec, Lieu lieuJoueur) {
        for (Evenement e : evenements) {
            if (!e.isDeclenche() && tempsSec >= e.getTriggerTimeSec()) {
                declencher(e, lieuJoueur);
                e.setDeclenche(true);
            }
        }
    }

    private void declencher(Evenement e, Lieu lieuJoueur) {
        if (e.getType() == Type_Evenement.HISTOIRE) {
            System.out.println("\n[HISTOIRE] " + e.getDescription());

        } 
        else if (e.getType() == Type_Evenement.DANGER) {
            System.out.println("\n[DANGER] " + e.getEnnemi() + " apparaît !");
            System.out.println(e.getDescription());

            if (e.getLieuxCibles() != null) {
                for(Lieu l : e.getLieuxCibles()) {
                    l.setCondamne(true);
                    System.out.println("-> La zone " + l.getNom() + " est maintenant CONDAMNE");
                }
            }

            if (e.getLieuxCibles() != null && e.getLieuxCibles().contains(lieuJoueur)) {
                System.out.println("💀 " + e.getEnnemi() + " t’a trouvé dans " + lieuJoueur.getNom() + " : tu es mort.");
            }
        }
    }
}
