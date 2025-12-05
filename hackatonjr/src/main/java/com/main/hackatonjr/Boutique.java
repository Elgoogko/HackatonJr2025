package com.main.hackatonjr;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadLocalRandom;

enum ModeTris{ PRIX_CROISSANT, PRIX_DECROISSANT, NOM_CROISSANT, NOM_DECROISSANT}

public class Boutique {
    private ArrayList<Stockables> stock;
    private ArrayList<Stockables> panier;
    @Autowired
    private Utilisateur utilisateur;

    public Boutique(Utilisateur utilisateur) {
        this.stock = new ArrayList<>();
        this.stock.add(new Capsule(10000.0f, Couleur.BLEU, "Capsule bleu"));
        this.stock.add(new Capsule(10000.0f, Couleur.ROUGE, "Capsule rouge"));
        this.stock.add(new Capsule(10000.0f, Couleur.VERT, "Capsule vert"));
        Catalogue catalogue = new Catalogue();
        this.stock.addAll(catalogue.Vetements);
        this.stock.addAll(catalogue.Nourritures);
        this.stock.addAll(catalogue.Vehicules);
        this.panier = new ArrayList<>();
        this.utilisateur = utilisateur;
        return;
    }

    public ArrayList<Stockables> getStock() {
        return this.stock;
    }

    public boolean acheter(Stockables sto) {
        float prix = sto.getPrix();
        if (sto.getPrix() > this.utilisateur.getArgent()){
            return false;
        }
        if(this.utilisateur.retirerArgent(prix) == false){
            return false;
        }
        
        if (sto instanceof Capsule) {
            Catalogue catalogue = new Catalogue();

            if(((Capsule)sto).getCouleur() == Couleur.BLEU){
                this.utilisateur.ajoutElement(catalogue.Vetements.get(ThreadLocalRandom.current().nextInt(catalogue.Vetements.size())));
            }
            else if(((Capsule)sto).getCouleur() == Couleur.ROUGE){
                this.utilisateur.ajoutElement(catalogue.Vehicules.get(ThreadLocalRandom.current().nextInt(catalogue.Vehicules.size())));
            }
            else{
                this.utilisateur.ajoutElement(catalogue.Nourritures.get(ThreadLocalRandom.current().nextInt(catalogue.Nourritures.size())));
            }
        }
        else{
            this.utilisateur.ajoutElement(sto);
            this.stock.remove(sto);
        }
        return true;
    }

    public boolean acheterPanier() {
        float prix = getPrixPanier();
        if (prix > this.utilisateur.getArgent()){
            return false;
        }
        ArrayList<Stockables> copie = new ArrayList<>(panier);
        for (Stockables elem : copie) {
            if (acheter(elem) == false){
                return false;
            }
        }
        return true;
    }

    public void ajouterPanier(Stockables sto) {
        this.panier.add(sto);
        this.stock.remove(sto);
        return;
    }

    public float getPrixPanier() {
        float prix = 0;
        for (Stockables elem : stock) {
            prix += elem.getPrix();
        }
        return prix;
    }

    public void trierStock(ModeTris mode) {
        int n = stock.size();
        //buble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean swap = false;
                Stockables current = stock.get(j);
                Stockables next = stock.get(j + 1);
                
                switch (mode) {
                    case PRIX_CROISSANT:
                        swap = current.getPrix() > next.getPrix();
                        break;
                    case PRIX_DECROISSANT:
                        swap = current.getPrix() < next.getPrix();
                        break;
                    case NOM_CROISSANT:
                        swap = current.getNom().compareTo(next.getNom()) > 0;
                        break;
                    case NOM_DECROISSANT:
                        swap = current.getNom().compareTo(next.getNom()) < 0;
                        break;
                }
                
                if (swap) {
                    stock.set(j, next);
                    stock.set(j + 1, current);
                }
            }
        }
    }

    public void afficherBoutique(){
        int compteur=1;
        System.out.println("Boutique (" + this.stock.size() + " éléments) : ");
        for(Stockables stockable : this.stock){
            System.out.println(compteur + ". " + stockable.getNom()  + " " + stockable.getPrix() + " zénis");
            compteur++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stock de la boutique:\n");
        for (Stockables elem : stock) {
            sb.append("- ").append(elem.getNom()).append(" : ").append(elem.getPrix()).append(" euros\n");
        }
        sb.append("Panier:\n");
        for (Stockables elem : panier) {
            sb.append("- ").append(elem.getNom()).append(" : ").append(elem.getPrix()).append(" euros\n");
        }
        return sb.toString();
    }
}
