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
        User user = new User("", 100000.0f, new ArrayList<>(), 0, new Outfit(null, null, null), null, map.getLocations().get(0));

        final int EXIT = 0, BACK = 1, INVENTORY = 1, SHOP = 2, PROFILE = 3, MAP = 4, EVENT = 5, SORT_PRICE_ASCENDING = 2, SORT_PRICE_DESCENDING = 3, SORT_NAME_ALPHABETICAL = 4, SORT_NAME_REVERSE_ALPHABETICAL = 5, BUY = 6, EQUIP = 2, MOVEMEMENT = 2, YES = 1, NO = 0;
        int choice = -1, choice2 = -1, index = -1, index2 = -1, response = -1;
        Stockable equipment;
        boolean death = false;

        display.displayCreation(user,sc);
        display.displayWelcome(user);
        display.displayTutorial();

        while(!death){ //The game continues until the user's death
            choice2 = -1;
            display.displayMainMenu();

            do{
                System.out.print("--> Your choice : ");
                choice = display.choice(sc);
                switch (choice) {
                    case INVENTORY:
                        display.displayInventoryMenu(user);
                        do{
                            System.out.print("--> Your choice : ");
                            choice2 = display.choice(sc);
                            switch(choice2){
                                case EQUIP:
                                    if(user.getInventory().size() <= 0){
                                        System.out.println("!!! You have no items to equip !");
                                    }
                                    else{
                                        do{
                                            System.out.print("----> Type the number of the item you want to equip : ");
                                            index = display.choice(sc);
                                        }while(index < 1 || index > user.getInventory().size());
                                        equipment = user.getInventory().get(index - 1);

                                        if(user.equip(user.getInventory().get(index - 1)) == true){
                                            System.out.print("- The item '" + equipment.getName() + "' was ");
                                        }
                                        else{
                                            System.out.print("- The item '" + equipment.getName() + "' is already ");
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
                            System.out.print("--> Your choice : ");
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
                                        System.out.print("----> Type the number of the item you want to buy : ");
                                        index = display.choice(sc);
                                    }while(index < 1 || index > shop.getStock().size());
                                    shop.addToCart(shop.getStock().get(index-1));

                                    do{
                                        do{
                                            System.out.print("- Do you want to add any item ? (1 : Yes / 0 : No) : ");
                                            response = display.choice(sc);
                                        }while(response != YES && response != NO);
                                        if(response == YES){
                                            do{
                                                System.out.print("----> Type the number of the item you want to add to the cart : ");
                                                index = display.choice(sc);
                                            }while(index < 1 || index > shop.getStock().size() || (!(shop.getStock().get(index-1) instanceof Capsule) && shop.getCart().contains(shop.getStock().get(index-1))));
                                            shop.addToCart(shop.getStock().get(index-1));
                                        }
                                    }while(response == YES);

                                    if(shop.buyCartStockables(user) == true){
                                        System.out.println("- The purchase was successful");
                                    }
                                    else{
                                        System.out.println("!!! You don't have enough money to make the purchase");
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
                            System.out.print("--> Your choice : ");
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
                            System.out.print("--> Your choice : ");
                            choice2 = display.choice(sc);
                            switch(choice2){
                                case BACK:
                                    break;

                                case EXIT:
                                    death = true;
                                    break;

                                case MOVEMEMENT:
                                    do{
                                        System.out.print("----> Type the number of the location you want to visit : ");
                                        index = display.choice(sc);
                                    }while(index < 1 || index > map.getLocations().size());

                                    if(user.getVehicle() == null){
                                        System.out.println("\n!!! You can't travel without any vehicle");
                                        break;
                                    }

                                    do{
                                        System.out.print("----> Do you want to go through a specific location ? (1 : Yes / 0 : No) : ");
                                        response = display.choice(sc);
                                    }while(response != NO && response != YES);

                                    ArrayList<Location> locations = new ArrayList<Location>();

                                    if(response == YES){
                                        do{
                                            System.out.print("----> Type the number of the location you want to go through : ");
                                            index2 = display.choice(sc);
                                        }while(index2 < 1 || index2 > map.getLocations().size());
                                        locations = map.shortestPathWithStop(user.getCurrentLocation(), map.getLocations().get(index2 - 1), map.getLocations().get(index - 1), user.getVehicle().getType());
                                    }
                                    else{
                                        locations = map.shortestPath(user.getCurrentLocation(), map.getLocations().get(index - 1), user.getVehicle().getType());
                                    }

                                    if(locations.isEmpty()){
                                        System.out.println("\n!!! You couldn't visit '" + map.getLocations().get(index - 1).getName() + "'\nCause : No path leads to there");
                                    }
                                    else{
                                        Float distanceTraveled = map.totalDistancePaths(locations, user.getVehicle().getType());
                                        Float travelTime = user.getVehicle().getTravelTime(distanceTraveled);
                                        int hunger = (int) ((distanceTraveled*100)/map.totalDistancePaths(map.getLocations(),VehicleType.CAR));
                                        float money = 500 * locations.size();
                                        //The user gains 500 for each location visited
                                        if(hunger + user.getHunger() >= 100){
                                            System.out.println("\n!!! You can't visit " + map.getLocations().get(index - 1).getName() + "\nCause : You have a hunger rate of " + user.getHunger() + " % and if you make this journey, your hunger rate will become " + (hunger + user.getHunger()) + " % resulting of your death");
                                        }
                                        else{
                                            System.out.println("\n- You have passed through the following locations (Distance traveled = " + distanceTraveled + " km / Temps du trajet = " + travelTime + " hours) : ");
                                            for(Location location : locations){
                                                System.out.println("--- " + location.getName());
                                            }
                                            user.setCurrentLocation(map.getLocations().get(index - 1));
                                            System.out.println("\n- The journey increased your hunger rate from " + user.getHunger() + " % to " + (hunger + user.getHunger()) + " %");
                                            user.addHunger(hunger);
                                            System.out.println("\n- You gained " + money + " zenis during your journey");
                                            user.addMoney(money);
                                        }
                                    }
                                    System.out.println("\n- You are currently located at : '" + user.getCurrentLocation().getName() + "'");
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
                                System.out.println(Colors.GREEN + "[STORY] " + event.getDescription() + Colors.RESET);
                                if(events.getAllEvents().get(EventTypeName.STORY).size() <= 0){
                                    System.out.println("!!! Congratulations, You survived\n");
                                    sc.close();
                                    return ;
                                }
                                break;

                            case DANGER:
                                System.out.println(Colors.RED + "[DANGER] " + event.getDescription() + Colors.RESET);
                                System.out.println("- Locations targetted : ");
                                for(Location location : event.getTargeLocations()){
                                    System.out.println("---> " + (location.getId() + 1) + ". " + location.getName());
                                }

                                do{
                                    System.out.print("\n- You are currently located at '" + user.getCurrentLocation().getName() + "' , do you want to run away ? (1 : Yes / 0 : No) : ");
                                    response = display.choice(sc);
                                }while(response != NO && response != YES);

                                if(response == NO){
                                    System.out.println("!!! You decided to not run away from your current location");
                                }
                                else{
                                    do{
                                        display.displayLocations(map,user);
                                        display.displayPaths(map,user);
                                        display.display10Locations(map,user);
                                        System.out.print("\n-> Type the number of the location tou want to escape to : ");
                                        index = display.choice(sc);
                                    }while(index < 1 || index > map.getLocations().size());

                                    ArrayList<Location> locations = new ArrayList<Location>();
                                    boolean verif = true;

                                    if(user.getVehicle() == null){
                                        System.out.println("!!! You can't travel without any vehicle");
                                        verif = false;
                                    }

                                    if(verif){
                                        locations = map.shortestPath(user.getCurrentLocation(), map.getLocations().get(index - 1), user.getVehicle().getType());
                                    }

                                    if(locations.isEmpty()){
                                        System.out.println("!!! You couldn't access to this location");
                                    }
                                    else{
                                        Float distanceTraveled = map.totalDistancePaths(locations, user.getVehicle().getType());
                                        Float travelTime = user.getVehicle().getTravelTime(distanceTraveled);
                                        int hunger = (int) ((distanceTraveled*100)/map.totalDistancePaths(map.getLocations(),VehicleType.CAR));
                                        money = 500*locations.size();

                                        if(hunger + user.getHunger() >= 100){
                                            System.out.println("\n!!! You can't visit " + map.getLocations().get(index - 1).getName() + "\nCause : You have a hunger rate of " + user.getHunger() + " % and if you make this journey, your hunger rate will become " + (hunger + user.getHunger()) + " % resulting of your death");
                                        }
                                        else{
                                            System.out.println("\n- You have passed through the following locations (Distance traveled = " + distanceTraveled + " km / Temps du trajet = " + travelTime + " hours) : ");
                                            for(Location location : locations){
                                                System.out.println("--- " + location.getName());
                                            }
                                            user.setCurrentLocation(map.getLocations().get(index - 1));
                                            System.out.println("\n- The journey increased your hunger rate from " + user.getHunger() + " % to " + (hunger + user.getHunger()) + " %");
                                            user.addHunger(hunger);
                                            System.out.println("\n- You gained " + money + " zenis during your journey");
                                            user.addMoney(money);
                                        }
                                    }
                                    System.out.println("\n- You are currently located at : '" + user.getCurrentLocation().getName() + "'");
                                }

                                death = event.eventEffect(user);

                                if(death == true){
                                    death = true;
                                }
                                else{
                                    System.out.println("!!! You survived the danger");
                                }

                                break;

                            case BONUS:
                                death = event.eventEffect(user);
                                System.out.println(Colors.GREEN + "[BONUS] " + event.getDescription() + (user.getMoney() - money) + " zenis" + Colors.RESET);
                                break;

                            case MALLUS:
                                death = event.eventEffect(user);
                                System.out.println(Colors.RED + "[MALLUS] " + event.getDescription() + (money - user.getMoney()) + " zenis" + Colors.RESET);
                                break;

                            case ATTACK:
                                System.out.println(Colors.RED + "[ATTACK] " + event.getDescription() + Colors.RESET);
                                death = event.eventEffect(user);
                                if(death == false){
                                    System.out.println("!!! You survived the danger but your outfit was destroyed");
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
                }

            }while(death == false && choice2 != EXIT && choice2 != BACK && choice2 != EQUIP && choice2 != MOVEMEMENT && choice != EVENT);
            
        }
        System.out.println("!!! You died\n");
        sc.close();
        return ;
    }
}
