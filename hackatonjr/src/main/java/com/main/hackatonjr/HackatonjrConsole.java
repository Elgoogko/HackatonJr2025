package com.main.hackatonjr;

import java.util.ArrayList;
import java.util.Scanner;

public class HackatonjrConsole {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Display display = new Display();
        GameMap map = new GameMap();
        Events events = new Events(map);
        Shop shop = new Shop();
        User user = new User("", 20000.0f, new ArrayList<>(), 0, new Outfit(null, null, null), null, map.getLocations().get(0));

        final int EXIT = 0, BACK = 1, INVENTORY = 1, SHOP = 2, PROFILE = 3, MAP = 4, EVENT = 5, TUTORIAL = 6, SORT_PRICE_ASCENDING = 2, SORT_PRICE_DESCENDING = 3, SORT_NAME_ALPHABETICAL = 4, SORT_NAME_REVERSE_ALPHABETICAL = 5, BUY = 6, EQUIP = 2, MOVEMEMENT = 2, YES = 1, NO = 0;
        int choice = -1, choice2 = -1, index = -1, index2 = -1, response = -1;
        boolean death = false;

        display.displayCreation(user,sc);
        display.displayWelcome(user);
        display.displayTutorial();

        while(!death){ //The game continues until the user's death
            choice2 = -1;
            display.displayMainMenu();

            do{
                System.out.print(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Your choice : ");
                choice = display.choice(sc);
                switch (choice) {
                    case INVENTORY:
                        display.displayInventoryMenu(user);
                        do{
                            System.out.print(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Your choice : ");
                            choice2 = display.choice(sc);
                            switch(choice2){
                                case EQUIP:
                                    if(user.getInventory().size() <= 0){
                                        System.out.println(Colors.LIGHT_RED + "!!!" + Colors.RESET + " You have no items to equip");
                                    }
                                    else{
                                        do{
                                            System.out.print(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " Type the number of the item you want to equip : ");
                                            index = display.choice(sc);
                                        }while(index < 1 || index > user.getInventory().size());
                                        Stockable equipment = user.getInventory().get(index - 1);

                                        if(user.equip(user.getInventory().get(index - 1)) == true){
                                            System.out.print(Colors.LIGHT_CYAN + "->" + Colors.RESET + " The item " + Colors.LIGHT_CYAN + "< " + equipment.getName() + " >" + Colors.RESET + " was ");
                                        }
                                        else{
                                            System.out.print(Colors.LIGHT_CYAN + "->" + Colors.RESET + " The item " + Colors.LIGHT_CYAN + "< " + equipment.getName() + " >" + Colors.RESET + " is already ");
                                        }

                                        if(equipment instanceof Food){
                                            System.out.println("consumed");
                                        }
                                        else{
                                            System.out.println("equipped");
                                        }
                                    }
                                    break;

                                case BACK:
                                    break;

                                case EXIT:
                                    death = true;
                                    break;
                            }
                        }while(choice2 != EXIT && choice2 != BACK && choice2 != EQUIP);

                        break;

                    case SHOP:
                        do{
                            display.displayShopMenu(shop);
                            System.out.print(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Your choice : ");
                            choice2 = display.choice(sc);
                            switch(choice2){
                                case SORT_PRICE_ASCENDING:
                                    shop.sortStock(SortMode.PRICE_ASCENDING);
                                    break;

                                case SORT_PRICE_DESCENDING:
                                    shop.sortStock(SortMode.PRICE_DESCENDING);
                                    break;

                                case SORT_NAME_ALPHABETICAL:
                                    shop.sortStock(SortMode.NAME_ALPHABETICAL);
                                    break;

                                case SORT_NAME_REVERSE_ALPHABETICAL:
                                    shop.sortStock(SortMode.NAME_REVERSE_ALPHABETICAL);
                                    break;

                                case BUY:
                                    do{
                                        System.out.print(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " Type the number of the item you want to buy : ");
                                        index = display.choice(sc);
                                    }while(index < 1 || index > shop.getStock().size());
                                    shop.addToCart(shop.getStock().get(index-1));

                                    do{
                                        do{
                                            System.out.print(Colors.LIGHT_CYAN + "->" + Colors.RESET + " Do you want to add any item ? (1 : Yes / 0 : No) : ");
                                            response = display.choice(sc);
                                        }while(response != YES && response != NO);
                                        if(response == YES){
                                            do{
                                                System.out.print(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " Type the number of the item you want to add to the cart : ");
                                                index = display.choice(sc);
                                            }while(index < 1 || index > shop.getStock().size() || (!(shop.getStock().get(index-1) instanceof Capsule) && shop.getCart().contains(shop.getStock().get(index-1))));
                                            shop.addToCart(shop.getStock().get(index-1));
                                        }
                                    }while(response == YES);

                                    if(shop.buyCartStockables(user) == true){
                                        System.out.println(Colors.LIGHT_GREEN + "\n!!!" + Colors.RESET + " The purchase was successful");
                                    }
                                    else{
                                        System.out.println(Colors.LIGHT_RED + "\n!!!" + Colors.RESET + " You don't have enough money to make the purchase");
                                    }
                                    
                                    break;

                                case BACK:
                                    break;

                                case EXIT:
                                    death = true;
                                    break ;
                            }
                        }while(choice2 != EXIT && choice2 != BACK);
                        break;

                    case PROFILE:
                        display.displayProfileMenu(user);
                        do{
                            System.out.print(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Your choice : ");
                            choice2 = display.choice(sc);
                            switch(choice2){
                                case BACK:
                                    break;

                                case EXIT:
                                    death = true;
                                    break;
                            }
                        }while(choice2 != EXIT && choice2 != BACK);
                        break;

                    case MAP:
                        display.displayMapMenu(map,user);
                        do{
                            System.out.print(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Your choice : ");
                            choice2 = display.choice(sc);
                            switch(choice2){
                                case BACK:
                                    break;

                                case EXIT:
                                    death = true;
                                    break;

                                case MOVEMEMENT:
                                    do{
                                        System.out.print(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " Type the number of the location you want to visit : ");
                                        index = display.choice(sc);
                                    }while(index < 1 || index > map.getLocations().size());

                                    if(user.getVehicle() == null){
                                        System.out.println(Colors.LIGHT_RED + "\n!!!" + Colors.RESET + " You can't travel without any vehicle");
                                        break;
                                    }

                                    do{
                                        System.out.print(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " Do you want to go through a specific location ? (1 : Yes / 0 : No) : ");
                                        response = display.choice(sc);
                                    }while(response != NO && response != YES);

                                    ArrayList<Location> locations = new ArrayList<Location>();

                                    if(response == YES){
                                        do{
                                            System.out.print(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " Type the number of the location you want to go through : ");
                                            index2 = display.choice(sc);
                                        }while(index2 < 1 || index2 > map.getLocations().size());
                                        locations = map.shortestPathWithStop(user.getCurrentLocation(), map.getLocations().get(index2 - 1), map.getLocations().get(index - 1), user.getVehicle().getType());
                                    }
                                    else{
                                        locations = map.shortestPath(user.getCurrentLocation(), map.getLocations().get(index - 1), user.getVehicle().getType());
                                    }

                                    if(locations.isEmpty()){
                                        System.out.println(Colors.LIGHT_RED + "\n!!!" + Colors.RESET + " You couldn't visit "+ Colors.LIGHT_CYAN + "< " + map.getLocations().get(index - 1).getName() + " >" + Colors.RESET + " because there isn't any path that leads to there");
                                    }
                                    else{
                                        Float distanceTraveled = map.totalDistancePaths(locations, user.getVehicle().getType());
                                        Float travelTime = user.getVehicle().getTravelTime(distanceTraveled);
                                        int hunger = (int) ((distanceTraveled*100)/map.totalDistancePaths(map.getLocations(),VehicleType.CAR));
                                        float money = 500 * (locations.size() - 1);
                                        //The user gains 500 for each location visited
                                        if(hunger + user.getHunger() >= 100){
                                            System.out.println(Colors.LIGHT_RED + "\n!!!" + Colors.RESET + " You can't visit "+ Colors.LIGHT_CYAN + "< " + map.getLocations().get(index - 1).getName() + " >" + Colors.RESET + " because You have a hunger rate of " + user.getHunger() + " %, and if you make this journey, your hunger rate will increase to " + (hunger + user.getHunger()) + " % resulting from your death");
                                        }
                                        else{
                                            System.out.println(Colors.LIGHT_CYAN + "\n-->" + Colors.RESET + " You have passed through the following locations (Distance traveled = " + Colors.LIGHT_CYAN + distanceTraveled + " km" + Colors.RESET + " / Temps du trajet = " + Colors.LIGHT_CYAN + travelTime + " hours" + Colors.RESET + ") : ");
                                            for(Location location : locations){
                                                System.out.println(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " " + location.getName());
                                            }
                                            user.setCurrentLocation(map.getLocations().get(index - 1));
                                            System.out.println(Colors.LIGHT_YELLOW + "\n!!!" + Colors.RESET + " The journey increased your hunger rate from " + user.getHunger() + " % to " + (hunger + user.getHunger()) + " %");
                                            user.addHunger(hunger);
                                            System.out.println(Colors.LIGHT_YELLOW + "!!!" + Colors.RESET + " You gained " + money + " zenis during your journey");
                                            user.addMoney(money);
                                        }
                                    }
                                    System.out.println(Colors.LIGHT_YELLOW + "!!!" + Colors.RESET + " You are currently located at " + Colors.LIGHT_CYAN + "< " + user.getCurrentLocation().getName() + " >" + Colors.RESET);
                                    break;
                            }
                        }while(choice2 != EXIT && choice2 != BACK && choice2 != MOVEMEMENT);
                        break;

                    case EVENT:
                        display.displayEventMenu();
                        Event event = events.getRandomEvent();
                        float money = user.getMoney();
                        
                        switch(event.getType()){
                            case STORY:
                                System.out.println(Colors.LIGHT_GREEN + "[STORY] " + event.getDescription() + Colors.RESET);
                                if(events.getAllEvents().get(EventTypeName.STORY).size() <= 0){
                                    System.out.println(Colors.LIGHT_YELLOW + "\n!!! Congratulations, You survived\n" + Colors.RESET);
                                    sc.close();
                                    return ;
                                }
                                break;

                            case DANGER:
                                System.out.println(Colors.LIGHT_RED + "[DANGER] " + event.getDescription() + Colors.RESET);
                                System.out.println(Colors.LIGHT_CYAN + "->" + Colors.RESET + " Locations targetted : ");
                                for(Location location : event.getTargeLocations()){
                                    System.out.println(Colors.LIGHT_RED + "--> " + (location.getId() + 1) + ". " + Colors.RESET + location.getName());
                                }

                                System.out.println("");
                                do{
                                    System.out.print(Colors.LIGHT_YELLOW + "!!!" + Colors.RESET + " You are currently located at " + Colors.LIGHT_CYAN + "< " + user.getCurrentLocation().getName() + " >" + Colors.RESET + " , do you want to run away ? (1 : Yes / 0 : No) : ");
                                    response = display.choice(sc);
                                }while(response != NO && response != YES);

                                if(response == NO){
                                    System.out.println(Colors.LIGHT_YELLOW + "!!!" + Colors.RESET + " You have decided to stay at your current location");
                                }
                                else{
                                    do{
                                        display.displayLocations(map,user);
                                        display.displayPaths(map,user);
                                        display.display10Locations(map,user);
                                        System.out.print(Colors.LIGHT_CYAN + "\n->" + Colors.RESET + " Type the number of the location you want to escape to : ");
                                        index = display.choice(sc);
                                    }while(index < 1 || index > map.getLocations().size());

                                    ArrayList<Location> locations = new ArrayList<Location>();
                                    boolean verif = true;

                                    if(user.getVehicle() == null){
                                        System.out.println(Colors.LIGHT_RED + "!!!" + Colors.RESET + " You can't travel without any vehicle");
                                        verif = false;
                                    }

                                    if(verif){
                                        locations = map.shortestPath(user.getCurrentLocation(), map.getLocations().get(index - 1), user.getVehicle().getType());
                                    }

                                    if(locations.isEmpty()){
                                        System.out.println(Colors.LIGHT_RED + "!!!" + Colors.RESET + " You couldn't access to this location");
                                    }
                                    else{
                                        Float distanceTraveled = map.totalDistancePaths(locations, user.getVehicle().getType());
                                        Float travelTime = user.getVehicle().getTravelTime(distanceTraveled);
                                        int hunger = (int) ((distanceTraveled*100)/map.totalDistancePaths(map.getLocations(),VehicleType.CAR));
                                        money = 500 * (locations.size() - 1);

                                        if(hunger + user.getHunger() >= 100){
                                            System.out.println(Colors.LIGHT_RED + "\n!!!" + Colors.RESET + " You can't visit "+ Colors.LIGHT_CYAN + "< " + map.getLocations().get(index - 1).getName() + " >" + Colors.RESET + " because You have a hunger rate of " + user.getHunger() + " %, and if you make this journey, your hunger rate will increase to " + (hunger + user.getHunger()) + " % resulting from your death");
                                        }
                                        else{
                                            System.out.println(Colors.LIGHT_CYAN + "\n-->" + Colors.RESET + " You have passed through the following locations (Distance traveled = " + Colors.LIGHT_CYAN + distanceTraveled + " km" + Colors.RESET + " / Temps du trajet = " + Colors.LIGHT_CYAN + travelTime + " hours" + Colors.RESET + ") : ");
                                            for(Location location : locations){
                                                System.out.println(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " " + location.getName());
                                            }
                                            user.setCurrentLocation(map.getLocations().get(index - 1));
                                            System.out.println(Colors.LIGHT_YELLOW + "\n!!!" + Colors.RESET + " The journey increased your hunger rate from " + user.getHunger() + " % to " + (hunger + user.getHunger()) + " %");
                                            user.addHunger(hunger);
                                            System.out.println(Colors.LIGHT_YELLOW + "!!!" + Colors.RESET + " You gained " + money + " zenis during your journey");
                                            user.addMoney(money);
                                        }
                                    }
                                    System.out.println(Colors.LIGHT_YELLOW + "!!!" + Colors.RESET + " You are currently located at " + Colors.LIGHT_CYAN + "< " + user.getCurrentLocation().getName() + " >" + Colors.RESET);
                                }

                                death = event.eventEffect(user);

                                if(death == true){
                                    death = true;
                                }
                                else{
                                    System.out.println(Colors.LIGHT_YELLOW + "!!!" + Colors.RESET + " You survived the danger");
                                }

                                break;

                            case BONUS:
                                death = event.eventEffect(user);
                                System.out.println(Colors.LIGHT_GREEN + "[BONUS] " + event.getDescription() + (user.getMoney() - money) + " zenis" + Colors.RESET);
                                break;

                            case MALLUS:
                                death = event.eventEffect(user);
                                System.out.println(Colors.LIGHT_RED + "[MALLUS] " + event.getDescription() + (money - user.getMoney()) + " zenis" + Colors.RESET);
                                break;

                            case ATTACK:
                                System.out.println(Colors.LIGHT_RED + "[ATTACK] " + event.getDescription() + Colors.RESET);
                                death = event.eventEffect(user);
                                if(death == false){
                                    System.out.println(Colors.LIGHT_YELLOW + "!!!" + Colors.RESET + " You survived the danger but your outfit was destroyed");
                                }
                                else{
                                    death = true;
                                }
                                break;
                        }
                        
                        break;

                    case EXIT:
                        death = true;
                        break;

                    case TUTORIAL:
                        display.displayTutorialMenu();
                        break;
                }

            }while(death == false && choice != TUTORIAL && choice2 != EXIT && choice2 != BACK && choice2 != EQUIP && choice2 != MOVEMEMENT && choice != EVENT);
            
        }
        System.out.println(Colors.LIGHT_PURPLE + "\n!!! Unfortunately, you died\n" + Colors.RESET);
        sc.close();
        return ;
    }
}
