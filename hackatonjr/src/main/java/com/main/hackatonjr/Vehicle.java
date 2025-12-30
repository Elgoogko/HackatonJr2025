package com.main.hackatonjr;

enum VehicleType {
    CAR,
    CLOUD,
    PILLAR
};

public class Vehicle extends Stockable {
    private float speed;
    private VehicleType type;

    public Vehicle(int id, String name, float price, float speed, VehicleType type){
        super(id,name,price);
        this.speed = speed;
        this.type = type;
    }

    public float getSpeed() {
        return this.speed;
    }
    public VehicleType getType() {
        return this.type;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public void setType(VehicleType type) {
        this.type = type;
    }

    public float getTravelTime(float distance) {
        if (this.speed == 0) {
            return Float.MAX_VALUE;
        }
        return distance / this.speed;
    }

    @Override 
    public boolean equals(Object obj){
        if(obj == null && this != null){
            return false;
        }
        if(obj instanceof Vehicle){
            Vehicle vehicle = (Vehicle) obj;
            if(vehicle.getId() == this.getId() && vehicle.getName().equals(this.getName()) && vehicle.getPrice() == this.getPrice() && vehicle.getSpeed() == this.speed && vehicle.getType() == this.type){
                return true;
            }
        }
        return false;
    }
}
