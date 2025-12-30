package com.main.hackatonjr;

public class Stockable {
    private int id;
    private String name;
    private float price;
    
    public Stockable(int id, String name, float price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    @Override 
    public boolean equals(Object obj){
        if(this instanceof Vehicle && obj instanceof Vehicle){
            return ((Vehicle)this).equals((Vehicle)obj);
        }
        else if(this instanceof Gear && obj instanceof Gear){
            return ((Gear)this).equals((Gear)obj);
        }
        else if(this instanceof Food && obj instanceof Food){
            return ((Food)this).equals((Food)obj);
        }
        return false;
    }
}


