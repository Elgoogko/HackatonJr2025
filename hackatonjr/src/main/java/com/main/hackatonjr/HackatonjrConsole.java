package com.main.hackatonjr;

import java.util.ArrayList;
import java.util.Scanner;

public class HackatonjrConsole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Menu menu = new Menu();

        Carte carte = new Carte();

        Gestionnaire_Events events = new Gestionnaire_Events(carte);

        Vetement haut = new Vetement(-1,0, TYPE_VETEMENT.Haut, "none", 0);
        Vetement bas = new Vetement(-2,0, TYPE_VETEMENT.Bas, "none", 0);
        Vetement tete = new Vetement(-3,0, TYPE_VETEMENT.Tete, "none", 0);

        Tenue tenue = new Tenue(haut, bas, tete);
        Vehicules vehicule = new Vehicules(-1,20, "Pieds", TYPE.Pieds, 0);
        Lieu lieu = carte.getLieux().get(0);

        Utilisateur utilisateur = new Utilisateur("", 100000.0f, 100, new ArrayList<>(), 0, 20, tenue, vehicule, lieu);

        Boutique boutique = new Boutique(utilisateur);
        int choix = -1, choix2 = -1, choix3 = -1, indice = -1, indice2 = -1, reponse = -1, taille = 0;
        final int QUITTER = 0, RETOUR = 1, INVENTAIRE = 1, BOUTIQUE = 2, PROFIL = 3, CARTE = 4, EVENEMENT = 5, TRIPRIXCROISSANT = 2, TRIPRIXDECROISSANT = 3, TRINOMCROISSANT = 4, TRINOMDECROISSANT = 5, ACHETER = 6, EQUIPER = 2, DEPLACEMENT = 2, OUI = 1, NON = 0;
        boolean verif;
        String nomEquipement;

        ArrayList<Lieu> lieux = new ArrayList<Lieu>();

        Evenement event = null;

        boolean mort;

        float argent = 0;


        menu.afficheCreation(utilisateur,sc);

        menu.afficheBienvenue(utilisateur,sc);

        while(utilisateur.getFaim()<100){
            choix2 = -1;
            menu.afficheMenu();

            do{
                System.out.print("--> Votre choix : ");
                choix = menu.choix(sc);
                switch (choix) {
                    case INVENTAIRE:
                        menu.afficheInventaire(utilisateur);

                        do{
                            System.out.print("--> Votre choix : ");
                            choix2 = menu.choix(sc);
                            
                            switch(choix2){
                                case EQUIPER:
                                    if(utilisateur.getInventaire().size() <= 0){
                                        System.out.println("Vous n'avez aucun élément à équiper !");
                                    }
                                    else{
                                        do{
                                            System.out.print("Donner le numéro de l'élément à équiper : ");
                                            indice = menu.choix(sc);
                                            taille = utilisateur.getInventaire().size();
                                        }while(indice < 1 || indice > utilisateur.getInventaire().size());
                                        nomEquipement = utilisateur.getInventaire().get(indice - 1).getNom();
                                        verif = utilisateur.equiper(utilisateur.getInventaire().get(indice - 1));
                                        if(verif == true){
                                            System.out.print("L'objet " + nomEquipement + " a été ");
                                        }
                                        else{
                                            System.out.print("L'objet " + nomEquipement + " est déjà ");
                                        }
                                        if(taille == utilisateur.getInventaire().size()){
                                            System.out.println("équipé");
                                        }
                                        else{
                                            System.out.println("consommé");
                                        }
                                    }
                                    break;
                                case RETOUR:
                                    break;
                                case QUITTER:
                                    System.out.println("\nFin du programme...\n");
                                    return ;
                            }
                        }while(choix2 != RETOUR && choix2 != EQUIPER);

                        break;
                    case BOUTIQUE:

                        do{
                            menu.afficheBoutique(boutique);

                            System.out.print("--> Votre choix : ");
                            choix2 = menu.choix(sc);
                            switch (choix2) {
                                case TRIPRIXCROISSANT:
                                    boutique.trierStock(ModeTris.PRIX_CROISSANT);
                                    break;
                                case TRIPRIXDECROISSANT:
                                    boutique.trierStock(ModeTris.PRIX_DECROISSANT);
                                    break;
                                case TRINOMCROISSANT:
                                    boutique.trierStock(ModeTris.NOM_CROISSANT);
                                    break;
                                case TRINOMDECROISSANT:
                                    boutique.trierStock((ModeTris.NOM_DECROISSANT));
                                    break;
                                case ACHETER:
                                    do{
                                        System.out.print("Donner le numéro de l'élément à acheter : ");
                                        indice = menu.choix(sc);
                                    }while(indice<1 || indice>boutique.getStock().size());
                                    boutique.ajouterPanier(boutique.getStock().get(indice-1));

                                    do{
                                        do{
                                            System.out.print("Voulez-vous rajouter un élément ? (1 : Oui / 0 : Non) : ");
                                            reponse = menu.choix(sc);
                                        }while(reponse != 1 && reponse != 0);
                                        if(reponse == 1){
                                            do{
                                                System.out.print("Donner le numéro de l'élément à rajouter au panier : ");
                                                indice = menu.choix(sc);
                                            }while(indice<1 || indice>boutique.getStock().size() || (!(boutique.getStock().get(indice-1) instanceof Capsule) && boutique.estDansPanier(boutique.getStock().get(indice-1))));
                                            boutique.ajouterPanier(boutique.getStock().get(indice-1));
                                        }
                                    }while(reponse == 1);

                                    verif = boutique.acheterPanier();
                                    boutique.viderPanier();
                                    if(verif == true){
                                        System.out.println("L'achat a été effectué avec succés");
                                    }
                                    else{
                                        System.out.println("Vous n'avez pas assez d'argent pour effectuer l'achat");
                                    }
                                    
                                    break;
                                case RETOUR:
                                    break;
                                case QUITTER:
                                    System.out.println("\nFin du programme...\n");
                                    return ;
                            }
                        }while(choix2 != RETOUR);
                        break;

                    case PROFIL:

                        menu.afficheProfil(utilisateur);
                        do{
                            System.out.print("--> Votre choix : ");

                            choix2 = menu.choix(sc);
                            switch(choix2){
                                case RETOUR:
                                    break;
                                case QUITTER:
                                    System.out.println("\nFin du programme...\n");
                                    return ;
                            }
                        }while(choix2 != RETOUR);
                        break;

                    case CARTE:

                        menu.afficheCarte(carte,utilisateur);
                        do{
                            System.out.print("--> Votre choix : ");

                            choix2 = menu.choix(sc);
                            switch(choix2){
                                case RETOUR:
                                    break;
                                case QUITTER:
                                    System.out.println("\nFin du programme...\n");
                                    return ;
                                case DEPLACEMENT:
                                    do{
                                        System.out.print("----> Donnez le numéro du lieu sur lequel vous voulez aller : ");
                                        indice = menu.choix(sc);
                                    }while(indice < 1 || indice > carte.getLieux().size());
                                    do{
                                        System.out.print("----> Voulez-vous passer par un lieu spécifique ? (1 : Oui / 0 : Non) : ");
                                        choix3 = menu.choix(sc);
                                    }while(choix3 != NON && choix3 != OUI);

                                    if(utilisateur.getVehicules().getType() == TYPE.Pieds){
                                        System.out.println("\n!!! Vous ne pouvez pas y aller à pieds");
                                        break;
                                    }

                                    if(choix3 == OUI){
                                        do{
                                            System.out.print("----> Donnez le numéro du lieu par lequel vous voulez passer : ");
                                            indice2 = menu.choix(sc);
                                        }while(indice2 < 1 || indice2 > carte.getLieux().size());
                                        lieux = carte.plusCourtCheminAvecEtape(utilisateur.getLieuActuel(), carte.getLieux().get(indice2 - 1), carte.getLieux().get(indice - 1), utilisateur.getVehicules().getType());
                                    }
                                    else{
                                        lieux = carte.plusCourtChemin(utilisateur.getLieuActuel(), carte.getLieux().get(indice - 1), utilisateur.getVehicules().getType());
                                    }

                                    if(lieux.isEmpty()){
                                        System.out.println("\n!!! Vous n'avez pas pu accéder à " + carte.getLieux().get(indice - 1).getNom() + "\nCause : Aucun chemin ne ramene vers la bas");
                                    }
                                    else{
                                        Float distanceParourue = carte.totalDistanceChemin(lieux, utilisateur.getVehicules().getType());
                                        Float tempsTrajet = utilisateur.getVehicules().getTempsTrajet(distanceParourue);
                                        int faim = (int) ((distanceParourue*100)/carte.totalDistanceChemin(carte.getLieux(),TYPE.Voiture));
                                        argent = 500*lieux.size();
                                        if(faim + utilisateur.getFaim() >= 100){
                                            System.out.println("\n!!! Vous n'avez pas pu accéder à " + carte.getLieux().get(indice - 1).getNom() + "\nCause : Vous avez " + utilisateur.getFaim() + " % de faim et ce trajet le fera monter a " + (faim + utilisateur.getFaim()) + " % causant votre mort");
                                        }
                                        else{
                                            System.out.println("\n- Vous êtes passé par les lieux suivants (Distance parcourue = " + distanceParourue + " / Temps du trajet = " + tempsTrajet + ") : ");
                                            for(Lieu l : lieux){
                                                System.out.println("--- " + l.getNom());
                                            }
                                            utilisateur.setLieuActuel(carte.getLieux().get(indice - 1));
                                            System.out.println("\n- Le trajet a augmente votre faim de " + utilisateur.getFaim() + " % a " + (faim + utilisateur.getFaim()) + " %");
                                            utilisateur.ajouterFaim(faim);
                                            System.out.println("\n- Vous avez gagné " + argent + " zenis lors de votre voyage");
                                            utilisateur.ajouterArgent(argent);
                                        }
                                    }
                                    System.out.println("\n- Vous êtes actuellement à : " + utilisateur.getLieuActuel().getNom());
                                    break;
                            }
                        }while(choix2 != RETOUR && choix2 != DEPLACEMENT);
                        break;

                    case EVENEMENT:
                        menu.afficheEvenement(events,utilisateur);
                        event = events.getRandomEvent();
                        
                        switch(event.getType()){
                            case HISTOIRE:
                                System.out.println("\u001B[32m" + "[HISTOIRE] " + event.getDescription() + "\u001B[0m");
                                if(events.getHistoires().size()<=0){
                                    System.out.println("!!! Bravo, Vous avez survécu\n");
                                    return;
                                }
                                break;

                            case DANGER:
                                System.out.println("\u001B[31m" + "[DANGER] " + event.getDescription() + "\u001B[0m");
                                System.out.print("- Lieux touchés : ");
                                for(Lieu l : event.getLieuxCibles()){
                                    System.out.print(l.getNom() + ", ");
                                }
                                do{
                                    System.out.print("\n- Vous vous trouvez actuellement à " + utilisateur.getLieuActuel().getNom() + " , voulez-vous fuir ? (1 : Oui / 0 : Non) : ");
                                    choix2 = menu.choix(sc);
                                }while(choix2 != NON && choix2 != OUI);

                                if(choix2 == NON){
                                    System.out.println("!!! Vous avez décidé de ne pas fuir");
                                }
                                else{
                                    do{
                                        carte.afficherLieux(utilisateur);
                                        carte.afficherChemins(utilisateur);
                                        carte.afficher10Lieux(utilisateur);
                                        System.out.print("\n-> Donnez le numéro du lieu où vous voulez fuir : ");
                                        indice = menu.choix(sc);
                                    }while(indice < 1 || indice > carte.getLieux().size());

                                    verif = true;

                                    if(utilisateur.getVehicules().getType() == TYPE.Pieds){
                                        System.out.println("!!! Vous ne pouvez pas y aller à pieds");
                                        verif = false;
                                    }

                                    if(verif){
                                        lieux = carte.plusCourtChemin(utilisateur.getLieuActuel(), carte.getLieux().get(indice - 1), utilisateur.getVehicules().getType());
                                    }

                                    if(lieux.isEmpty()){
                                        System.out.println("!!! Vous ne pouvez pas accéder à ce lieu");
                                    }
                                    else{
                                        Float distanceParourue = carte.totalDistanceChemin(lieux, utilisateur.getVehicules().getType());
                                        Float tempsTrajet = utilisateur.getVehicules().getTempsTrajet(distanceParourue);
                                        int faim = (int) ((distanceParourue*100)/carte.totalDistanceChemin(carte.getLieux(),TYPE.Voiture));
                                        argent = 500*lieux.size();
                                        if(faim + utilisateur.getFaim() >= 100){
                                            System.out.println("\n!!! Vous n'avez pas pu accéder à " + carte.getLieux().get(indice - 1).getNom() + "\nCause : Vous avez " + utilisateur.getFaim() + " % de faim et ce trajet le fera monter a " + (faim + utilisateur.getFaim()) + " % causant votre mort");
                                        }
                                        else{
                                            System.out.println("\n- Vous êtes passé par les lieux suivants (Distance parcourue = " + distanceParourue + " / Temps du trajet = " + tempsTrajet + ") : ");
                                            for(Lieu l : lieux){
                                                System.out.println("--- " + l.getNom());
                                            }
                                            utilisateur.setLieuActuel(carte.getLieux().get(indice - 1));
                                            System.out.println("\n- Le trajet a augmente votre faim de " + utilisateur.getFaim() + " % a " + (faim + utilisateur.getFaim()) + " %");
                                            utilisateur.ajouterFaim(faim);
                                            System.out.println("\n- Vous avez gagné " + argent + " zenis lors de votre voyage");
                                            utilisateur.ajouterArgent(argent);
                                        }
                                    }
                                    System.out.println("\n- Vous êtes actuellement à : " + utilisateur.getLieuActuel().getNom());
                                }

                                mort = events.effetEvenement(event, utilisateur);

                                if(mort == true){
                                    System.out.println("!!! Vous etes mort\n");
                                    return ;
                                }
                                else{
                                    System.out.println("!!! Vous avez evite la mort");
                                }

                                break;

                            case BONUS:
                                argent = utilisateur.getArgent();
                                mort = events.effetEvenement(event, utilisateur);
                                System.out.println("\u001B[32m" + "[BONUS] " + event.getDescription() + (utilisateur.getArgent() - argent) + " zenis" + "\u001B[0m");
                                break;

                            case MALLUS:
                                argent = utilisateur.getArgent();
                                mort = events.effetEvenement(event, utilisateur);
                                System.out.println("\u001B[31m" + "[MALLUS] " + event.getDescription() + (argent - utilisateur.getArgent()) + " zenis" + "\u001B[0m");
                                break;

                            case ATTAQUE:
                                System.out.println("\u001B[31m" + "[ATTAQUE] " + event.getDescription() + "\u001B[0m");
                                mort = events.effetEvenement(event, utilisateur);
                                if(mort == false){
                                    System.out.println("!!! Vous avez survecu a l'attaque mais votre tenue a été détruite");
                                }
                                else{
                                    System.out.println("!!! Vous etes mort\n");
                                    return ;
                                }
                                break;
                        }
                        break;

                    case QUITTER:
                        System.out.println("\nFin du programme...\n");
                        return ;
                }

            }while(choix2 != RETOUR && choix2 != EQUIPER && choix2 != DEPLACEMENT && choix != EVENEMENT);
        }

        sc.close();
    }
}
