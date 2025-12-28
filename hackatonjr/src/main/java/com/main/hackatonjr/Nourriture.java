package com.main.hackatonjr;

public class Nourriture extends Stockables {
    private int RenduFaim;

    public Nourriture(int id, int RenduFaim, String nom, float prix){
        super(id, nom, prix);
        this.RenduFaim = RenduFaim;
    }
    
    public int getRenduFaim(){
        return this.RenduFaim;
    }

    @Override 
    public boolean equals(Object obj){
        if(obj instanceof Nourriture){
            Nourriture nourriture = (Nourriture) obj;
            if(nourriture.getId() == this.getId() && nourriture.getNom().equals(this.getNom()) && nourriture.getPrix() == this.getPrix() && nourriture.getRenduFaim() == this.RenduFaim){
                return true;
            }
        }
        return false;
    }
}
