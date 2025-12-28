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
    private ArrayList<Evenement> attaques;

    public ArrayList<Lieu> retourLieuxRandom(ArrayList<Lieu> lieuxExistants){
        ArrayList<Lieu> lieux = new ArrayList<>();
        lieux.add(lieuxExistants.get(ThreadLocalRandom.current().nextInt(lieuxExistants.size())));
        for(Chemin c : lieux.get(0).getChemins()){
            if(c.getLieuA() != lieux.get(0)){
                lieux.add(c.getLieuA());
            }
            else if(c.getLieuB() != lieux.get(0)){
                lieux.add(c.getLieuB());
            }
        }
            
        return lieux;
    }

    public Gestionnaire_Events(Carte carte){
        this.typesEvenements = new ArrayList<>(List.of(new Type_Evenement(T_Evenement.HISTOIRE,0.05f),new Type_Evenement(T_Evenement.DANGER,0.05f),new Type_Evenement(T_Evenement.BONUS,0.05f),new Type_Evenement(T_Evenement.MALLUS,0.05f),new Type_Evenement(T_Evenement.ATTAQUE,0.8f)));

        this.dangers = new ArrayList<>();
        this.dangers.add(new Evenement(T_Evenement.DANGER, 0, "Friezer lance une attaque", retourLieuxRandom(carte.getLieux())));
        this.dangers.add(new Evenement(T_Evenement.DANGER, 0, "Une explosion a été déclanchée", retourLieuxRandom(carte.getLieux())));
        this.dangers.add(new Evenement(T_Evenement.DANGER, 0, "Le capitaine Ginyu et sa troupe sont en train de combattre", retourLieuxRandom(carte.getLieux())));

        this.histoires = new ArrayList<>();
        this.histoires.add(new Evenement(T_Evenement.HISTOIRE, 0, "Gohan, Krillin et Bulma sont arrivés sur Namek", new ArrayList<>()));
        this.histoires.add(new Evenement(T_Evenement.HISTOIRE, 0, "Vegeta s'allie à Gohan et Krillin pour récupérer les dragon balls", new ArrayList<>()));
        this.histoires.add(new Evenement(T_Evenement.HISTOIRE, 0, "Goku arrive pour en finir avec Friezer", new ArrayList<>()));

        this.bonus = new ArrayList<>();
        this.bonus.add(new Evenement(T_Evenement.BONUS, 0, "Vous avez gagné ", new ArrayList<>()));

        this.mallus = new ArrayList<>();
        this.mallus.add(new Evenement(T_Evenement.MALLUS, 0, "Vous avez perdu ", new ArrayList<>()));

        this.attaques = new ArrayList<>();
        this.attaques.add(new Evenement(T_Evenement.ATTAQUE, 0, "Freezer t'attaque", new ArrayList<>()));
        this.attaques.add(new Evenement(T_Evenement.ATTAQUE, 0, "Vegeta t'attaque", new ArrayList<>()));
        this.attaques.add(new Evenement(T_Evenement.ATTAQUE, 0, "Ginyu t'attaque", new ArrayList<>()));
    }


    public Evenement getRandomEvent(){
        double random = Math.random();
        int counter = 0;
        float somme = this.typesEvenements.get(counter).getProbabilite();

        while(somme < random && counter < this.typesEvenements.size()-1){
            counter++;
            somme+=this.typesEvenements.get(counter).getProbabilite();
        }

        switch(this.typesEvenements.get(counter).getTypeEvenement()){
            case HISTOIRE:
                if(this.histoires.size()>0){
                    Evenement event = this.histoires.get(0);
                    this.histoires.remove(0);
                    return event;
                }
                break;

            case DANGER:
                if(this.dangers.size()>0){
                    return this.dangers.get(ThreadLocalRandom.current().nextInt(this.dangers.size()));
                }
                break;

            case BONUS:
                if(this.bonus.size()>0){
                    return this.bonus.get(ThreadLocalRandom.current().nextInt(this.bonus.size()));
                }
                break;

            case MALLUS:
                if(this.mallus.size()>0){
                    return this.mallus.get(ThreadLocalRandom.current().nextInt(this.mallus.size()));
                }
                break;

            case ATTAQUE:
                if(this.attaques.size()>0){
                    return this.attaques.get(ThreadLocalRandom.current().nextInt(this.attaques.size()));
                }
                break;
        }

        return null;
    }

    public boolean effetEvenement(Evenement event, Utilisateur utilisateur){
        int random = 0;

        switch(event.getType()){
            case DANGER:
                for(Lieu lieu : event.getLieuxCibles()){
                    lieu.setCondamne(true);
                }
                if(utilisateur.getLieuActuel().estCondamne()){
                    return true;
                }
                break;

            case BONUS:
                random = ThreadLocalRandom.current().nextInt(1, 30)*100;
                utilisateur.ajouterArgent((float)random);
                break;

            case MALLUS:
                random = ThreadLocalRandom.current().nextInt(1, 10)*100;
                if(utilisateur.retirerArgent(random) == false){
                    utilisateur.setArgent(0);
                }
                break;

            case ATTAQUE:
                Vetement haut = new Vetement(-1,0, TYPE_VETEMENT.Haut, "none", 0);
                Vetement bas = new Vetement(-2,0, TYPE_VETEMENT.Bas, "none", 0);
                Vetement tete = new Vetement(-3,0, TYPE_VETEMENT.Tete, "none", 0);

                Tenue tenue = new Tenue(haut, bas, tete);

                boolean verify = true;

                if(!utilisateur.getTenue().getBas().equals(bas) && !utilisateur.getTenue().getHaut().equals(haut) && !utilisateur.getTenue().getTete().equals(tete)){
                    verify = false;
                }

                utilisateur.getInventaire().remove(utilisateur.getTenue().getBas());
                utilisateur.getInventaire().remove(utilisateur.getTenue().getHaut());
                utilisateur.getInventaire().remove(utilisateur.getTenue().getTete());

                utilisateur.setTenue(tenue);

                return verify;
            default:
                break;
        }
        return false;

                 

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
