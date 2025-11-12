package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.traps.trap.NoneTrap;
import fr.univlille.labyrinth.model.maze.traps.trap.RandomTrap;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class TrapSetup {
    static private Trap[][] traps;
    static private Map<TrapFactory, Integer> trapMap;

    /** 
     * @param maze
     * @param numberOfRandomTraps
     * @param numberOfTeleporter
     * @param numberOfPush
     * @param numberOfFake
     * @param numberOfStun
     * @return Trap[][]
     */
    public static Trap[][] generate(Maze maze, int numberOfRandomTraps, int numberOfTeleporter, int numberOfPush, int numberOfFake, int numberOfStun) {
        TrapSetup.traps = new Trap[maze.getHeight()][maze.getWidth()];
        trapMap = new EnumMap<>(TrapFactory.class);
        trapMap.put(TrapFactory.RANDOM_TRAP,numberOfRandomTraps);
        trapMap.put(TrapFactory.PUSH_TRAP, numberOfPush);
        trapMap.put(TrapFactory.TELEPORTER_TRAP, numberOfTeleporter);
        trapMap.put(TrapFactory.FAKE_EXIT_TRAP, numberOfFake);
        trapMap.put(TrapFactory.STUN_TRAP, numberOfStun);
        fillPath();
        generateTraps(maze);
        randomizeRandomTrap();


        return traps;
    }

    /** 
     * @param maze
     * @param numberOfRandomTraps
     * @return Trap[][]
     */
    public static Trap[][] generate(Maze maze, int numberOfRandomTraps){
        return generate(maze,numberOfRandomTraps,0,0,0,0);
    }

    private static void randomizeRandomTrap() {
        Random random = new Random();
        for (int x = 0; x < traps.length; x++) {
            for (int y = 0; y < traps[x].length; y++) {
                if (traps[x][y] instanceof RandomTrap) {
                    traps[x][y] = TrapFactory.values()[random.nextInt(TrapFactory.TELEPORTER_TRAP.ordinal(), TrapFactory.values().length)].generateTrap();
                }
            }
        }
    }

    /** 
     * @param maze
     */
    public static void generateTraps(Maze maze) {
        for(Map.Entry<TrapFactory, Integer> entry : trapMap.entrySet()){
            int value = entry.getValue();
            for (int i = 0; i < value; i++){
                Position position = getRandomCell(maze);
                setTrap(position.getY(),  position.getX(), entry.getKey().generateTrap());
            }
        }
    }

    /** 
     * @param maze
     * @return Position
     */
    public static Position getRandomCell(Maze maze){
        Random random = new Random();
        int x, y;
        Position position;
        do {
            y = random.nextInt(traps.length);
            x = random.nextInt(traps[y].length);
            position = new Position(x, y);
        } while (!(getTrap(y,x) instanceof NoneTrap) ||
                position.equals(maze.getExitPosition()) ||
                position.equals(maze.getEntryPosition()));
        return position;
    }

    public static void fillPath(){
        for (int i = 0; i< traps.length; i++){
            for (int j = 0; j< traps[i].length; j++){
                if (traps[i][j]==null){
                    traps[i][j]= TrapFactory.NONE.generateTrap();
                }
            }
        }
    }

    /** 
     * @param y
     * @param x
     * @return Trap
     */
    static Trap getTrap(int y, int x){
        return traps[y][x];
    }

    /** 
     * @param y
     * @param x
     * @param trap
     */
    public static void setTrap(int y, int x, Trap trap){
        traps[y][x] = trap;
    }




}
