package com.main.hackatonjr;

import java.util.ArrayList;
import java.util.List;

public class Chemin {
    private float distance;
    private Lieu lieuA;
    private Lieu lieuB;
    private ArrayList<TYPE> modeTransportOk;

    public Chemin(Lieu lieuA, Lieu lieuB) {
        // par defaut tous les modes de transport ok
        this(lieuA, lieuB, new ArrayList<TYPE>(List.of(TYPE.Nuage,TYPE.Pilier,TYPE.Voiture)));
    }

    public Chemin(Lieu lieuA, Lieu lieuB, ArrayList<TYPE> modeTransportAccepte) {
        this.lieuA = lieuA;
        this.lieuB = lieuB;
        this.distance = Coordonnees.distance(lieuA.getCoordonnees(), lieuB.getCoordonnees());
        this.modeTransportOk = modeTransportAccepte;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Lieu getLieuA() {
        return lieuA;
    }

    public Lieu getLieuB() {
        return lieuB;
    }

    public ArrayList<TYPE> getModeTransportOk() {
        return modeTransportOk;
    }

    public void setLieuA(Lieu lieuA) {
        this.lieuA = lieuA;
    }

    public Lieu getAutre(Lieu l) {
        if (l == lieuA) {
            return lieuB;
        }
        if (l == lieuB) {
            return lieuA;
        }
        return null;
    }
}
