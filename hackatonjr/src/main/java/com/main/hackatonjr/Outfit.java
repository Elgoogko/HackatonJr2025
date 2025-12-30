package com.main.hackatonjr;

public class Outfit {
    private Gear head;
    private Gear top;
    private Gear bottom;

    public Outfit(Gear head, Gear top, Gear bottom){
        this.head = head;
        this.top = top;
        this.bottom = bottom;
    }

    public Gear getHead(){
        return this.head;
    }
    public Gear getTop(){
        return this.top;
    }
    public Gear getBottom(){
        return this.bottom;
    }

    public void setHead(Gear head){
        this.head = head;
    }
    public void setTop(Gear top){
        this.top = top;
    }
    public void setBottom(Gear bottom){
        this.bottom = bottom;
    }

    @Override 
    public boolean equals(Object obj){
        if(obj instanceof Outfit){
            Outfit outfit = (Outfit) obj;
            if(this.head.equals(outfit.getHead()) && this.top.equals(outfit.getTop()) && this.bottom.equals(outfit.getBottom())){
                return true;
            }
        }
        return false;
    }
}
