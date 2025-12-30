package com.main.hackatonjr;

import java.util.ArrayList;

public class Catalog {
    public ArrayList<Gear> gears = new ArrayList<>();
    public ArrayList<Food> foods = new ArrayList<>();
    public ArrayList<Vehicle> vehicles = new ArrayList<>();

    public Catalog(){
        //Strating with id = 3 because there are already three capsules in the shop
        gears.add(new Gear(3, "Chaozu's hat", 200.0f, GearType.HEAD));
        gears.add(new Gear(4, "Great Saiyaman's helmet", 700.0f, GearType.HEAD));
        gears.add(new Gear(5, "Piccolo's turban", 400.0f, GearType.HEAD));

        gears.add(new Gear(6, "Goku's Turtle School Gi", 500.0f, GearType.TOP));
        gears.add(new Gear(7, "Piccolo's Cape", 800.0f, GearType.TOP));
        gears.add(new Gear(8, "Vegeta's Saiyan Armor", 600.0f, GearType.TOP));

        gears.add(new Gear(9, "Goku's Gi Pants", 300.0f, GearType.BOTTOM));
        gears.add(new Gear(10, "Piccolo's Baggy Pants", 400.0f, GearType.BOTTOM));
        gears.add(new Gear(11, "Vegeta's Battle Suit Pants", 600.0f, GearType.BOTTOM));

        vehicles.add(new Vehicle(16, "Car", 2000.0f, 100.0f, VehicleType.CAR));
        vehicles.add(new Vehicle(17, "Kinto'un", 4000.0f, 400.0f, VehicleType.CLOUD));
        vehicles.add(new Vehicle(18, "Flying pillar", 8000.0f, 800.0f, VehicleType.PILLAR));

        foods.add(new Food(12, "Senzu", 1000.0f, 100));
        foods.add(new Food(13, "Ramen", 500.0f, 50));
        foods.add(new Food(14, "Egg", 300.0f, 30));
        foods.add(new Food(15, "Fruit", 200.0f, 20));
    }
}