package Renderer;

import Coordinates.Coordinates;
import Entity.Entity;
import Location.Location;


public class Renderer {

    public void renderInConsole (Location location){
        for(int i = Coordinates.MAX_LINE-1; i>=0; i--){
            for (int j=0; j<=Coordinates.MAX_COLUMN-1; j++) {
                Coordinates coordinates = new Coordinates(i,j);
                if (location.getEntity(coordinates)==null) {
                    System.out.print(" . ");
                } else {
                   getSprite(location,coordinates);
                }
            }
            System.out.println(" ");
        }
    }

    private void getSprite(Location location, Coordinates coordinates) {
        Entity entity = location.getEntity(coordinates);

        switch (entity.getClass().getSimpleName()){
            case "Herbivore":
                System.out.print(" \uD83D\uDC07");
                    break;
            case "Predator":
                System.out.print(" \uD83D\uDC3A");
                break;
            case "Grass":
                System.out.print("\ud83c\udf3f ");
                break;
            case "Tree":
                System.out.print("\ud83c\udf33 ");
                break;
            case "Rock":
                System.out.print(" \u26f0");
                break;

        }

    }
}
