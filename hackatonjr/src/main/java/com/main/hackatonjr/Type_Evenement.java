package com.main.hackatonjr;

public class Type_Evenement {
    private T_Evenement type;
    private float prob;

    public Type_Evenement(T_Evenement type, float prob){
        this.type = type;
        this.prob = prob;
    }

    public T_Evenement getTypeEvenement(){
        return this.type;
    }
    public void setTypeEvenement(T_Evenement type){
        this.type = type;
    }

    public float getProbabilite(){
        return this.prob;
    }
    public void setProbabilite(float prob){
        this.prob = prob;
    }
}
