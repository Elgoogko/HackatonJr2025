package com.main.hackatonjr;

public class Game {
    private User user;
    private Shop shop;
    private GameMap map;
    private Events events;
    private boolean end = false;
    private Event actualEvent = null;

    public Game(User user, Shop shop, GameMap map, Events events){
        this.user = user;
        this.shop = shop;
        this.map = map;
        this.events = events;
    }

    public User getUser(){
        return this.user;
    }
    public Shop getShop(){
        return this.shop;
    }
    public GameMap getMap(){
        return this.map;
    }
    public Events getEvents(){
        return this.events;
    }

    public String type(Stockable stockable){
        if(stockable instanceof Food){
            return "food";
        }
        else if(stockable instanceof Vehicle){
            return "vehicle";
        }
        else{
            Gear gear = (Gear) stockable;
            if(gear.getType() == GearType.TOP){
                return "top";
            }
            else if(gear.getType() == GearType.HEAD){
                return "head";
            }
            else{
                return "bottom";
            }
        }
    }

    public boolean end(){
        return this.end;
    }

    public void setEnd(boolean bool){
        this.end = bool;
    }

    public Event getActualEvent(){
        return this.actualEvent;
    }

    public void setActualEvent(Event event){
        this.actualEvent = event;
    }
}
