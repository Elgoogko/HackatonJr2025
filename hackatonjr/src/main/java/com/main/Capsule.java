package com.main;

enum Couleur{ BLEU, ROUGE, VERT}

enum Vehicules {
    
}

public class Capsule extends Stockables{
    private float prix;
    private Couleur couleur;

    public Capsule(float prix, Couleur couleur, String nom) {
        super(nom, prix);
        this.couleur = couleur;
    }

    public float getPrix() {
        return prix;
    }

    public Couleur getCouleur() {
        return couleur;
    }
    //TODO
    public Stockables getObjetAleatoire(){
        switch (this.couleur) {
            case BLEU:
                
                break;
            case ROUGE:
                break;
            case VERT:
                break;
            default:
                //pas cool 
                break;
        }
        return null;
    }


}
