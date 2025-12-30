package com.main.hackatonjr;

import java.util.ArrayList;

public class User {
    private String name;
    private float money;
    private ArrayList<Stockable> inventory;
    private int hunger;
    private Outfit outfit;
    private Vehicle vehicle;
    private Location current;

    public User(String name, float money, ArrayList<Stockable> inventory, int hunger, Outfit outfit, Vehicle vehicle, Location current){
        this.name = name;
        this.money = money;
        this.inventory = inventory;
        this.hunger = hunger;
        this.outfit = outfit;
        this.vehicle = vehicle;
        this.current = current;
    }

    public Location getCurrentLocation(){
        return this.current;
    }
    public String getName(){
        return this.name;
    }
    public float getMoney(){
        return this.money;
    }
    public ArrayList<Stockable> getInventory(){
        return this.inventory;
    }
    public int getHunger(){
        return this.hunger;
    }
    public Outfit getOutfit(){
        return this.outfit;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
    }

    public void setCurrentLocation(Location current){
        this.current = current;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setMoney(float money){
        this.money = money;
    }
    public void setInventory(ArrayList<Stockable> inventory){
        this.inventory = inventory;
    }
    public void setHunger(int hunger){
        this.hunger = hunger;
    }
    public void setOutfit(Outfit outfit){
        this.outfit = outfit;
    }
    public void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public void addMoney(float money){
        this.money += money;
    }

    public void withdrawMoney(float price){
        this.money -= price;
        if(this.money < 0){
            this.money = 0;
        }
    }

    public void addToInventory(Stockable stockable){
        this.inventory.add(stockable);
    }

    public void addAllStock(ArrayList<Stockable> stockables){
        this.inventory.addAll(stockables);
    }

    public void reduceHunger(int hunger){
        this.hunger -= hunger;
        if(this.hunger < 0){
            this.hunger = 0;
        }
    }

    public void addHunger(int hunger){
        this.hunger += hunger;
        if(this.hunger > 100){
            this.hunger = 100;
        }
    }

    public void consumeFood(Food food){
        this.reduceHunger(food.getHunger());
        this.inventory.remove(food);
    }

    //Returns false if the gear is already equipped or true if it isn't
    public boolean equipGear(Gear gear){
        switch(gear.getType()){
            case HEAD:
                if(this.outfit.getHead() == null || !this.outfit.getHead().equals(gear)){
                    this.outfit.setHead(gear);
                    return true;
                }
                break;
            case TOP:
                if(this.outfit.getTop() == null || !this.outfit.getTop().equals(gear)){
                    this.outfit.setTop(gear);
                    return true;
                }
                break;
            case BOTTOM:
                if(this.outfit.getBottom() == null || !this.outfit.getBottom().equals(gear)){
                    this.outfit.setBottom(gear);
                    return true;
                }
                break;
        }
        return false;
    }

    //Returns false if the vehicle is already equipped or true if it isn't
    public boolean equipVehicle(Vehicle vehicle){
        if(this.vehicle != null && this.vehicle.equals(vehicle)){
            return false;
        }
        this.vehicle = vehicle;
        return true;
    }

    //Equips a stockable depending of its type
    public boolean equip(Stockable stockable){
        boolean isEquipped = true;
        if(stockable instanceof Food){
            consumeFood((Food)stockable);
        }
        else if(stockable instanceof Gear){
            isEquipped = equipGear((Gear)stockable);
        }
        else{
            isEquipped = equipVehicle((Vehicle)stockable);
        }
        return isEquipped;
    }

    //Returns true if a stockable is already equipped or false if it's not
    //Food can't be equipped it can just be consumed
    public boolean isEquipped(Stockable stockable){
        if(stockable instanceof Gear){
            Gear gear = (Gear)stockable;
            if((gear.getType() == GearType.HEAD && this.getOutfit().getHead() != null && this.getOutfit().getHead().equals(gear))
            || (gear.getType() == GearType.TOP && this.getOutfit().getTop() != null && this.getOutfit().getTop().equals(gear))
            || (gear.getType() == GearType.BOTTOM && this.getOutfit().getBottom() != null && this.getOutfit().getBottom().equals(gear))){
                return true;
            }
        }
        else if(stockable instanceof Vehicle){
            Vehicle vehicle = (Vehicle)stockable;
            if(this.getVehicle() != null && this.getVehicle().equals(vehicle)){
                return true;
            }
        }
        return false;
    }
}