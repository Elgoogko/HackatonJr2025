package com.main.hackatonjr;

enum EventTypeName {
    STORY,
    DANGER,
    BONUS,
    MALLUS,
    ATTACK
}

public class EventType {
    private EventTypeName type;
    private float probability;

    public EventType(EventTypeName type, float probability){
        this.type = type;
        this.probability = probability;
    }

    public EventTypeName getType(){
        return this.type;
    }
    public float getProbability(){
        return this.probability;
    }

    public void setType(EventTypeName type){
        this.type = type;
    }
    public void setProbability(float probability){
        this.probability = probability;
    }
}
