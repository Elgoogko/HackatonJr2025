package com.main;

import java.util.List;


public class Evenement {
    private Type_Evenement type;
    private String description;
    private int triggerTimeSec;
    private boolean declenche;

    private String ennemi;
    private List<Lieu> lieuxCibles;

    public static Evenement histoire(int triggerTimeSec, String description) {
        Evenement e = new Evenement();
        e.type = Type_Evenement.HISTOIRE;
        e.triggerTimeSec = triggerTimeSec;
        e.description = description;
        e.declenche = false;
        return e;
    }

    public static Evenement danger(int triggerTimeSec, String ennemi, String description, List<Lieu> lieuxCibles) {
        Evenement e = new Evenement();
        e.type = Type_Evenement.DANGER;
        e.triggerTimeSec = triggerTimeSec;
        e.ennemi = ennemi;
        e.description = description;
        e.lieuxCibles = lieuxCibles;
        e.declenche = false;
        return e;
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
    public String getEnnemi() {
        return ennemi;
    }
    public List<Lieu> getLieuxCibles() {
        return lieuxCibles;
    }
}
    
