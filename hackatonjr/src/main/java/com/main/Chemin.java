package com.main;

public class Chemin {
    private float distance;
    private Lieu lieuA;
    private Lieu lieuB;

    public Chemin(Lieu lieuA, Lieu lieuB, float distance) {
        this.lieuA = lieuA;
        this.lieuB = lieuB;
        this.distance = distance;
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
