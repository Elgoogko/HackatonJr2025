package com.main;

import com.main.Stockables;

public class Nourriture extends Stockables {
    private int RenduFaim;

    public Nourriture(int RenduFaim, String nom, float prix){
        super(nom, prix);
        this.RenduFaim = RenduFaim;
    }
    
    public int getRenduFaim(){
        return this.RenduFaim;
    }
}
