package com.main;

public class Chemin {
    private float distance;
    private Lieu lieuA;
    private Lieu lieuB;

    public Chemin(float distance, Lieu lieuA, Lieu lieuB) {
        this.distance = distance;
        this.lieuA = lieuA;
        this.lieuB = lieuB;
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
}
