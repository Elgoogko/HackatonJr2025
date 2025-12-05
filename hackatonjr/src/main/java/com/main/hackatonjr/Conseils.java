package com.main.hackatonjr;

import java.util.ArrayList;


public class Conseils {
    //liste de conseils pour survivre sur Manek dans DBZ
    private ArrayList<String> conseilsEnnemis;
    private ArrayList<String> conseilsArgent;
    private ArrayList<String> conseilsDeplacement;
    private ArrayList<String> conseilsSurvie;
    // categories : ENNEMIS, ARGENT, DEPLACEMENT, SURVIE

    public Conseils() {
        this.conseilsEnnemis = new ArrayList<>();
        this.conseilsArgent = new ArrayList<>();
        this.conseilsDeplacement = new ArrayList<>();
        this.conseilsSurvie = new ArrayList<>();

        //ajout de conseils exemples
        this.conseilsEnnemis.add("Évite de rester sur les zones où un ennemi arrive.");
        this.conseilsArgent.add("Économise ton argent pour acheter des objets utiles.");
        this.conseilsArgent.add("Gagne de l'argent en te déplaçant ou passivement en attendant.");
        this.conseilsArgent.add("Le stock de la boutique est limité, tu peux cependant acheter autant de capsules que tu veux.");
        
        this.conseilsSurvie.add("Couvre toi s'il fait trop froid.");
        this.conseilsDeplacement.add("Certains chemins ne sont accessibles qu'avec certains moyens de transport.");
        this.conseilsSurvie.add("Mange régulièrement pour maintenir ton récupérer des PV.");
        this.conseilsSurvie.add("Survie pendant 5 min pour gagner");
    }

}
