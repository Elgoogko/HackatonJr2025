package com.main.hackatonjr;

enum ModeTransport {
    VOITURE, NUAGE, TOUS, PILIER
}

public class Chemin {
    private float distance;
    private Lieu lieuA;
    private Lieu lieuB;
    private ModeTransport modeTransportOk;

    public Chemin(Lieu lieuA, Lieu lieuB) {
        // par defaut tous les modes de transport ok
        this(lieuA, lieuB, ModeTransport.TOUS);
    }

    public Chemin(Lieu lieuA, Lieu lieuB, ModeTransport modeTransportAccepte) {
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

    public ModeTransport getModeTransportOk() {
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
