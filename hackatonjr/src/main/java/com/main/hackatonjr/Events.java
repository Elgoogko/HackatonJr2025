package com.main.hackatonjr;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Events {
    private ArrayList<EventType> types;
    private EnumMap<EventTypeName, ArrayList<Event>> allevents;//2D array that contains events of each type

    public Events(GameMap map){
        this.types = new ArrayList<>(List.of(new EventType(EventTypeName.STORY,0.3f),new EventType(EventTypeName.DANGER,0.3f),new EventType(EventTypeName.BONUS,0.15f),new EventType(EventTypeName.MALLUS,0.15f),new EventType(EventTypeName.ATTACK,0.1f)));

        this.allevents = new EnumMap<>(EventTypeName.class);

        this.allevents.put(EventTypeName.STORY,new ArrayList<>());
        this.allevents.get(EventTypeName.STORY).add(new Event(EventTypeName.STORY, 0, "Gohan, Krillin and Bulma arrived on Namek", new ArrayList<>()));
        this.allevents.get(EventTypeName.STORY).add(new Event(EventTypeName.STORY, 0, "Vegeta teams up with Gohan and Krillin to find the dragon balls", new ArrayList<>()));
        this.allevents.get(EventTypeName.STORY).add(new Event(EventTypeName.STORY, 0, "Goku arrives to battle Frieza", new ArrayList<>()));

        this.allevents.put(EventTypeName.DANGER,new ArrayList<>());
        this.allevents.get(EventTypeName.DANGER).add(new Event(EventTypeName.DANGER, 60, "Frieza launches an attack", map.randomLocations()));
        this.allevents.get(EventTypeName.DANGER).add(new Event(EventTypeName.DANGER, 60, "An explosion occured", map.randomLocations()));
        this.allevents.get(EventTypeName.DANGER).add(new Event(EventTypeName.DANGER, 60, "Captain Ginyu and his troops are fighting", map.randomLocations()));

        this.allevents.put(EventTypeName.BONUS,new ArrayList<>());
        this.allevents.get(EventTypeName.BONUS).add(new Event(EventTypeName.BONUS, 0, "You won ", new ArrayList<>()));

        this.allevents.put(EventTypeName.MALLUS,new ArrayList<>());
        this.allevents.get(EventTypeName.MALLUS).add(new Event(EventTypeName.MALLUS, 0, "You lost ", new ArrayList<>()));

        this.allevents.put(EventTypeName.ATTACK,new ArrayList<>());
        this.allevents.get(EventTypeName.ATTACK).add(new Event(EventTypeName.ATTACK, 60, "Frieza attackes you", new ArrayList<>()));
        this.allevents.get(EventTypeName.ATTACK).add(new Event(EventTypeName.ATTACK, 60, "Vegeta attackes you", new ArrayList<>()));
        this.allevents.get(EventTypeName.ATTACK).add(new Event(EventTypeName.ATTACK, 60, "Ginyu attackes you", new ArrayList<>()));
    }

    public EnumMap<EventTypeName, ArrayList<Event>> getAllEvents(){
        return this.allevents;
    }

    public Event getRandomEvent(){
        double random = Math.random();//Returns a number 0.0 <= x < 1.0
        int counter = 0;
        float sum = this.types.get(0).getProbability();

        while(sum < random && counter < this.types.size()-1){
            counter++;
            sum += this.types.get(counter).getProbability();
        }
        //Picks a random type of events

        switch(this.types.get(counter).getType()){
            case STORY:
                if(allevents.get(EventTypeName.STORY).size()>0){
                    Event event = allevents.get(EventTypeName.STORY).get(0);
                    allevents.get(EventTypeName.STORY).remove(0);
                    return event;
                }
                break;

            case DANGER:
                if(this.allevents.get(EventTypeName.DANGER).size()>0){
                    return this.allevents.get(EventTypeName.DANGER).get(ThreadLocalRandom.current().nextInt(this.allevents.get(EventTypeName.DANGER).size()));
                }
                break;

            case BONUS:
                if(this.allevents.get(EventTypeName.BONUS).size()>0){
                    return this.allevents.get(EventTypeName.BONUS).get(ThreadLocalRandom.current().nextInt(this.allevents.get(EventTypeName.BONUS).size()));
                }
                break;

            case MALLUS:
                if(this.allevents.get(EventTypeName.MALLUS).size()>0){
                    return this.allevents.get(EventTypeName.MALLUS).get(ThreadLocalRandom.current().nextInt(this.allevents.get(EventTypeName.MALLUS).size()));
                }
                break;

            case ATTACK:
                if(this.allevents.get(EventTypeName.ATTACK).size()>0){
                    return this.allevents.get(EventTypeName.ATTACK).get(ThreadLocalRandom.current().nextInt(this.allevents.get(EventTypeName.ATTACK).size()));
                }
                break;
        }

        return null;
    }
}
