package com.main.hackatonjr;

import java.util.ArrayList;
import java.util.List;

enum Type_Evenement {HISTOIRE,DANGER,BONUS,MALLUS}

public class Evenement {
    private Type_Evenement type;
    private String description;
    private int triggerTimeSec;
    private boolean declenche;
    private ArrayList<Lieu> lieuxCibles;

    public Evenement(Type_Evenement type, int triggerTimeSec, String description, ArrayList<Lieu> lieuxCibles) {
        this.type = type;
        this.triggerTimeSec = triggerTimeSec;
        this.description = description;
        this.lieuxCibles = lieuxCibles;
        this.declenche = false;
    }

    public Type_Evenement getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public int getTriggerTimeSec() {
        return triggerTimeSec;
    }
    public boolean isDeclenche() {
        return declenche;
    }
    public void setDeclenche(boolean declenche) {
        this.declenche = declenche;
    }
    public List<Lieu> getLieuxCibles() {
        return lieuxCibles;
    }
    
}
    
