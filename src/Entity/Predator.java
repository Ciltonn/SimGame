package Entity;
import Coordinates.Coordinates;
import Location.Location;

public class Predator extends Creature {
    private int power;

    public Predator(Coordinates coordinates, int speed, int hp, int power) {
        super(coordinates, speed, hp);
        this.power = power;
    }

    @Override
    public boolean isPossibleCell(Coordinates coordinates, Location location){
        Entity entity = location.getEntity(coordinates);
        return entity==null|| !(entity instanceof Rock) && !(entity instanceof Tree) && !(entity instanceof Predator) && !(entity instanceof Grass);
    }
}
