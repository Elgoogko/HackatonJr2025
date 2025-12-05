package com.main.hackatonjr;
import com.main.*;

import java.util.ArrayList;
import java.util.List;
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
        System.out.println("Votre personnage a comme véhicule : " + user.getVehicules().getNom() + " ");
        System.out.println("Vous commencez avec " + user.getArgent() + " zénis\n");

        
        Boutique shop = new Boutique();
        for(Stockables s : shop.getStock()){
            System.out.println(s.getNom()  + " " + s.getPrix() + " zénis" +"\n");
        }
        /*int choix = -1;
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
        */
        ArrayList<Chemin> chemins1 = new ArrayList<>();
        ArrayList<Chemin> chemins2 = new ArrayList<>();
        ArrayList<Chemin> chemins3 = new ArrayList<>();

        Lieu village = new Lieu(1, "Village de Moori", 25,
                "Village paisible de Namek.", null, chemins1,
                new Coordonnees(100, 900));

        Lieu lac = new Lieu(2, "Lac de Namek", 22,
                "Grande étendue d'eau bleue.", null, chemins2,
                new Coordonnees(300, 700));

        Lieu plateau = new Lieu(3, "Plateau rocheux", 30,
                "Zone de combat fréquente.", null, chemins3,
                new Coordonnees(500, 500));


        // Position de départ du joueur
        Lieu positionJoueur = village;

        // Gestionnaire d'événements
        Gestionnaire_Events gestionnaire = new Gestionnaire_Events();

        // Évènement HISTOIRE après 10 s
        gestionnaire.ajouter(Evenement.histoire(
                10,
                "Les Nameks vivent paisiblement sous le ciel vert de Namek..."
        ));

        List<Lieu> tousLesLieux = List.of(village, plateau, lac);
        Lieu cible = tousLesLieux.get( (int)(Math.random() * tousLesLieux.size()));
        // Évènement DANGER : Freezer arrive après 30 s dans 1 zone
        gestionnaire.ajouter(Evenement.danger(
                30,
                "Freezer",
                "Freezer débarque sur Namek et commence à raser des villages.",
                List.of(cible)
        ));

        // Timer de partie (en secondes)
        int dureePartieSec = 90;
        long start = System.currentTimeMillis();

        boolean fini = false;

        while (!fini) {

            long now = System.currentTimeMillis();
            int tempsEcouleSec = (int) ((now - start) / 1000);
            int tempsRestant = dureePartieSec - tempsEcouleSec;

            if (tempsRestant <= 0) {
                System.out.println("Temps écoulé, la partie est terminée !");
                break;
            }

            gestionnaire.update(tempsEcouleSec, positionJoueur);

            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("Temps restant : " + tempsRestant + " s");
            System.out.println("Lieu actuel : " + positionJoueur.getNom());
            System.out.println("1 - Aller à la boutique");
            System.out.println("2 - Se déplacer");
            System.out.println("0 - Quitter");
            System.out.print("Votre choix : ");

            int choix;
            try {
                choix = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide.");
                continue;
            }

            switch (choix) {

                case 1:
                    int choixBoutique = -1;
                    while (choixBoutique != 0) {
                        System.out.println("\n=== MENU BOUTIQUE ===");
                        System.out.println("1 - Trier par prix croissant");
                        System.out.println("2 - Trier par prix décroissant");
                        System.out.println("3 - Trier par nom croissant (A->Z)");
                        System.out.println("4 - Trier par nom décroissant (Z->A)");
                        System.out.println("0 - Quitter la boutique");
                        System.out.print("Votre choix : ");

                        try {
                            choixBoutique = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Entrée invalide.");
                            continue;
                        }

                        switch (choixBoutique) {
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
                                shop.trierStock(ModeTris.NOM_DECROISSANT);
                                break;
                            case 0:
                                System.out.println("Vous quittez la boutique.");
                                continue;
                            default:
                                System.out.println("Choix invalide");
                        }

                        System.out.println("\n=== Boutique Triée ===");
                        for (Stockables s : shop.getStock()) {
                            System.out.println(s.getNom() + " - " + s.getPrix() + " zénis");
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n=== LIEUX DISPONIBLES ===");
                    afficherLieuChoix(village);
                    afficherLieuChoix(lac);
                    afficherLieuChoix(plateau);
                    System.out.print("Aller où ? (id, 0 pour annuler) : ");

                    int idDest;
                    try {
                        idDest = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide.");
                        break;
                    }

                    if (idDest == 0) {
                        break;
                    }

                    Lieu destination = null;
                    if (idDest == village.getId()) destination = village;
                    if (idDest == lac.getId()) destination = lac;
                    if (idDest == plateau.getId()) destination = plateau;

                    if (destination == null) {
                        System.out.println("Ce lieu n'existe pas.");
                    } else if (destination.estCondamne()) {
                        System.out.println("⚠ Ce lieu est condamné, vous ne pouvez pas y aller.");
                    } else {
                        positionJoueur = destination;
                        System.out.println("Vous vous déplacez vers : " + positionJoueur.getNom());
                    }
                    break;

                case 0:
                    fini = true;
                    break;

                default:
                    System.out.println("Choix invalide.");
            }

            if (positionJoueur.estCondamne()) {
                System.out.println("💀 Vous êtes dans une zone condamnée... Game Over.");
                fini = true;
            }
        }

        sc.close();
        System.out.println("Fin du test.");
    }

    private static void afficherLieuChoix(Lieu l) {
        if (l.estCondamne()) {
            System.out.println(l.getId() + " - " + l.getNom() + " (CONDAMNÉ)");
        } else {
            System.out.println(l.getId() + " - " + l.getNom());
        }
    }

    
}
//system.out.prim  pour afficher