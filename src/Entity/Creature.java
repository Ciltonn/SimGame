package Entity;
import Location.Location;
import Coordinates.Coordinates;
import BFS.BFS;

import java.util.List;

public abstract class Creature extends Entity {
    private Coordinates coordinates;
    private int speed;
    private int hp;

    public Creature(Coordinates coordinates, int speed, int hp) {
        this.coordinates = coordinates;
        this.speed = speed;
        this.hp = hp;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public static Class<? extends Entity> getTarget(Class<? extends Creature> hunterClass) {
        if (hunterClass.equals(Predator.class)) {
            return Herbivore.class;
        }
        if (hunterClass.equals(Herbivore.class)) {
            return Grass.class;
        }
        return null;
    }

    public void makeMove(Location location, BFS bfs ){
        List<Coordinates> path = bfs.pathFinder(location, getClass(), getCoordinates());
        if (path != null && path.size() > 1) {
            moveEntity(getCoordinates(), path.get(1), location);
        }
    }

    public boolean moveEntity (Coordinates oldPosition, Coordinates newPosition, Location location){
        Entity entity = location.getEntity(oldPosition);
        if (entity == null || !isPossibleCell(newPosition, location)) {
            return false;
        }
        location.removeEntity(oldPosition);
        location.addEntity(newPosition, entity);
        if (entity instanceof Creature) {
            ((Creature) entity).setCoordinates(newPosition);

        }
        return true;
    }

        public abstract boolean isPossibleCell(Coordinates coordinates, Location location);


}
