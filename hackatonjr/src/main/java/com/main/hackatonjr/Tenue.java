package com.main.hackatonjr;

public class Tenue {
    private Vetement haut;
    private Vetement bas;
    private Vetement tete;

    public Tenue(Vetement haut, Vetement bas, Vetement tete){
        this.haut = haut;
        this.bas = bas;
        this.tete = tete;
    }

    public Vetement getHaut(){
        return this.haut;
    }
    public Vetement getBas(){
        return this.bas;
    }
    public Vetement getTete(){
        return this.tete;
    }

    public void setHaut(Vetement haut){
        this.haut = haut;
    }
    public void setBas(Vetement bas){
        this.bas = bas;
    }
    public void setTete(Vetement tete){
        this.tete = tete;
    }
}
