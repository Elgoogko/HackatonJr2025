package com.main.hackatonjr;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Display {

    public void displayInventory(User user){
        int counter = 1;
        for(Stockable stockable : user.getInventory()){
            System.out.print(Colors.LIGHT_CYAN + counter + ". " + Colors.RESET + stockable.getName());
            if(user.isEquipped(stockable) == true){
                System.out.println(Colors.LIGHT_CYAN + " (Equipped)" + Colors.RESET);
            }
            else{
                System.out.println("");
            }
            counter++;
        }
    }

    public String stockableName(Stockable stockable){
        if(stockable == null){
            return "None";
        }
        else{
            return stockable.getName();
        }
    }

    public void displayProfile(User user){
        System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Username : " + Colors.LIGHT_CYAN + user.getName() + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Current location : " + Colors.LIGHT_CYAN + user.getCurrentLocation().getName() + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Money : " + Colors.LIGHT_CYAN + user.getMoney() + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Hunger : " + Colors.LIGHT_CYAN + user.getHunger() + "%" + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Vehicle : " + Colors.LIGHT_CYAN + stockableName(user.getVehicle()) + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Outfit : ");
        System.out.println(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " Head : " + Colors.LIGHT_CYAN + stockableName(user.getOutfit().getHead()) + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " Top : " + Colors.LIGHT_CYAN + stockableName(user.getOutfit().getTop()) + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "---->" + Colors.RESET + " Bottom : " + Colors.LIGHT_CYAN + stockableName(user.getOutfit().getBottom()) + Colors.RESET);
    }

    public void displayShop(Shop shop){
        int counter = 1;
        for(Stockable stockable : shop.getStock()){
            System.out.println(Colors.LIGHT_CYAN + counter + ". " + Colors.RESET + stockable.getName()  + " (" + stockable.getPrice() + " zenis)");
            counter++;
        }
    }

    public String coloredLocationName(Location location, User user){
        if(location.getId() == user.getCurrentLocation().getId()){
            return (Colors.BOLD + Colors.GREEN + location.getName() + Colors.RESET + Colors.LIGHT_CYAN);
        }
        else if(location.isCondemned() == true){
            return (Colors.BOLD + Colors.RED + location.getName() + Colors.RESET + Colors.LIGHT_CYAN);
        }
        else{
            return (Colors.LIGHT_WHITE + location.getName() + Colors.LIGHT_CYAN);
        }
    }

    public void displayLocations(GameMap map, User user){
        int counter = 1;
        System.out.println(Colors.LIGHT_CYAN + "\n->" + Colors.RESET + " Locations (" + map.getLocations().size() + ") : \n" + Colors.LIGHT_CYAN);
        for(Location location : map.getLocations()){
            System.out.println(counter + ". " + coloredLocationName(location,user));
            counter++;
        }
        System.out.print(Colors.RESET);
    }

    //Display all the possible paths from the current location of the user
    public void displayPaths(GameMap map, User user){
        String string = "";

        System.out.println(Colors.LIGHT_CYAN + "\n->" + Colors.RESET + " Paths : \n");
        System.out.println(Colors.LIGHT_CYAN + (user.getCurrentLocation().getId() + 1) + ". " + Colors.RESET + coloredLocationName(user.getCurrentLocation(),user));
        for(Location location : map.getLocations()){
            if(location != user.getCurrentLocation() && !location.isCondemned()){
                if(!map.shortestPath(user.getCurrentLocation(),location,VehicleType.PILLAR).isEmpty()){
                    string = "Pillar";
                }

                if(!map.shortestPath(user.getCurrentLocation(),location,VehicleType.CLOUD).isEmpty()){
                    if(string != ""){
                        string += " / ";
                    }
                    string += "Cloud";
                }

                if(!map.shortestPath(user.getCurrentLocation(),location,VehicleType.CAR).isEmpty()){
                    if(string != ""){
                        string += " / ";
                    }
                    string += "Car";
                }

                if(string != ""){
                    System.out.println("---> " + Colors.LIGHT_CYAN + (location.getId() + 1) + ". " + Colors.RESET + coloredLocationName(location,user) + " (" + string + ")");
                }
            }
        }

    }

    public String coloredLocationId(int id, GameMap map, User user){
        int number = id + 1;
        if(id == user.getCurrentLocation().getId()){
            return (Colors.BOLD + Colors.BLINK + Colors.GREEN + number + Colors.RESET + Colors.LIGHT_CYAN);
        }
        else if(map.getLocations().get(id).isCondemned() == true){
            return (Colors.BOLD + Colors.BLINK + Colors.RED + number + Colors.RESET + Colors.LIGHT_CYAN);
        }
        else{
            return (Colors.LIGHT_WHITE + number + Colors.LIGHT_CYAN);
        }
    }

    public void display10Locations(GameMap map, User user){
        System.out.println("\n " + coloredLocationId(map.getLocations().get(0).getId(),map,user) + " <---------C-A-R---------->  " + coloredLocationId(map.getLocations().get(1).getId(),map,user) + "  <-----------------------> " + coloredLocationId(map.getLocations().get(9).getId(),map,user));
        System.out.println(" ^                            ^ ^                           ^");
        System.out.println(" |                       _____| |                           |");
        System.out.println(" |                      |       |                           v");
        System.out.println(" C                      |       |                           " + coloredLocationId(map.getLocations().get(8).getId(),map,user));
        System.out.println(" L                      |       |                           ^");
        System.out.println(" O                      |       |                           |");
        System.out.println(" U                      |       |                           v");
        System.out.println(" D                      |       |                           " + coloredLocationId(map.getLocations().get(7).getId(),map,user));
        System.out.println(" |  ____________________|       |                           ^");
        System.out.println(" | |                            |                           |");
        System.out.println(" v v                            v                           v");
        System.out.println("  " + coloredLocationId(map.getLocations().get(2).getId(),map,user) + " <----------> " + coloredLocationId(map.getLocations().get(3).getId(),map,user) + " <----------> " + coloredLocationId(map.getLocations().get(4).getId(),map,user) + " <---------> " + coloredLocationId(map.getLocations().get(5).getId(),map,user) + " <---------> " + coloredLocationId(map.getLocations().get(6).getId(),map,user));
        System.out.println(" ^                                                          ^");
        System.out.println(" |                                                          |");
        System.out.println(" |________________________C_L_O_U_D_________________________|" + Colors.RESET);
    }

    // MENU

    public int choice(Scanner sc){
        String string = sc.nextLine();
        if(string == null || string.isEmpty()){
            return -1;
        }
        try{
            return Integer.parseInt(string);
        }
        catch(NumberFormatException e){
            return -1;
        }        
    }

    public void displayEmptyLine(int size){
        int i=0;
        System.out.print(" +");
        for(i=1;i<size-1;i++){
            System.out.print("-");
        }
        System.out.print("+\n");
    }
    
    public void displayMenu(List<String> menu){
        if(menu.size() <= 0){
            return ;
        }

        int max=menu.get(0).length();
        int i=0,j=0;
        for(i=0;i<menu.size();i++){
            if(menu.get(i).length() > max){
                max = menu.get(i).length();
            }
        }
        //Get the max length from the menu choices

        int size=max+4;

        System.out.print("\n");

        if(menu.get(0) != ""){//There is a title for the menu
            displayEmptyLine(size);
            System.out.print(" | ");
            for(i=0;i<max;i++){
                if(i < (max-menu.get(0).length())/2 || i >= (max+menu.get(0).length())/2){
                    System.out.print(" ");
                }
                else if(i == (max-menu.get(0).length())/2){
                    System.out.print(Colors.LIGHT_CYAN + menu.get(0) + Colors.RESET);
                }
            }
            System.out.print(" |\n");
        }

        displayEmptyLine(size);

        if(menu.size() <= 1){
            return ;
        }
    
        for(i=1;i<menu.size();i++){
            System.out.print(" | ");
            System.out.print(menu.get(i));
            for(j=0;j<max-menu.get(i).length();j++){
                System.out.print(" ");
            }
            System.out.print(" |\n");
        }

        displayEmptyLine(size);
    }

    public void displayCreation(User user, Scanner sc){
        displayMenu(Arrays.asList("Creation"));

        do{
            System.out.print("--> Enter your username (Maximum of 10 characters) : ");
            user.setName(sc.nextLine());
        }while(user.getName() == null || user.getName().length() > 10 || user.getName().length() <= 0);
    }

    public void displayWelcome(User user){
        displayMenu(Arrays.asList("Welcome " + user.getName()));
    }

    public void displayTutorial(){
       System.out.println(Colors.LIGHT_CYAN + "->" + Colors.RESET + " You are a namekian that wants to survive during Frieza's invasion. Your goal is to go through the events until goku arrives to defeat this monster");

       System.out.println(Colors.LIGHT_CYAN + "\n-> Shop" + Colors.RESET);
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " There are 3 types of items that can be bought from the shop : ");
       System.out.println(Colors.LIGHT_PURPLE + "----> Gears :" + Colors.RESET + " That can help you resist attacks from enemies");
       System.out.println(Colors.LIGHT_PURPLE + "----> Foods :" + Colors.RESET + " That can help you reduce your hunger");
       System.out.println(Colors.LIGHT_PURPLE + "----> Vehicles :" + Colors.RESET + " That can help you visit other locations");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Each item costs a certain amount of zenis that you can buy with your money");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " When you buy an item it disappears from the shop except for the capsules");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " The capsules give you a random item from the shop according to their colors");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " The items you buy are added to your inventory, that you can equip later");

       System.out.println(Colors.LIGHT_CYAN + "\n-> Map" + Colors.RESET);
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " The map represents the namek planet with different locations");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Each location is linked to others by paths that can be taken with specific types of vehicles");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " When you make a journey you automatically take the shortest path");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " You can choose to go through another location while making the journey");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " You gain a certain amount of money for each journey you make");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " Your hunger rate increases with each journey you make");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " You can't take journeys that can result on increasing your hunger rate above 100 %");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " You need the appropriate vehicle to go to a specific location");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " On the map, if there aren't any label on the line linking 2 locations, it means that the path can be taken with any vehicles excpect on foot");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " You can't visit condemned locations");

       System.out.println(Colors.LIGHT_CYAN + "\n-> Events" + Colors.RESET);
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " There are 4 types of events : ");
       System.out.println(Colors.LIGHT_PURPLE + "----> Story :" + Colors.RESET + " Follows the main plot. You will need to finish it in order to survive");
       System.out.println(Colors.LIGHT_PURPLE + "----> Danger :" + Colors.RESET + " Causes the condemnation of some locations. You must escape from the condemned locations to survive");
       System.out.println(Colors.LIGHT_PURPLE + "----> Bonus :" + Colors.RESET + " Gives you a certain amount of money");
       System.out.println(Colors.LIGHT_PURPLE + "----> Mallus :" + Colors.RESET + " Takes from you a certain amount of money");
       System.out.println(Colors.LIGHT_PURPLE + "----> Attack :" + Colors.RESET + " Targets you. You will need a full outfit (head, top, and bottom gears) otherwise you will die. Even if you survive, you loose your outfit");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " The types of events are choosen randomly");
       System.out.println(Colors.LIGHT_CYAN + "-->" + Colors.RESET + " You must go through events until you finish the story (Goku arrives to defeat Frieza and save namekians)");
    }

    public void displayTutorialMenu(){
        displayMenu(Arrays.asList("Tutorial"));
        displayTutorial();
    }

    public void displayMainMenu(){
        List<String> menu = Arrays.asList(
            "Menu",
            "0 - Exit",
            "1 - Inventory",
            "2 - Shop",
            "3 - Profile",
            "4 - Map",
            "5 - Events",
            "6 - Tutorial"
        );

        displayMenu(menu);
    }

    public void displayInventoryMenu(User user){
        List<String> menu=Arrays.asList(
            "",
            "0 - Exit",
            "1 - Back",
            "2 - Equip"
        );

        displayMenu(Arrays.asList("Inventory (" + user.getInventory().size() + " items)"));
        displayInventory(user);
        displayMenu(menu);
    }

    public void displayShopMenu(Shop shop){
        List<String> menu=Arrays.asList(
            "",
            "0 - Exit",
            "1 - Back",
            "2 - Sort by ascending price",
            "3 - Sort by descending price",
            "4 - Sort by alphabetical order (A -> Z)",
            "5 - Sort by reversed alphabetical order (Z -> A)",
            "6 - Buy"
        );

        displayMenu(Arrays.asList("Shop (" + shop.getStock().size() + " items)"));
        displayShop(shop);
        displayMenu(menu);
    }

    public void displayProfileMenu(User user){
        List<String> menu=Arrays.asList(
            "",
            "0 - Exit",
            "1 - Back"
        );

        displayMenu(Arrays.asList("Profile"));
        displayProfile(user);
        displayMenu(menu);
    }

    public void displayMapMenu(GameMap map, User user){
        List<String> menu=Arrays.asList(
            "",
            "0 - Exit",
            "1 - Back",
            "2 - Move"
        );

        displayMenu(Arrays.asList("Map"));
        displayLocations(map,user);
        displayPaths(map,user);
        display10Locations(map,user);
        displayMenu(menu);
    }

    public void displayEventMenu(){
        displayMenu(Arrays.asList("Events"));
    }
}