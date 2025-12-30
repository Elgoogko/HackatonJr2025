package com.main.hackatonjr;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private float distance;
    private Location locationA;
    private Location locationB;
    private ArrayList<VehicleType> types;//The possible types of types that the user can make the journey with

    public Path(Location locationA, Location locationB){
        //By default all types of types can be used to make the journey 
        this(locationA, locationB, new ArrayList<VehicleType>(List.of(VehicleType.CLOUD,VehicleType.PILLAR,VehicleType.CAR)));
    }

    public Path(Location locationA, Location locationB, ArrayList<VehicleType> types){
        this.locationA = locationA;
        this.locationB = locationB;
        this.distance = Coordinates.distance(locationA.getCoordinates(), locationB.getCoordinates());
        this.types = types;
    }

    public float getDistance(){
        return this.distance;
    }
    public Location getLocationA(){
        return this.locationA;
    }
    public Location getLocationB(){
        return this.locationB;
    }
    public ArrayList<VehicleType> getVehicleTypes(){
        return this.types;
    }

    public void setDistance(float distance){
        this.distance = distance;
    }
    public void setLocationA(Location locationA){
        this.locationA = locationA;
    }
    public void setLocationB(Location locationB){
        this.locationB = locationB;
    }

    //Eeturns the location linked to a location passed as a parameter via the path
    public Location getOtherLocation(Location location){
        if (location == locationA) {
            return locationB;
        }
        if (location == locationB) {
            return locationA;
        }
        return null;
    }
}
