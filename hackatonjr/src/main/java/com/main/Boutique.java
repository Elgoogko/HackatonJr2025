package com.main;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

enum ModeTris{ PRIX_CROISSANT, PRIX_DECROISSANT, NOM_CROISSANT, NOM_DECROISSANT}

public class Boutique {
    private ArrayList<Stockables> stock;
    private ArrayList<Stockables> panier;
    @Autowired
    private Utilisateur utilisateur;

    public Boutique() {
        this.stock = new ArrayList<>();
        this.stock.add(new Capsule(10000, Couleur.BLEU, null));
        this.stock.add(new Capsule(10000, Couleur.ROUGE, null));
        this.stock.add(new Capsule(10000, Couleur.VERT, null));
        return;
    }

    public Stockables acheter(Stockables sto) {
        float prix = sto.getPrix();
        if (sto.getPrix() > utilisateur.getArgent()){
            return null;
        }
        utilisateur.retirerArgent(prix);
        
        this.stock.remove(sto);
        if (sto instanceof Capsule) {
            Capsule c = (Capsule) sto;            
            return c;
        }
        return sto;
    }

    public ArrayList<Stockables> acheterPanier() {
        float prix = getPrixPanier();
        if (prix > utilisateur.getArgent()){
            return null;
        }
        ArrayList<Stockables> panier_achete = new ArrayList<>();
        for (Stockables elem : stock) {
            panier_achete.add(acheter(elem));
        }
        return panier_achete;
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
