package com.main.hackatonjr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Gestionnaire_Events {

    private ArrayList<Type_Evenement> typesEvenements;

    private ArrayList<Evenement> dangers;
    private ArrayList<Evenement> histoires;
    private ArrayList<Evenement> bonus;
    private ArrayList<Evenement> mallus;

    public Gestionnaire_Events(Carte carte){
        this.typesEvenements = new ArrayList<>(List.of(Type_Evenement.HISTOIRE,Type_Evenement.DANGER,Type_Evenement.BONUS,Type_Evenement.MALLUS));
        ArrayList<Lieu> lieux = carte.getLieux();

        this.dangers = new ArrayList<>();
        this.dangers.add(new Evenement(Type_Evenement.DANGER, 0, "Friezer lance une attaque", new ArrayList<>(List.of(lieux.get(ThreadLocalRandom.current().nextInt(lieux.size())),lieux.get(ThreadLocalRandom.current().nextInt(lieux.size()))))));
        this.dangers.add(new Evenement(Type_Evenement.DANGER, 0, "Une explosion a été déclanchée", new ArrayList<>(List.of(lieux.get(ThreadLocalRandom.current().nextInt(lieux.size())),lieux.get(ThreadLocalRandom.current().nextInt(lieux.size()))))));
        this.dangers.add(new Evenement(Type_Evenement.DANGER, 0, "Le capitaine Ginyu et sa troupe sont en train de combattre", new ArrayList<>(List.of(lieux.get(ThreadLocalRandom.current().nextInt(lieux.size()))))));

        this.histoires = new ArrayList<>();
        this.histoires.add(new Evenement(Type_Evenement.HISTOIRE, 0, "Gohan, Krillin et Bulma sont arrivés sur Namek", new ArrayList<>()));
        this.histoires.add(new Evenement(Type_Evenement.HISTOIRE, 0, "Vegeta s'allie à Gohan et Krillin pour récupérer les dragon balls", new ArrayList<>()));
        this.histoires.add(new Evenement(Type_Evenement.HISTOIRE, 0, "Goku arrive pour en finir avec Friezer", new ArrayList<>()));
    }

    public void declencher(Evenement e, Lieu lieuJoueur) {
        if (e.getType() == Type_Evenement.HISTOIRE) {
            System.out.println("\n[HISTOIRE] " + e.getDescription());

        } 
        else if (e.getType() == Type_Evenement.DANGER) {
            System.out.println("\n[DANGER] " + e.getDescription());

            if (e.getLieuxCibles() != null) {
                for(Lieu l : e.getLieuxCibles()) {
                    l.setCondamne(true);
                    System.out.println("-> La zone " + l.getNom() + " est maintenant CONDAMNE");
                }
            }

            if (e.getLieuxCibles() != null && e.getLieuxCibles().contains(lieuJoueur)) {
                System.out.println("💀 Tu es mort.");
            }
        }
    }

    public ArrayList<Evenement> getDangers(){
        return this.dangers;
    }
    public ArrayList<Evenement> getHistoires(){
        return this.histoires;
    }
    public ArrayList<Evenement> getBonus(){
        return this.bonus;
    }
    public ArrayList<Evenement> getMallus(){
        return this.mallus;
    }
}
