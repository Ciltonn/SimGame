package Simulation;

import Coordinates.Coordinates;
import Entity.*;
import Factory.*;
import Location.Location;
import Renderer.Renderer;
import BFS.BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {

    private volatile boolean paused = false;
    private volatile boolean running = true;


    private final Location location = new Location();
    private final Factory factory = new Factory();
    private final Renderer renderer = new Renderer();
    private final BFS bfs = new BFS();
    private final SpawnEntities spawnEntities = new SpawnEntities();
    private int turnCount = 0;

    public void start2Simulation(int stepsSimulation) {
        new Thread(this::userInput).start();

        startSimulation(stepsSimulation);
    }

    private void userInput(){
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("[enter] для остановки и возобновления симуляции");
            System.out.println("[e] для выхода");

            while (running){
                String input = scanner.nextLine();

                if(input.equals("")) {
                    setPaused();
                } else if(input.equals("e")) {
                    setStop();
                    break;
                }
            }
        }
    }

    private void startSimulation(int stepsSimulation) {

        factory.setEntities(location);



        for (int i = 0; i < stepsSimulation && running; i++) {
            while (paused && running) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            if (!running) {
                break;
            }

            nextTurn();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }


    }

    private void nextTurn() {
        System.out.println("Turn" + turnCount++);
        creatureMove();
        spawnEntities.perform(location);
        renderer.renderInConsole(location);
    }


    private void creatureMove() {
        List<Entity> entities = new ArrayList<>(location.getEntities());
        for (Entity entity : entities) {
            if (entity instanceof Creature creature) {
                Coordinates coord = creature.getCoordinates();
                List<Coordinates> path = bfs.pathFinder(location, creature.getClass(), coord);
                creature.makeMove(location, bfs);
            }
        }
    }

    public void setPaused() {
        paused = !paused;
        if (paused == true) {
            System.out.println("пауза");
        } else {
            System.out.println("продолжение");
        }
    }


    public void setStop() {
        running = false;
        System.out.println("симуляция закрывается");
    }
}

