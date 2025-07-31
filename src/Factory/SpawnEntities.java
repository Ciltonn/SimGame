package Factory;

import Actions.Action;
import Entity.*;
import Location.Location;

import java.util.List;

public class SpawnEntities implements Action {

    public final static int COUNT_PREDATOR = 1;
    public final static int COUNT_HERBIVORE = 5;
    public final static int COUNT_GRASS = 8;

    private int currentPredator = 0;
    private int currentHerbivore = 0;
    private int currentGrass = 0;

    @Override
    public void perform(Location location) {

        countEntities(location);
        spawnEntities(location);

    }

    private void countEntities(Location location) {
        List<Entity> entitiesOnLocation = location.getEntities();
        currentHerbivore=0;
        currentPredator=0;
        currentGrass=0;

        for (Entity entity : entitiesOnLocation) {

            if (entity instanceof Herbivore) {
                currentHerbivore++;
            } else if (entity instanceof Predator) {
                currentPredator++;
            } else if (entity instanceof Grass) {
                currentGrass++;
            }

        }
    }

    private void spawnEntities(Location location) {
        Factory factory = new Factory();

        while (COUNT_PREDATOR > currentPredator) {
            factory.spawnPredator(location);
            currentPredator++;
        }

        while (COUNT_HERBIVORE > currentHerbivore) {
            factory.spawnHerbivore(location);
            currentHerbivore++;
        }

        while (COUNT_GRASS > currentGrass) {
            factory.spawnGrass(location);
            currentGrass++;
        }
    }
}
