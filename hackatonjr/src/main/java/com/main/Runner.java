package com.main;
import com.main.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Création du personnage ===");
        System.out.print("Donnez votre pseudo : ");
        String pseudo = sc.nextLine();

        Vetement haut = new Vetement(0, TYPE_VETEMENT.Haut, "naked", 0);
        Vetement bas = new Vetement(0, TYPE_VETEMENT.Bas, "naked", 0);
        Vetement tete = new Vetement(0, TYPE_VETEMENT.Tete, "naked", 0);

        Tenue tenue = new Tenue(haut, bas, tete);
        Vehicules vehicule = new Vehicules(0, pseudo, null, 0)

        Utilisateur user = new Utilisateur(pseudo, 1000, 100, new ArrayList<>(), 100, 20, tenue);
        System.out.println("Bienvenue " + user.getNom() + " !");
        System.out.println("Votre personnage porte une tenue  composé de " + user.getTenue().getBas().getNom() + ", " + user.getTenue().getHaut().getNom() + " et " + user.getTenue().getTete().getNom());
        System.out.println("Vous commencez avec " + user.getArgent() + " zénis\n");

        
        Boutique shop = new Boutique();
        for(Stockables s : shop.getStock()){
            System.out.println(s.getNom()  + " " + s.getPrix() +"\n");
        }

    }
}
