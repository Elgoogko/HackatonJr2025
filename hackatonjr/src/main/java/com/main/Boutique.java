package com.main;

import com.main.Stockables;
import java.util.ArrayList;

public class Boutique {
    private ArrayList<Stockables> stock;
    private ArrayList<Stockables> panier;

    public Boutique() {
        this.stock = new ArrayList<>();
        this.stock.add(new Capsule(10000, Couleur.BLEU, null));
        this.stock.add(new Capsule(10000, Couleur.ROUGE, null));
        this.stock.add(new Capsule(10000, Couleur.VERT, null));
        return;
    }

    public Stockables acheter(Stockables stock) {
        if (stock instanceof Capsule) {
            Capsule c = (Capsule) stock;
            return c;
        }
        this.stock.remove(stock);
        return stock;
    }

}

