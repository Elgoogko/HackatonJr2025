package com.main.hackatonjr;
import com.main.hackatonjr.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

@Component
public class Runner {
    public int choix(Scanner sc){
        String chaine = sc.nextLine();
        if(chaine == null || chaine.isEmpty()){
            return -1;
        }
        try{
            return Integer.parseInt(chaine);
        }
        catch(NumberFormatException e){
            return -1;
        }        
    }

    public void start() {

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
        ModeTransport transport = null;


        menu.afficheCreation(utilisateur,sc);

        menu.afficheBienvenue(utilisateur,sc);

        while(utilisateur.getFaim()<100){
            choix2 = -1;
            menu.afficheMenu();

            do{
                System.out.print("--> Votre choix : ");
                choix = choix(sc);
                switch (choix) {
                    case INVENTAIRE:
                        menu.afficheInventaire(utilisateur);

                        do{
                            System.out.print("--> Votre choix : ");
                            choix2 = choix(sc);
                            
                            switch(choix2){
                                case EQUIPER:
                                    if(utilisateur.getInventaire().size() <= 0){
                                        System.out.println("Vous n'avez aucun élément à équiper !");
                                    }
                                    else{
                                        do{
                                            System.out.print("Donner le numéro de l'élément à équiper : ");
                                            indice = choix(sc);
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
                            choix2 = choix(sc);
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
                                        indice = choix(sc);
                                    }while(indice<1 || indice>boutique.getStock().size());
                                    boutique.ajouterPanier(boutique.getStock().get(indice-1));

                                    do{
                                        do{
                                            System.out.print("Voulez-vous rajouter un élément ? (1 : Oui / 0 : Non) : ");
                                            reponse = choix(sc);
                                        }while(reponse != 1 && reponse != 0);
                                        if(reponse == 1){
                                            do{
                                                System.out.print("Donner le numéro de l'élément à rajouter au panier : ");
                                                indice = choix(sc);
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

                            choix2 = choix(sc);
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

                            choix2 = choix(sc);
                            switch(choix2){
                                case RETOUR:
                                    break;
                                case QUITTER:
                                    System.out.println("\nFin du programme...\n");
                                    return ;
                                case DEPLACEMENT:
                                    do{
                                        System.out.print("----> Donnez le numéro du lieu sur lequel vous voulez aller : ");
                                        indice = choix(sc);
                                    }while(indice < 1 || indice > carte.getLieux().size());
                                    do{
                                        System.out.print("----> Voulez-vous passer par un lieu spécifique ? (1 : Oui / 0 : Non) : ");
                                        choix3 = choix(sc);
                                    }while(choix3 != NON && choix3 != OUI);
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
                                        System.out.println("\n!!! Vous ne pouvez pas y aller à pieds");
                                        break;
                                    }

                                    if(choix3 == OUI){
                                        do{
                                            System.out.print("----> Donnez le numéro du lieu par lequel vous voulez passer : ");
                                            indice2 = choix(sc);
                                        }while(indice2 < 1 || indice2 > carte.getLieux().size());
                                        lieux = carte.plusCourtCheminAvecEtape(utilisateur.getLieuActuel(), carte.getLieux().get(indice2 - 1), carte.getLieux().get(indice - 1), transport);
                                    }
                                    else{
                                        lieux = carte.plusCourtChemin(utilisateur.getLieuActuel(), carte.getLieux().get(indice - 1), transport);
                                    }

                                    if(lieux.isEmpty()){
                                        System.out.println("\n!!! Vous n'avez pas pu accéder à " + carte.getLieux().get(indice - 1).getNom() + "\nCause : Aucun chemin ne ramene vers la bas");
                                    }
                                    else{
                                        Float distanceParourue = carte.totalDistanceChemin(lieux, transport);
                                        Float tempsTrajet = utilisateur.getVehicules().getTempsTrajet(distanceParourue);
                                        int faim = (int) ((distanceParourue*100)/carte.totalDistanceChemin(carte.getLieux(),ModeTransport.VOITURE));
                                        float argent = 500*lieux.size();
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
                        if(ThreadLocalRandom.current().nextInt(0,2) == 0 || events.getDangers().size() <= 0){
                            System.out.println("\u001B[32m" + "[HISTOIRE] " + events.getHistoires().get(0).getDescription() + "\u001B[0m");
                            events.getHistoires().remove(0);
                            if(events.getHistoires().size() <= 0){
                                System.out.println("\nVOUS AVEZ SURVECU !!!\n\n");
                                return ;
                            }
                            break;
                        }
                        else{
                            System.out.println("\u001B[31m" + "[DANGER] " + events.getDangers().get(0).getDescription() + "\u001B[0m");
                            System.out.print("- Lieux touchés : ");
                            for(Lieu l : events.getDangers().get(0).getLieuxCibles()){
                                System.out.print(l.getNom() + ", ");
                            }
                            do{
                                System.out.print("\n- Vous vous trouvez actuellement à " + utilisateur.getLieuActuel().getNom() + " , voulez-vous fuir ? (1 : Oui / 0 : Non) : ");
                                choix2 = choix(sc);
                            }while(choix2 != 0 && choix2 != 1);

                            if(choix2 == 0){
                                System.out.println("!!! Vous avez décidé de ne pas fuir");
                            }
                            else{
                                do{
                                    carte.afficherLieux(utilisateur);
                                    carte.afficherChemins(utilisateur);
                                    carte.afficher10Lieux(utilisateur);
                                    System.out.print("\n-> Donnez le numéro du lieu où vous voulez fuir : ");
                                    indice = choix(sc);
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
                                    System.out.println("!!! Vous ne pouvez pas y aller à pieds");
                                    verif = false;
                                }

                                if(verif){
                                    lieux = carte.plusCourtChemin(utilisateur.getLieuActuel(), carte.getLieux().get(indice - 1), transport);
                                }

                                if(lieux.isEmpty()){
                                    System.out.println("!!! Vous ne pouvez pas accéder à ce lieu");
                                }
                                else{
                                    Float distanceParourue = carte.totalDistanceChemin(lieux, transport);
                                    Float tempsTrajet = utilisateur.getVehicules().getTempsTrajet(distanceParourue);
                                    int faim = (int) ((distanceParourue*100)/carte.totalDistanceChemin(carte.getLieux(),ModeTransport.VOITURE));
                                    float argent = 500*lieux.size();
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

                            for(Lieu l : events.getDangers().get(0).getLieuxCibles()){
                                l.setCondamne(true);
                            }
                            events.getDangers().remove(0);

                            if(utilisateur.getLieuActuel().estCondamne()){
                                System.out.println("!!! Vous êtes morts");
                                return ;
                            }

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
