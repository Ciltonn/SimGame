package BFS;

import Coordinates.Coordinates;
import Entity.Entity;
import Entity.Creature;
import Location.Location;

import java.util.*;

public class BFS {
    private final Queue<Coordinates> queue = new LinkedList<>();
    private final Set<Coordinates> visited = new HashSet<>();
    private final Map<Coordinates, Coordinates> path = new HashMap<>();
    private final List<Coordinates> pathToTarget = new ArrayList<>();


    public List<Coordinates> pathFinder(Location location, Class<? extends Creature> hunterClass, Coordinates start) {
        queue.clear();
        visited.clear();
        path.clear();
        pathToTarget.clear();


        if (start == null) {
            return null;
        }

        queue.add(start);
        visited.add(start);
        path.put(start, null);
        Class<? extends Entity> targetClass = Creature.getTarget(hunterClass);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            Entity entity = location.getEntity(current);


            if (targetClass.isInstance(entity)) {
                return getPathToTarget(current);
            }

            int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

            for (int[] d : dir) {
                int newRank = current.getLine() + d[0];
                int newFile = current.getColumn() + d[1];

                Coordinates neighbors = new Coordinates(newRank, newFile);

                while (checkingCoordinates(neighbors)){


                    queue.add(neighbors);
                    visited.add(neighbors);
                    path.put(neighbors, current);
                }
            }
        }
        return Collections.emptyList();
    }

    public List<Coordinates> getPathToTarget(Coordinates target) {
        pathToTarget.clear();
        Coordinates current = target;

        while (current != null) {
            pathToTarget.add(0, current);
            current = path.get(current);
        }
        return pathToTarget;
    }

    private boolean checkingCoordinates (Coordinates coordinates) {
        return coordinates != null && coordinates.isValid() && !visited.contains(coordinates);
    }

}
