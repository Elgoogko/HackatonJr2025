package com.main.hackatonjr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

public class GameMap {
    private ArrayList<Location> locations;
    private ArrayList<Path> paths;

    public GameMap(){
        //10 locations
        this.locations = new ArrayList<Location>();
        this.locations.add(new Location(0, "Village of the great chief", "It's a village. It's ruled by a very great chief.", new ArrayList<Path>(), new Coordinates(50, 950)));
        this.locations.add(new Location(1, "Central canyon", "A huge canyon with steep walls.", new ArrayList<Path>(), new Coordinates(150, 850)));
        this.locations.add(new Location(2, "Frieza vessel", "Frieza's spaceship is landed here.", new ArrayList<Path>(), new Coordinates(75, 600)));
        this.locations.add(new Location(3, "Village of Moori", "A peaceful village inhabited by Namekians.", new ArrayList<Path>(), new Coordinates(400, 500)));
        this.locations.add(new Location(4, "House of Guru", "House of the great Namekian Guru.", new ArrayList<Path>(), new Coordinates(600, 600)));
        this.locations.add(new Location(5, "Old temple", "A mysterious and old temple.", new ArrayList<Path>(), new Coordinates(550, 50)));
        this.locations.add(new Location(6, "Namek forest", "A large cargo ship filled with goods.", new ArrayList<Path>(), new Coordinates(50, 50)));
        this.locations.add(new Location(7, "Rock bridge", "A natural bridge made of rocks.", new ArrayList<Path>(), new Coordinates(950, 300)));
        this.locations.add(new Location(8, "Underwater temple", "A mysterious underwater temple.", new ArrayList<Path>(), new Coordinates(900, 800)));
        this.locations.add(new Location(9, "Namek mines", "Mines rich in precious minerals.", new ArrayList<Path>(), new Coordinates(750, 900)));

        this.paths = new ArrayList<Path>();
        //Connect the consecutive points
        Path path = null;
        int i = 0;
        for (i=0;i<this.locations.size()-1;i++) {
            path = new Path(locations.get(i), locations.get(i+1), new ArrayList<VehicleType>(List.of(VehicleType.CLOUD,VehicleType.PILLAR,VehicleType.CAR)));
            this.paths.add(path);
            this.locations.get(i).addPath(path);
            this.locations.get(i+1).addPath(path);
        }

        //Connect other points
        Path path1 = new Path(locations.get(0), locations.get(2), new ArrayList<VehicleType>(List.of(VehicleType.CLOUD)));
        Path path2 = new Path(locations.get(1), locations.get(9), new ArrayList<VehicleType>(List.of(VehicleType.CAR)));
        Path path3 = new Path(locations.get(1), locations.get(4), new ArrayList<VehicleType>(List.of(VehicleType.CLOUD,VehicleType.PILLAR,VehicleType.CAR)));
        Path path4 = new Path(locations.get(2), locations.get(6), new ArrayList<VehicleType>(List.of(VehicleType.CLOUD)));

        this.paths.add(path1);
        this.paths.add(path2);
        this.paths.add(path3);
        this.paths.add(path4);

        this.locations.get(0).addPath(path1);
        this.locations.get(2).addPath(path1);
        this.locations.get(1).addPath(path2);
        this.locations.get(9).addPath(path2);
        this.locations.get(1).addPath(path3);
        this.locations.get(4).addPath(path3);
        this.locations.get(2).addPath(path4);
        this.locations.get(6).addPath(path4);
    }

    public ArrayList<Location> getLocations(){
        return this.locations;
    }
    public ArrayList<Path> getPaths(){
        return this.paths;
    }

    public ArrayList<Location> shortestPath(Location departure, Location arrival, VehicleType type){
        //Dijkstra's algorithm
        if (type == VehicleType.PILLAR) {
            //If it is a pillar can go directly to the arrival in a straight line 
            ArrayList<Location> finalPaths = new ArrayList<Location>();
            if(arrival.isCondemned()){
                return finalPaths;
            }
            finalPaths.add(departure);
            finalPaths.add(arrival);
            return finalPaths;
        }
        //Array with minimum distance between each location and the starting point
        Map<Location, Float> distances = new HashMap<>();

        Map<Location, Location> previous = new HashMap<>();

        //Nodes to explore (ordered by minimum distance)
        PriorityQueue<Location> queue = new PriorityQueue<>(Comparator.comparing(distances::get));

        //Initialization
        distances.put(departure, 0f);
        queue.add(departure);

        while(!queue.isEmpty()){
            Location current = queue.poll(); //Location with the minimum distance and removes it

            if(current.equals(arrival)){
                break;
            }

            for(Path path : current.getPaths()){
                boolean transportIsOk = path.getVehicleTypes().contains(type);
                Location neighbor = path.getOtherLocation(current); //Returns the location linked to the current one
                float newDistance = distances.getOrDefault(current, Float.MAX_VALUE) + path.getDistance();

                if(newDistance < distances.getOrDefault(neighbor, Float.MAX_VALUE) && transportIsOk && !neighbor.isCondemned()){
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, current);
                    queue.add(neighbor); //Adds the neighbor to the queue to explore it later
                }
            }
        }

        //Reconstruction of the path
        ArrayList<Location> finalPaths = new ArrayList<>();
        Location actual = arrival;

        while(actual != null){
            finalPaths.add(0, actual);
            actual = previous.get(actual);
        }

        //If the first location isn't the departure one --> No path found
        if(finalPaths.isEmpty() || !finalPaths.get(0).equals(departure)){
            return new ArrayList<>();
        }

        return finalPaths;
    }

    public float totalDistancePaths(ArrayList<Location> pathLocations, VehicleType type){
        float totalDistance = 0f;
        int i=0;

        if(type == VehicleType.PILLAR){
            return Coordinates.distance(pathLocations.get(0).getCoordinates(), pathLocations.get(pathLocations.size() - 1).getCoordinates());
        }

        for(i=0;i<pathLocations.size()-1;i++){
            Location actual = pathLocations.get(i);
            Location next = pathLocations.get(i+1);
            //Find the path between 2 locations
            for(Path path : actual.getPaths()){
                if (path.getOtherLocation(actual).equals(next)) {
                    totalDistance += path.getDistance();
                    break;
                }
            }
        }

        return totalDistance;
    }

    public ArrayList<Location> shortestPathWithStop(Location departure, Location stop, Location arrival, VehicleType type) {
        ArrayList<Location> firstSegment = shortestPath(departure, stop, type);
        ArrayList<Location> secondSegment = shortestPath(stop, arrival, type);

        if(firstSegment.isEmpty() || secondSegment.isEmpty()){
            return new ArrayList<>();
        }
        //Merge the 2 segments and avoiding to duplciate the stop
        firstSegment.remove(firstSegment.size()-1);
        firstSegment.addAll(secondSegment);
        return firstSegment;
    }

    //Finds a random location and returns all the locations linked to it
    public ArrayList<Location> randomLocations(){
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(this.locations.get(ThreadLocalRandom.current().nextInt(this.locations.size())));

        for(Path path : locations.get(0).getPaths()){
            locations.add(path.getOtherLocation(locations.get(0)));
        }

        return locations;
    }
}
