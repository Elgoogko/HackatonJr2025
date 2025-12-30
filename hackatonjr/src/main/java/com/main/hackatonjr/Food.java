package com.main.hackatonjr;

public class Food extends Stockable {
    private int hunger;

    public Food(int id, String name, float price, int hunger){
        super(id,name,price);
        this.hunger = hunger;
    }
    
    public int getHunger(){
        return this.hunger;
    }

    public void setHunger(int hunger){
        this.hunger = hunger;
    }

    @Override 
    public boolean equals(Object obj){
        if(obj == null && this != null){
            return false;
        }
        if(obj instanceof Food){
            Food food = (Food) obj;
            if(food.getId() == this.getId() && food.getName().equals(this.getName()) && food.getPrice() == this.getPrice() && food.getHunger() == this.hunger){
                return true;
            }
        }
        return false;
    }
}
