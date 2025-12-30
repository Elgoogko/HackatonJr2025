package com.main.hackatonjr;

import java.util.ArrayList;

public class Location {
    private int id;
    private String name;
    private String description;
    private ArrayList<Path> paths;
    private Coordinates coordinates;
    private boolean condemned = false;

    public Location(int id, String name, String description, ArrayList<Path> paths, Coordinates coordinates){
        this.id = id;
        this.name = name;
        this.description = description;
        this.paths = paths;
        this.coordinates = coordinates;
    }

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public ArrayList<Path> getPaths(){
        return this.paths;
    }
    public Coordinates getCoordinates(){
        return this.coordinates;
    }
    public boolean isCondemned(){
        return this.condemned;
    }

    public void setPaths(ArrayList<Path> paths){
        this.paths = paths;
    }
    public void setCondemned(boolean condemned){
        this.condemned = condemned;
    }

    public void addPath(Path path){
        this.paths.add(path);
    }
    public void addAllPaths(ArrayList<Path> paths){
        this.paths.addAll(paths);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Location)){
            return false;
        }
        Location lieu = (Location) obj;
        return id == lieu.id;
    }
}