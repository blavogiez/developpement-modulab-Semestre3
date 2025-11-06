package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.algorithm.Trap;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class TrapManager {
    static private Trap[][] traps;
    static private Map<Trap, Integer> trapMap;

    public Maze generate(Maze maze, int numberOfRandomTraps, int numberOfTeleporter, int numberOfPush, int numberOfFake, int numberOfStun) {
        TrapManager.traps = maze.getGrid();
        trapMap = new EnumMap<Trap, Integer>(Trap.class);
        trapMap.put(Trap.PUSH, numberOfPush);
        trapMap.put(Trap.TELEPORTER, numberOfTeleporter);
        trapMap.put(Trap.FAKE, numberOfFake);
        trapMap.put(Trap.STUN, numberOfStun);
        generateTraps(maze);
        maze.setGrid(traps);
        return maze;
    }

    public void generateTraps(Maze maze) {
        for(Map.Entry<Trap, Integer> entry : trapMap.entrySet()){
            for (int i = 0; i < entry.getValue(); i++){
                Position position = getRandomCell(maze);
                setTrap(position.getX(),  position.getY(), entry.getKey());
            }
        }
    }

    public Position getRandomCell(Maze maze){
        Random random = new Random();
        int x, y;
        Position position;
        do {
            y = random.nextInt(traps[0].length);
            x = random.nextInt(traps.length);
            position = new Position(x, y);
        } while (getTrap(x,y)!=Trap.PATH || position.equals(maze.getExitPosition()) || position.equals(maze.getEntryPosition()));
        return position;
    }

    Enum<Trap> getTrap(int x, int y){
        return traps[x][y];
    }

    public void setTrap(int x, int y, Trap trap){
        traps[x][y] = trap;
    }




}
