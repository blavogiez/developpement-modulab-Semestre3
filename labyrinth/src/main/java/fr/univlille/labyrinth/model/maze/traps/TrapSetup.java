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
    static Trap[][] traps;
    static Map<TrapFactory, Integer> trapMap;
    

    public static Trap[][] generate(Maze maze, Map<TrapFactory, Integer> trapMap){
        TrapSetup.traps = new Trap[maze.getHeight()][maze.getWidth()];
        fillPath();
        generateTraps(maze);
        randomizeRandomTrap();

        return traps;
    }

    public static Trap[][] generate(Maze maze, String setup){
        trapMap = new EnumMap<>(TrapFactory.class);
        String[] separatedTraps = setup.split("_");
        for (int i = 0; i<separatedTraps.length;i++) {
            extractTrapAndValueFromConfiguration result = getExtractTrapAndValueFromConfiguration(separatedTraps[i]);
            verifyAndAddTrapIfExists(result);
        }
        return generate(maze,trapMap);
    }

    private static void verifyAndAddTrapIfExists(extractTrapAndValueFromConfiguration result) {
        TrapFactory trapType = TrapFactory.NONE;
        int numberTrap = 0;
        if (result.trap() != null && result.value() != null) {


            try {
                trapType = TrapFactory.compactedValueOf(result.trap());
                numberTrap = Integer.parseInt(result.value());
            } catch (Exception e) {
                trapType = TrapFactory.NONE;
            }
            trapMap.put(trapType, numberTrap);
        }
    }

    private static extractTrapAndValueFromConfiguration getExtractTrapAndValueFromConfiguration(String trapandvalue) {
        String trap = null;
        String value = null;
        for (int j = 0; j < trapandvalue.length(); j++) {
            char car = trapandvalue.charAt(j);
            if (car < '9' && car > '0') {
                trap = trapandvalue.substring(0, j);
                value = trapandvalue.substring(j);
                break;
            }
        }
        return new extractTrapAndValueFromConfiguration(trap, value);
    }

    private record extractTrapAndValueFromConfiguration(String trap, String value) {
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

    public static void generateTraps(Maze maze) {
        for(Map.Entry<TrapFactory, Integer> entry : trapMap.entrySet()){
            int value = entry.getValue();
            for (int i = 0; i < value; i++){
                Position position = getRandomCell(maze);
                setTrap(position.getY(),  position.getX(), entry.getKey().generateTrap());
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

    static Trap getTrap(int y, int x){
        return traps[y][x];
    }

    public static void setTrap(int y, int x, Trap trap){
        traps[y][x] = trap;
    }




}
