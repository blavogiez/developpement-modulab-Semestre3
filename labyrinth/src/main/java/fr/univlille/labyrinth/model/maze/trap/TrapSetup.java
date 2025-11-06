package fr.univlille.labyrinth.model.maze.trap;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class TrapSetup {
    static private Trap[][] traps;
    static private Map<Trap, Integer> trapMap;

    public static Trap[][] generate(Maze maze, int numberOfRandomTraps, int numberOfTeleporter, int numberOfPush, int numberOfFake, int numberOfStun) {
        TrapSetup.traps = new Trap[maze.getHeight()][maze.getWidth()];
        trapMap = new EnumMap<>(Trap.class);
        trapMap.put(Trap.RANDOM,numberOfRandomTraps);
        trapMap.put(Trap.PUSH, numberOfPush);
        trapMap.put(Trap.TELEPORTER, numberOfTeleporter);
        trapMap.put(Trap.FAKE, numberOfFake);
        trapMap.put(Trap.STUN, numberOfStun);
        fillPath();
        generateTraps(maze);
        randomizeRandomTrap();


        return traps;
    }

    public static Trap[][] generate(Maze maze, int numberOfRandomTraps){
        return generate(maze,numberOfRandomTraps,0,0,0,0);
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
            int value = entry.getValue();
            for (int i = 0; i < value; i++){
                Position position = getRandomCell(maze);
                setTrap(position.getX(),  position.getY(), entry.getKey());
                System.out.println("Trap mis en position "+position.toString());
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
        } while (getTrap(y,x)!=Trap.PATH && !position.equals(maze.getExitPosition()) && !position.equals(maze.getEntryPosition()));
        return position;
    }

    public static void fillPath(){
        for (int i = 0; i<traps.length;i++){
            for (int j = 0; j<traps[i].length;j++){
                if (traps[i][j]==null){
                    traps[i][j]=Trap.PATH;
                }
            }
        }
    }

    static Enum<Trap> getTrap(int y, int x){
        return traps[y][x];
    }

    public static void setTrap(int y, int x, Trap trap){
        traps[y][x] = trap;
    }




}
