package com.main.hackatonjr;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Event {
    private EventTypeName type;
    private String description;
    private int triggerTimeSec;
    private ArrayList<Location> targetLocations;

    public Event(EventTypeName type, int triggerTimeSec, String description, ArrayList<Location> targetLocations){
        this.type = type;
        this.triggerTimeSec = triggerTimeSec;
        this.description = description;
        this.targetLocations = targetLocations;
    }

    public EventTypeName getType(){
        return type;
    }
    public String getDescription(){
        return description;
    }
    public int getTriggerTimeSec(){
        return triggerTimeSec;
    }
    public ArrayList<Location> getTargeLocations() {
        return targetLocations;
    }

    public boolean eventEffect(User user){
        int random = 0;

        switch(this.getType()){
            case DANGER:
                for(Location lieu : this.targetLocations){
                    lieu.setCondemned(true);
                }
                if(user.getCurrentLocation().isCondemned()){
                    return true;
                }
                break;

            case BONUS:
                random = ThreadLocalRandom.current().nextInt(1, 30) * 100;
                user.addMoney((float)random);
                break;

            case MALLUS:
                random = ThreadLocalRandom.current().nextInt(1, 10) * 100;
                user.withdrawMoney(random);
                break;

            case ATTACK:
                boolean idDead = true;

                if(user.getOutfit().getHead() != null && user.getOutfit().getTop() != null && user.getOutfit().getBottom() != null){
                    idDead = false;
                }

                user.getInventory().remove(user.getOutfit().getHead());
                user.getInventory().remove(user.getOutfit().getTop());
                user.getInventory().remove(user.getOutfit().getBottom());

                user.setOutfit(new Outfit(null, null, null));

                return idDead;

            default:
                break;
        }

        return false;
    }
}
    
