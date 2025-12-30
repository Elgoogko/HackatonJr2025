package com.main.hackatonjr;

import java.util.concurrent.ThreadLocalRandom;

enum CapsuleColor {
    BLUE,//Gears
    RED,//Vehicles
    GREEN//Foods
}

public class Capsule extends Stockable {
    
    private CapsuleColor color;

    public Capsule(int id, String name , float price, CapsuleColor color){
        super(id,name,price);
        this.color = color;
    }

    public CapsuleColor getColor(){
        return color;
    }

    public void setColor(CapsuleColor color){
        this.color = color;
    }

    //Get a random object from the shop depending on the capsule's color
    public Stockable getRandomObject(int id){
        Catalog catalog = new Catalog();

        switch (this.color) {
            case BLUE:
                Gear gear = catalog.gears.get(ThreadLocalRandom.current().nextInt(catalog.gears.size()));
                return new Gear(id,gear.getName(),gear.getPrice(),gear.getType());

            case RED:
                Vehicle vehicle = catalog.vehicles.get(ThreadLocalRandom.current().nextInt(catalog.vehicles.size()));
                return new Vehicle(id,vehicle.getName(),vehicle.getPrice(),vehicle.getSpeed(),vehicle.getType());

            case GREEN:
                Food food = catalog.foods.get(ThreadLocalRandom.current().nextInt(catalog.foods.size()));
                return new Food(id,food.getName(),food.getPrice(),food.getHunger());
        }
        return null;
    }
}
