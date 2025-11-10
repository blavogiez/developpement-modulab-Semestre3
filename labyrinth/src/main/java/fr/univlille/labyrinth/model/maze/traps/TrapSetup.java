package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class TrapSetup {
    static private TrapFactory[][] trapFactories;
    static private Map<TrapFactory, Integer> trapMap;

    public static TrapFactory[][] generate(Maze maze, int numberOfRandomTraps, int numberOfTeleporter, int numberOfPush, int numberOfFake, int numberOfStun) {
        TrapSetup.trapFactories = new TrapFactory[maze.getHeight()][maze.getWidth()];
        trapMap = new EnumMap<>(TrapFactory.class);
        trapMap.put(TrapFactory.RANDOM,numberOfRandomTraps);
        trapMap.put(TrapFactory.PUSH, numberOfPush);
        trapMap.put(TrapFactory.TELEPORTER, numberOfTeleporter);
        trapMap.put(TrapFactory.FAKE, numberOfFake);
        trapMap.put(TrapFactory.STUN, numberOfStun);
        fillPath();
        generateTraps(maze);
        randomizeRandomTrap();


        return trapFactories;
    }

    public static TrapFactory[][] generate(Maze maze, int numberOfRandomTraps){
        return generate(maze,numberOfRandomTraps,0,0,0,0);
    }

    private static void randomizeRandomTrap() {
        Random random = new Random();
        for (int x = 0; x < trapFactories.length; x++) {
            for (int y = 0; y < trapFactories[x].length; y++) {
                if (trapFactories[x][y] == TrapFactory.RANDOM) {
                    trapFactories[x][y] = TrapFactory.values()[random.nextInt(TrapFactory.TELEPORTER.ordinal(), TrapFactory.values().length)];
                }
            }
        }
    }

    public static void generateTraps(Maze maze) {
        for(Map.Entry<TrapFactory, Integer> entry : trapMap.entrySet()){
            int value = entry.getValue();
            for (int i = 0; i < value; i++){
                Position position = getRandomCell(maze);
                setTrap(position.getY(),  position.getX(), entry.getKey());
            }
        }
    }

    public static Position getRandomCell(Maze maze){
        Random random = new Random();
        int x, y;
        Position position;
        do {
            y = random.nextInt(trapFactories.length);
            x = random.nextInt(trapFactories[y].length);
            position = new Position(x, y);
        } while (getTrap(y,x)!= TrapFactory.NONE ||
                position.equals(maze.getExitPosition()) ||
                position.equals(maze.getEntryPosition()));
        return position;
    }

    public static void fillPath(){
        for (int i = 0; i< trapFactories.length; i++){
            for (int j = 0; j< trapFactories[i].length; j++){
                if (trapFactories[i][j]==null){
                    trapFactories[i][j]= TrapFactory.NONE;
                }
            }
        }
    }

    static Enum<TrapFactory> getTrap(int y, int x){
        return trapFactories[y][x];
    }

    public static void setTrap(int y, int x, TrapFactory trapFactory){
        trapFactories[y][x] = trapFactory;
    }




}
