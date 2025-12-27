package com.main.hackatonjr;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public void afficheLigneVide(int taille){
        int i=0;
        System.out.print(" +");
        for(i=1;i<taille-1;i++){
            System.out.print("-");
        }
        System.out.print("+\n");
    }
    
    public void affiche(List<String> menu){
        if(menu.size()<=0){
            return ;
        }

        int i=0,j=0,taille=0;
        int max=menu.get(0).length();
        for(i=0;i<menu.size();i++){
            if(menu.get(i).length()>max){
                max = menu.get(i).length();
            }
        }
        taille=max+4;
        System.out.print("\n");

        if(menu.get(0) != ""){
            this.afficheLigneVide(taille);
            System.out.print(" | ");
            for(i=0;i<taille-4;i++){
                if(i<(taille-4-menu.get(0).length())/2 || i>=(taille-4+menu.get(0).length())/2){
                    System.out.print(" ");
                }
                else if(i==(taille-4)/2-menu.get(0).length()/2){
                    System.out.print("\u001B[96m" + menu.get(0) + "\u001B[0m");
                }
            }
            System.out.print(" |\n");
        }

        this.afficheLigneVide(taille);

        if(menu.size()<=1){
            return ;
        }
    
        for(i=1;i<menu.size();i++){
            System.out.print(" | ");
            System.out.print(menu.get(i));
            for(j=0;j<taille-4-menu.get(i).length();j++){
                System.out.print(" ");
            }
            System.out.print(" |\n");
        }

        this.afficheLigneVide(taille);
    }

    public void afficheCreation(Utilisateur utilisateur, Scanner sc){
        this.affiche(Arrays.asList("Creation"));

        do{
            System.out.print("--> Donnez votre pseudo (Maximum 10 caractères) : ");
            utilisateur.setNom(sc.nextLine());
        }while(utilisateur.getNom() == null || utilisateur.getNom().length() > 10 || utilisateur.getNom().length() <= 0);
    }

    public void afficheBienvenue(Utilisateur utilisateur, Scanner sc){
        this.affiche(Arrays.asList("Bienvenue " + utilisateur.getNom()));

        System.out.println("Le jeu simule l'arc frizer de db..., si on se deplace on gagne de l argent et notre faim augmente ...");
    }

    public void afficheMenu(){
        List<String> menu=Arrays.asList(
            "Menu",
            "0 - Quitter",
            "1 - Ouvrir l'inventaire",
            "2 - Ouvrir la boutique",
            "3 - Mon profil",
            "4 - Ouvrir la carte",
            "5 - Voir les evenements"
        );

        affiche(menu);
    }

    public void afficheInventaire(Utilisateur utilisateur){
        List<String> menu=Arrays.asList(
            "",
            "0 - Quitter",
            "1 - Retour",
            "2 - Equiper"
        );

        this.affiche(Arrays.asList("Inventaire (" + utilisateur.getInventaire().size() + " éléments)"));
        utilisateur.afficherInventaire();
        affiche(menu);
    }

    public void afficheBoutique(Boutique boutique){
        List<String> menu=Arrays.asList(
            "",
            "0 - Quitter",
            "1 - Retour",
            "2 - Trier par prix croissant",
            "3 - Trier par prix décroissant",
            "4 - Trier par nom croissant (A->Z)",
            "5 - Trier par nom décroissant (Z->A)",
            "6 - Acheter un élément"
        );

        this.affiche(Arrays.asList("Boutique (" + boutique.getStock().size() + " éléments)"));
        boutique.afficherBoutique();
        affiche(menu);
    }

    public void afficheProfil(Utilisateur utilisateur){
        List<String> menu=Arrays.asList(
            "",
            "0 - Quitter",
            "1 - Retour"
        );

        this.affiche(Arrays.asList("Profil"));
        utilisateur.afficherProfil();
        affiche(menu);
    }

    public void afficheCarte(Carte carte, Utilisateur utilisateur){
        List<String> menu=Arrays.asList(
            "",
            "0 - Quitter",
            "1 - Retour",
            "2 - Deplacement"
        );

        this.affiche(Arrays.asList("Carte"));
        carte.afficherLieux(utilisateur);
        carte.afficherChemins(utilisateur);
        carte.afficher10Lieux(utilisateur);
        affiche(menu);
    }

    public void afficheEvenement(Gestionnaire_Events events, Utilisateur utilisateur){
        this.affiche(Arrays.asList("Evenements"));
    }
}
