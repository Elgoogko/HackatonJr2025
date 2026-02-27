package com.main.hackatonjr;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@SessionAttributes("game")
public class GameController {

    //ModelAttribute crée la session si elle n'existe pas contrairement a SessionAttribute

    @ModelAttribute("game")
    public Game initGame(){
        GameMap map = new GameMap();
        Events events = new Events(map);
        Shop shop = new Shop();
        User user = new User("", 20000.0f, new ArrayList<>(), 0, new Outfit(null, null, null), null, map.getLocations().get(0));
        return new Game(user,shop,map,events);
    }
    
    @GetMapping("/index")
    public String index(@ModelAttribute("game") Game game) {
        if(game != null && !game.getUser().getName().isEmpty()){
            return "redirect:/game";
        }
        return "index";
    }
    
    @PostMapping("/name")
    public String getName(@RequestParam String name, @ModelAttribute("game") Game game){
        game.getUser().setName(name);
        return "redirect:/game";
    }

    @GetMapping("/game")
    public String game(@ModelAttribute("game") Game game) {
        if(game == null || game.getUser().getName().isEmpty()){
            return "redirect:/index";
        }
        if(game.end()){
            return "redirect:/end";
        }
        return "game";
    }

    @GetMapping("/end")
    public String end(@ModelAttribute("game") Game game){
        if(game == null || game.getUser().getName().isEmpty()){
            return "redirect:/index";
        }
        if(!game.end()){
            return "redirect:/game";
        }
        return "end";
    }

    @GetMapping("/restart")
    public String restart(SessionStatus status){
        status.setComplete();
        return "redirect:/index";
    }

    @GetMapping("/leave")
    public String leave(@ModelAttribute("game") Game game){
        game.setEnd(true);
        return "redirect:/end";
    }
}
