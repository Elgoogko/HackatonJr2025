package com.main.hackatonjr;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api")
public class GameApi {

    @PostMapping("/pay")
    public User pay(@RequestBody String ids, @SessionAttribute("game") Game game){
        String[] arrayId = ids.split("_");
        for(int i = 0; i < arrayId.length; i++){
            game.getShop().addToCart(game.getShop().getStock().get(Integer.parseInt(arrayId[i])));
        }

        if(game.getShop().buyCartStockables(game.getUser())){
            User user = game.getUser();
            return new User(user.getName(),user.getMoney(),user.getInventory(),user.getHunger(),user.getOutfit(),user.getVehicle(),null);
        }
        else{
            return new User(null,0,null,0,null,null,null);
        }
    }

    @PostMapping("/equip")
    public String equip(@RequestBody String id, @SessionAttribute("game") Game game){
        Stockable stockable = game.getUser().getInventory().get(Integer.parseInt(id));

        System.out.print(id);

        if(!game.getUser().equip(stockable)){
            return "-1";
        }
        
        if(stockable instanceof Food){
            return "Food_" + game.getUser().getHunger();
        }
        else if(stockable instanceof Vehicle){
            return "Vehicle_" + stockable.getName();
        }
        else{
            Gear gear = (Gear)stockable;
            if(gear.getType() == GearType.HEAD){
                return "Head_" + stockable.getName();
            }
            else if(gear.getType() == GearType.TOP){
                return "Top_" + stockable.getName();
            }
            else{
                return "Bottom_" + stockable.getName();
            }
        }
    }

    @PostMapping("/move")
    public User move(@RequestBody String id, @SessionAttribute("game") Game game){
        Location loc = game.getMap().getLocations().get(Integer.parseInt(id));

        GameMap map = game.getMap();
        User user = game.getUser();

        if(user.getVehicle() == null){
            return new User(null,0,null,0,null,null,null);
        }

        ArrayList<Location> locations = map.shortestPath(user.getCurrentLocation(), loc, user.getVehicle().getType());

        if(locations.isEmpty()){
            return new User(null,0,null,0,null,null,null);
        }

        Float distanceTraveled = map.totalDistancePaths(locations, user.getVehicle().getType());
        int hunger = (int) ((distanceTraveled*100)/map.totalDistancePaths(map.getLocations(),VehicleType.CAR));
        float money = 500 * (locations.size() - 1);
        //The user gains 500 for each location visited
        if(hunger + user.getHunger() >= 100){
            return new User(null,0,null,0,null,null,null);
        }
        else{
            user.setCurrentLocation(loc);
            user.addHunger(hunger);
            user.addMoney(money);
        }

        return new User(user.getName(),user.getMoney(),user.getInventory(),user.getHunger(),user.getOutfit(),user.getVehicle(),new Location(-1,user.getCurrentLocation().getName(),"",null,null));
    }


    @GetMapping("/locations")
    public ArrayList<String> getLocations(@SessionAttribute("game") Game game){
        ArrayList<String> locationsId = new ArrayList<>();

        ArrayList<Location> allLocations = game.getMap().getLocations();

        Location current = game.getUser().getCurrentLocation();

        for(int i = 0; i < allLocations.size(); i++){
            if(allLocations.get(i).getId() != current.getId() && game.getUser().getVehicle() != null && !game.getMap().shortestPath(current, allLocations.get(i), game.getUser().getVehicle().getType()).isEmpty()){
                locationsId.add("" + allLocations.get(i).getId());
            }
        }

        if(locationsId.isEmpty()){
            locationsId.add("-1");
            return locationsId;
        }
        return locationsId;
    }

    @GetMapping("/event")
    public Event event(@SessionAttribute("game") Game game){
        game.setActualEvent(game.getEvents().getRandomEvent());
        Event event = game.getActualEvent();
        ArrayList<Location> locations = new ArrayList<>();
        for(int i = 0; i < event.getTargeLocations().size(); i++){
            Location loc = event.getTargeLocations().get(i);
            locations.add(new Location(loc.getId(),loc.getName(),loc.getDescription(),null,loc.getCoordinates()));
        }

        String str = " some zénis";

        if(event.getType() != EventTypeName.BONUS && event.getType() != EventTypeName.MALLUS){
            str = "";
        }
        return new Event(event.getType(),event.getTriggerTimeSec(),event.getDescription() + str,locations);
    }

    @GetMapping("/triggerEvent")
    public User triggerEvent(@SessionAttribute("game") Game game){
        Event event = game.getActualEvent();
        game.setActualEvent(null);

        if(event.getType() == EventTypeName.STORY){
            if(game.getEvents().getAllEvents().get(EventTypeName.STORY).size() <= 0){
                game.setEnd(true);
            }
        }
        else{
            game.setEnd(event.eventEffect(game.getUser()));
        }

        User user = game.getUser();
        return new User(user.getName(),user.getMoney(),user.getInventory(),user.getHunger(),user.getOutfit(),user.getVehicle(),null);
    }

    @GetMapping("/endGame")
    public boolean endGame(@SessionAttribute("game") Game game){
        return game.end();
    }

}
