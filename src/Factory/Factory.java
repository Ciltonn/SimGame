package Factory;

import Entity.*;
import Location.Location;
import java.util.Random;
import Coordinates.Coordinates;

public class Factory {

    public void setEntities(Location location){

        for(int i = 0; i < SpawnEntities.COUNT_PREDATOR; i++) {
            spawnPredator(location);
        }
        for(int i = 0; i < SpawnEntities.COUNT_HERBIVORE; i++) {
            spawnHerbivore(location);
        }
        for(int i = 0; i < SpawnEntities.COUNT_GRASS; i++) {
            spawnGrass(location);
        }
        for(int i = 0; i < 2; i++) {
            spawnTree(location);
        }
        for(int i = 0; i < 2; i++) {
            spawnRock(location);
        }
    }

    public void spawnPredator(Location location){
        Coordinates coordinates = randomCoordinates(location);
        location.addEntity(coordinates, new Predator(coordinates,1,2, 1));
    }
    public void spawnHerbivore(Location location){
        Coordinates coordinates = randomCoordinates(location);
        location.addEntity(coordinates, new Herbivore(coordinates, 1,1));
    }
    public void spawnGrass(Location location){
        Coordinates coordinates = randomCoordinates(location);
        location.addEntity(coordinates, new Grass());
    }
    public void spawnTree(Location location){
        Coordinates coordinates = randomCoordinates(location);
        location.addEntity(coordinates, new Tree());
    }
    public void spawnRock(Location location){
        Coordinates coordinates = randomCoordinates(location);
        location.addEntity(coordinates, new Rock());
    }


      public Coordinates randomCoordinates(Location location) {
        Random random = new Random();
        Coordinates coordinates;

        do {

            coordinates = new Coordinates(random.nextInt(Coordinates.MAX_LINE), random.nextInt(Coordinates.MAX_COLUMN));

        } while (location.getEntity(coordinates) != null);

        return coordinates;
    }
}

