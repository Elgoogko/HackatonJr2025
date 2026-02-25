package com.main.hackatonjr;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("game")
public class GameController {

    @ModelAttribute("game")
    public Game initGame(){
        GameMap map = new GameMap();
        Events events = new Events(map);
        Shop shop = new Shop();
        User user = new User("", 20000.0f, new ArrayList<>(), 0, new Outfit(null, null, null), null, map.getLocations().get(0));
        return new Game(user,shop,map,events);
    }
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    
    
    @PostMapping("/name")
    public String getName(@RequestParam String name, @ModelAttribute("game") Game game){
        game.getUser().setName(name);
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/name")
    public String name() {
        return "name";
    }

    @PostMapping("/pay")
    @ResponseBody
    public User pay(@RequestBody String ids, @ModelAttribute("game") Game game){
        String[] arrayId = ids.split("_");
        for(int i = 0; i < arrayId.length; i++){
            game.getShop().addToCart(game.getShop().getStock().get(Integer.parseInt(arrayId[i])));
        }

        if(game.getShop().buyCartStockables(game.getUser())){
            User user = game.getUser();
            return new User(user.getName(),user.getMoney(),user.getInventory(),user.getHunger(),user.getOutfit(),user.getVehicle(),null);
        }
        else{
            return null;
        }
    }

    @PostMapping("/equip")
    @ResponseBody
    public String equip(@RequestBody String id, @ModelAttribute("game") Game game){
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
}
