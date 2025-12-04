package com.main;

import com.main.Stockables;

public class Nourriture extends Stockables {
    private float RenduFaim;

    public Nourriture(float RenduFaim, String nom, float prix){
        super(nom, prix);
        this.RenduFaim = RenduFaim;
    }
    
    public float getRenduFaim(){
        return this.RenduFaim;
    }
}
