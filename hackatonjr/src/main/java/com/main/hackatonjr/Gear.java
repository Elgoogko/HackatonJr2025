package com.main.hackatonjr;

enum GearType {
    HEAD,
    TOP, 
    BOTTOM
};

public class Gear extends Stockable {
    private GearType type;

    public Gear(int id, String name, float price, GearType type){
        super(id,name,price);
        this.type = type;
    }

    public GearType getType(){
        return this.type;
    }

    public void setType(GearType type){
        this.type = type;
    }

    @Override 
    public boolean equals(Object obj){
        if(obj == null && this != null){
            return false;
        }
        if(obj instanceof Gear){
            Gear gear = (Gear) obj;
            if(gear.getId() == this.getId() && gear.getName().equals(this.getName()) && gear.getPrice() == this.getPrice() && gear.getType() == this.type){
                return true;
            }
        }
        return false;
    }
}
