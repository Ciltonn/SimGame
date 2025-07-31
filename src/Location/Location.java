package Location;

import Coordinates.Coordinates;
import Entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
    private final Map<Coordinates, Entity> entities = new HashMap<>();

    public void addEntity(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }


    public List<Entity> getEntities(){
        return new ArrayList<>(entities.values());
    }



}
