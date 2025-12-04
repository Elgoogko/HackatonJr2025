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
        Vehicules vehicule = new Vehicules(20, "pieds", TYPE.Pieds, 0);

        Utilisateur user = new Utilisateur(nomPerso, 1000, 100, new ArrayList<>(), 100, 20, tenue, vehicule);
        System.out.println("Bienvenue " + user.getNom() + " !");
        System.out.println("Votre personnage porte une tenue  composé de " + user.getTenue().getBas().getNom() + ", " + user.getTenue().getHaut().getNom() + " et " + user.getTenue().getTete().getNom());
        System.out.println("Votre personnage a comme véhicule :" + user.getVehicules().getNom() + " ");
        System.out.println("Vous commencez avec " + user.getArgent() + " zénis\n");

        
        Boutique shop = new Boutique();
        for(Stockables s : shop.getStock()){
            System.out.println(s.getNom()  + " " + s.getPrix() + " zénis" +"\n");
        }
        int choix = -1;
        while(choix !=0){
            System.out.println("\n=== MENU BOUTIQUE ===");
            System.out.println("1 - Trier par prix croissant");
            System.out.println("2 - Trier par prix décroissant");
            System.out.println("3 - Trier par nom croissant (A->Z)");
            System.out.println("4 - Trier par nom décroissant (Z->A)");
            System.out.println("0 - Quitter");
            System.out.println("Votre choix : ");

            choix = Integer.parseInt(sc.nextLine());
            switch (choix) {
                case 1:
                    shop.trierStock(ModeTris.PRIX_CROISSANT);
                    break;
                case 2:
                    shop.trierStock(ModeTris.PRIX_DECROISSANT);
                    break;
                case 3:
                    shop.trierStock(ModeTris.NOM_CROISSANT);
                    break;
                case 4:
                    shop.trierStock((ModeTris.NOM_DECROISSANT));
                    break;
                case 0:
                    System.out.println("Retour...");
                    continue;
                default:
                    System.out.println("Choix invalide");
            }

            System.out.println("\n=== Boutique Triée ===");
            for(Stockables s : shop.getStock()) {
                System.out.println(s.getNom() + " - " + s.getPrix() + " zénis");
            }
        }

        sc.close();

    }
}
//system.out.prim  pour afficher