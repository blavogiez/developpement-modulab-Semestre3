package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.algorithm.Trap;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class TrapManager {
    static private Trap[][] traps;
    static private Map<Trap, Integer> trapMap;

    public static Maze generate(Maze maze, int numberOfRandomTraps, int numberOfTeleporter, int numberOfPush, int numberOfFake, int numberOfStun) {
        TrapManager.traps = maze.getGrid();
        trapMap = new EnumMap<>(Trap.class);
        trapMap.put(Trap.RANDOM,numberOfRandomTraps);
        trapMap.put(Trap.PUSH, numberOfPush);
        trapMap.put(Trap.TELEPORTER, numberOfTeleporter);
        trapMap.put(Trap.FAKE, numberOfFake);
        trapMap.put(Trap.STUN, numberOfStun);
        generateTraps(maze);
        randomizeRandomTrap();
        maze.setGrid(traps);
        return maze;
    }

    private static void randomizeRandomTrap() {
        Random random = new Random();
        for (int y = 0; y < traps.length; y++) {
            for (int x = 0; x < traps[y].length; x++) {
                if (traps[y][x] == Trap.RANDOM) {
                    traps[y][x] = Trap.values()[random.nextInt(Trap.TELEPORTER.ordinal(),Trap.values().length)];
                }
            }
        }
    }

    public static void generateTraps(Maze maze) {
        for(Map.Entry<Trap, Integer> entry : trapMap.entrySet()){
            for (int i = 0; i < entry.getValue(); i++){
                Position position = getRandomCell(maze);
                setTrap(position.getX(),  position.getY(), entry.getKey());
            }
        }
    }

    public static Position getRandomCell(Maze maze){
        Random random = new Random();
        int x, y;
        Position position;
        do {
            y = random.nextInt(traps.length);
            x = random.nextInt(traps[y].length);
            position = new Position(x, y);
        } while (getTrap(y,x)!=Trap.PATH || position.equals(maze.getExitPosition()) || position.equals(maze.getEntryPosition()));
        return position;
    }

    static Enum<Trap> getTrap(int y, int x){
        return traps[y][x];
    }

    public static void setTrap(int y, int x, Trap trap){
        traps[y][x] = trap;
    }




}
