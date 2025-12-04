package com.main;
import com.main.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Création du personnage ===");
        System.out.print("Donnez votre nom guerrier : ");
        String nomPerso = sc.nextLine();

        Vetement haut = new Vetement(0, TYPE_VETEMENT.Haut, "naked", 0);
        Vetement bas = new Vetement(0, TYPE_VETEMENT.Bas, "naked", 0);
        Vetement tete = new Vetement(0, TYPE_VETEMENT.Tete, "naked", 0);


        Tenue tenue = new Tenue(haut, bas, tete);

        Utilisateur user = new Utilisateur(nomPerso, 1000, 100, new ArrayList<>(), 100, 20, tenue);
        System.out.println("Bienvenue " + user.getNom() + " !");
        System.out.println("Votre personnage porte une tenue  composé de " + user.getTenue().getBas().getNom() + ", " + user.getTenue().getHaut().getNom() + "et " + user.getTenue().getTete().getNom());
        System.out.println("Vous commencez avec " + user.getArgent() + " zénis\n");

        
        Boutique boutique = new Boutique();

    }
}
//system.out.prim  pour afficher