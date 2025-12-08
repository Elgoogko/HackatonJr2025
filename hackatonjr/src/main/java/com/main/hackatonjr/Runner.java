package com.main.hackatonjr;
import com.main.hackatonjr.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

@Component
public class Runner {
    public void start() {

        Scanner sc = new Scanner(System.in);

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Carte carte = new Carte();

        Gestionnaire_Events events = new Gestionnaire_Events(carte);

        Vetement haut = new Vetement(-1,0, TYPE_VETEMENT.Haut, "none", 0);
        Vetement bas = new Vetement(-2,0, TYPE_VETEMENT.Bas, "none", 0);
        Vetement tete = new Vetement(-3,0, TYPE_VETEMENT.Tete, "none", 0);

        Tenue tenue = new Tenue(haut, bas, tete);
        Vehicules vehicule = new Vehicules(-1,20, "pieds", TYPE.Pieds, 0);
        Lieu lieu = carte.getLieux().get(0);

        Utilisateur utilisateur = new Utilisateur("", 100000.0f, 100, new ArrayList<>(), 100, 20, tenue, vehicule, lieu);
        
        System.out.println("\n=== CREATION ===");
        do{
            System.out.print("Donnez votre pseudo (Maximum 10 caractères) : ");
            utilisateur.setNom(sc.nextLine());
        }while(utilisateur.getNom().length() > 10 || utilisateur.getNom() == "");

        System.out.println("\n=== BIENVENUE " + utilisateur.getNom() + " ===");
        System.out.println("Votre personnage porte une tenue composé de " + utilisateur.getTenue().getBas().getNom() + ", " + utilisateur.getTenue().getHaut().getNom() + " et " + utilisateur.getTenue().getTete().getNom());
        System.out.println("Votre personnage a comme véhicule : " + utilisateur.getVehicules().getNom());
        System.out.println("Vous commencez avec " + utilisateur.getArgent() + " zénis");

        Boutique boutique = new Boutique(utilisateur);
        int choix = -1, choix2 = -1, choix3 = -1, choix4 = -1, choix5 = -1, choix6 = -1, choix7 = -1, choix8 = -1, indice = -1, indice2 = -1, reponse = -1, taille = 0;
        boolean verif;
        String nomEquipement;

        ArrayList<Lieu> lieux = new ArrayList<Lieu>();
        ModeTransport transport = null;

        while(choix != 0){
            choix5 = -1;
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Ouvrir l'inventaire");
            System.out.println("2 - Ouvrir la boutique");
            System.out.println("3 - Mon profil");
            System.out.println("4 - Ouvrir la carte");
            System.out.println("5 - Voir les evenements");
            System.out.println("0 - Quitter");

            do{
                System.out.print("Votre choix : ");
                choix = Integer.parseInt(sc.nextLine());
                switch (choix) {
                    case 1:
                        System.out.println("\n=== INVENTAIRE ===");
                        utilisateur.afficherInventaire();
                        System.out.println("\n1 - Equiper");
                        System.out.println("2 - Retour");
                        System.out.println("0 - Quitter");

                        do{
                            System.out.print("Votre choix : ");

                            choix2 = Integer.parseInt(sc.nextLine());
                            switch(choix2){
                                case 1:
                                    if(utilisateur.getInventaire().size() <= 0){
                                        System.out.println("Vous n'avez aucun élément à équiper !");
                                    }
                                    else{
                                        do{
                                            System.out.print("Donner le numéro de l'élément à équiper : ");
                                            indice = Integer.parseInt(sc.nextLine());
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
                                case 2:
                                    choix = -1;
                                    break;
                                case 0:
                                    System.out.println("\nFin du programme...\n");
                                    return ;
                                default:
                                    System.out.println("Choix invalide");
                            }
                        }while(choix2 != 0 && choix2 != 1 && choix2 != 2);

                        break;
                    case 2:

                        do{

                            System.out.println("\n=== MENU BOUTIQUE ===\n");
                            boutique.afficherBoutique();
                            System.out.println("\n1 - Trier par prix croissant");
                            System.out.println("2 - Trier par prix décroissant");
                            System.out.println("3 - Trier par nom croissant (A->Z)");
                            System.out.println("4 - Trier par nom décroissant (Z->A)");
                            System.out.println("5 - Acheter un élément");
                            System.out.println("6 - Retour");
                            System.out.println("0 - Quitter");
                            System.out.print("Votre choix : ");

                            choix3 = Integer.parseInt(sc.nextLine());
                            switch (choix3) {
                                case 1:
                                    boutique.trierStock(ModeTris.PRIX_CROISSANT);
                                    break;
                                case 2:
                                    boutique.trierStock(ModeTris.PRIX_DECROISSANT);
                                    break;
                                case 3:
                                    boutique.trierStock(ModeTris.NOM_CROISSANT);
                                    break;
                                case 4:
                                    boutique.trierStock((ModeTris.NOM_DECROISSANT));
                                    break;
                                case 5:
                                    do{
                                        System.out.print("Donner le numéro de l'élément à acheter : ");
                                        indice = Integer.parseInt(sc.nextLine());
                                    }while(indice<1 || indice>boutique.getStock().size());
                                    boutique.ajouterPanier(boutique.getStock().get(indice-1));

                                    do{
                                        do{
                                            System.out.print("Voulez-vous rajouter un élément ? (1 : Oui / 0 : Non) : ");
                                            reponse = Integer.parseInt(sc.nextLine());
                                        }while(reponse != 1 && reponse != 0);
                                        if(reponse == 1){
                                            do{
                                                System.out.print("Donner le numéro de l'élément à rajouter au panier : ");
                                                indice = Integer.parseInt(sc.nextLine());
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
                                case 6:
                                    break;
                                case 0:
                                    System.out.println("\nFin du programme...\n");
                                    return ;
                                default:
                                    System.out.println("Choix invalide");
                            }
                        }while(choix3 != 6);

                        break;
                    case 3:
                        System.out.println("\n=== Profil ===");
                        utilisateur.afficherProfil();
                        System.out.println("1 - Retour");
                        System.out.println("0 - Quitter");

                        do{
                            System.out.print("Votre choix : ");

                            choix4 = Integer.parseInt(sc.nextLine());
                            switch(choix4){
                                case 1:
                                    choix = -1;
                                    break;
                                case 0:
                                    System.out.println("\nFin du programme...\n");
                                    return ;
                                default:
                                    System.out.println("Choix invalide");
                            }
                        }while(choix4 != 0 && choix4 != 1);
                        break;
                    case 4:
                        System.out.println("\n=== Carte ===");
                        carte.afficherLieux();
                        carte.afficherChemins();
                        System.out.println("1 - Retour");
                        System.out.println("2 - Deplacement");
                        System.out.println("0 - Quitter");

                        do{
                            System.out.print("Votre choix : ");

                            choix5 = Integer.parseInt(sc.nextLine());
                            switch(choix5){
                                case 1:
                                    choix = -1;
                                    break;
                                case 0:
                                    System.out.println("\nFin du programme...\n");
                                    return ;
                                case 2:
                                    do{
                                        System.out.print("Donnez le numéro du lieu sur lequel vous voulez aller : ");
                                        indice = Integer.parseInt(sc.nextLine());
                                    }while(indice < 1 || indice > carte.getLieux().size());
                                    do{
                                        System.out.print("Voulez-vous passer par un lieu spécifique ? (1 : Oui / 0 : Non) : ");
                                        choix6 = Integer.parseInt(sc.nextLine());
                                    }while(choix6 != 0 && choix6 != 1);
                                    if(utilisateur.getVehicules().getType() == TYPE.Voiture){
                                        transport = ModeTransport.VOITURE;
                                    }
                                    else if(utilisateur.getVehicules().getType() == TYPE.Nuage){
                                        transport = ModeTransport.NUAGE;
                                    }
                                    else if(utilisateur.getVehicules().getType() == TYPE.Pilier){
                                        transport = ModeTransport.PILIER;
                                    }
                                    else{
                                        System.out.println("Vous ne pouvez pas y aller à pieds");
                                        break;
                                    }
                                    if(choix6 == 1){
                                        do{
                                            System.out.print("Donnez le numéro du lieu par lequel vous voulez passer : ");
                                            indice2 = Integer.parseInt(sc.nextLine());
                                        }while(indice2 < 1 || indice2 > carte.getLieux().size());
                                        lieux = carte.plusCourtCheminAvecEtape(utilisateur.getLieuActuel(), carte.getLieux().get(indice2 - 1), carte.getLieux().get(indice - 1), transport);
                                    }
                                    else{
                                        lieux = carte.plusCourtChemin(utilisateur.getLieuActuel(), carte.getLieux().get(indice - 1), transport);
                                    }
                                    if(lieux.isEmpty()){
                                        System.out.println("Vous n'avez pas pu accéder à " + carte.getLieux().get(indice - 1).getNom());
                                    }
                                    else{
                                        Float distanceParourue = carte.totalDistanceChemin(lieux, transport);
                                        Float tempsTrajet = utilisateur.getVehicules().getTempsTrajet(distanceParourue);
                                        System.out.println("Vous êtes passé par les lieux suivants (Distance parcourue = " + distanceParourue + " / Temps du trajet = " + tempsTrajet + ") : ");
                                        for(Lieu l : lieux){
                                            System.out.println("->" + l.getNom());
                                        }
                                        utilisateur.setLieuActuel(carte.getLieux().get(indice - 1));
                                    }
                                    System.out.println("Vous êtes à " + utilisateur.getLieuActuel().getNom());
                                    break;
                                default:
                                    System.out.println("Choix invalide");
                            }
                        }while(choix5 != 0 && choix5 != 1 && choix5 != 2);
                        break;
                    case 5:
                        System.out.println("\n=== EVENEMENT ===");
                        if(ThreadLocalRandom.current().nextInt(0,2) == 0 || events.getDangers().size() <= 0){
                            System.out.println("[HISTOIRE] " + events.getHistoires().get(0).getDescription());
                            events.getHistoires().remove(0);
                            if(events.getHistoires().size() <= 0){
                                System.out.println("\nVOUS AVEZ SURVECU !!!\n\n");
                                return ;
                            }
                            break;
                        }
                        else{
                            System.out.println("[DANGER] " + events.getDangers().get(0).getDescription());
                            System.out.print("Lieux touchés : ");
                            for(Lieu l : events.getDangers().get(0).getLieuxCibles()){
                                System.out.print(l.getNom() + ", ");
                            }
                            do{
                                System.out.print("\nVoulez-vous fuir ? (1 : Oui / 0 : Non) : ");
                                choix7 = Integer.parseInt(sc.nextLine());
                            }while(choix7 != 0 && choix7 != 1);

                            if(choix7 == 0){
                                System.out.println("Vous avez décidé de ne pas fuir");
                            }
                            else{
                                do{
                                    System.out.print("Donnez le numéro du lieu où vous voulez fuir : ");
                                    indice = Integer.parseInt(sc.nextLine());
                                }while(indice < 1 || indice > carte.getLieux().size());

                                verif = true;
                                if(utilisateur.getVehicules().getType() == TYPE.Voiture){
                                    transport = ModeTransport.VOITURE;
                                }
                                else if(utilisateur.getVehicules().getType() == TYPE.Nuage){
                                    transport = ModeTransport.NUAGE;
                                }
                                else if(utilisateur.getVehicules().getType() == TYPE.Pilier){
                                    transport = ModeTransport.PILIER;
                                }
                                else{
                                    System.out.println("Vous ne pouvez pas y aller à pieds");
                                    verif = false;
                                }
                                if(verif){
                                    lieux = carte.plusCourtChemin(utilisateur.getLieuActuel(), carte.getLieux().get(indice - 1), transport);
                                }
                                if(lieux.isEmpty()){
                                    System.out.println("Vous ne pouvez pas accéder à ce lieu");
                                }
                                else{
                                    Float distanceParourue = carte.totalDistanceChemin(lieux, transport);
                                    Float tempsTrajet = utilisateur.getVehicules().getTempsTrajet(distanceParourue);
                                    System.out.println("Vous êtes passé par les lieux suivants (Distance parcourue = " + distanceParourue + " / Temps du trajet = " + tempsTrajet + ") : ");
                                    for(Lieu l : lieux){
                                        System.out.println("->" + l.getNom());
                                    }
                                    utilisateur.setLieuActuel(carte.getLieux().get(indice - 1));
                                    System.out.println("Vous êtes à " + utilisateur.getLieuActuel().getNom());
                                }
                            }

                            for(Lieu l : events.getDangers().get(0).getLieuxCibles()){
                                l.setCondamne(true);
                            }
                            events.getDangers().remove(0);

                            if(utilisateur.getLieuActuel().estCondamne()){
                                System.out.println("Vous êtes morts");
                                return ;
                            }

                        }

                        break;
                    case 0:
                        System.out.println("\nFin du programme...\n");
                        return ;
                    default:
                        System.out.println("Choix invalide");
                }
                if((choix == -1 && choix2 == 2) || (choix == -1 && choix4 == 1) || (choix == -1 && choix5 == 1) || choix5 != 2){
                    break;
                }
            }while(choix != 0 && choix != 1 && choix != 2 && choix != 3 && choix != 4 && choix != 5);
        }

        sc.close();

    }
}
