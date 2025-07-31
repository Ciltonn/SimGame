package Entity;
import Coordinates.Coordinates;
import Location.Location;

public class Herbivore extends Creature {


    public Herbivore(Coordinates coordinates, int speed, int hp) {
        super(coordinates, speed, hp);
    }

    @Override
    public boolean isPossibleCell (Coordinates coordinates, Location location) {
    Entity entity = location.getEntity(coordinates);
    return entity == null || !(entity instanceof Rock) && !(entity instanceof Tree) && !(entity instanceof Predator) && !(entity instanceof Herbivore);
}


}
