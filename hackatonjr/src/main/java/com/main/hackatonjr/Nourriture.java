package com.main.hackatonjr;

import com.main.hackatonjr.Stockables;

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
