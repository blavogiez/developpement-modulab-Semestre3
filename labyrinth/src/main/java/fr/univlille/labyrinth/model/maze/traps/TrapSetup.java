package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.traps.trap.NoneTrap;
import fr.univlille.labyrinth.model.maze.traps.trap.RandomTrap;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class TrapSetup {
    private static TrapSetup instance;

    /** 
     * @return TrapSetup
     */
    public static TrapSetup getInstance() {
        if (instance==null) instance=new TrapSetup();
        return instance;
    }

    Trap[][] traps;
    Map<TrapFactory, Integer> trapMap;

    /** 
     * @param maze
     * @return Trap[][]
     */
    public Trap[][] generate(ObservableMaze maze){
        traps = new Trap[maze.getHeight()][maze.getWidth()];
        fillPath();
        generateTraps(maze);
        randomizeRandomTrap();

        return traps;
    }

    /** 
     * @param maze
     * @param setup
     * @return Trap[][]
     */
    public Trap[][] generate(ObservableMaze maze, String setup){

        trapMap = new EnumMap<>(TrapFactory.class);
        if (setup!=null) {
            String[] separatedTraps = setup.split("_");
            for (int i = 0; i < separatedTraps.length; i++) {
                ExtractTrapAndValueFromConfiguration result = getExtractTrapAndValueFromConfiguration(separatedTraps[i]);
                verifyAndAddTrapIfExists(result);
            }
        }
            return generate(maze);
        }

    /** 
     * @param result
     */
    private void verifyAndAddTrapIfExists(ExtractTrapAndValueFromConfiguration result) {
        TrapFactory trapType;
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

    /** 
     * @param trapandvalue
     * @return extractTrapAndValueFromConfiguration
     */
    private ExtractTrapAndValueFromConfiguration getExtractTrapAndValueFromConfiguration(String trapandvalue) {
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
        return new ExtractTrapAndValueFromConfiguration(trap, value);
    }

    private record ExtractTrapAndValueFromConfiguration(String trap, String value) {
    }

    private static final Random RANDOM = new Random();
    private void randomizeRandomTrap() {

        for (int x = 0; x < traps.length; x++) {
            for (int y = 0; y < traps[x].length; y++) {
                if (traps[x][y] instanceof RandomTrap) {
                    traps[x][y] = TrapFactory.values()[RANDOM.nextInt(TrapFactory.TELEPORTER_TRAP.ordinal(), TrapFactory.values().length)].generateTrap();
                }
            }
        }
    }

    /** 
     * Génère les pièges avec une protection contre une boucle infinie (par exemple : 200 000 pièges voulus)
     * @param maze
     */
    public void generateTraps(ObservableMaze maze) {
        for(Map.Entry<TrapFactory, Integer> entry : trapMap.entrySet()){
            int value = entry.getValue();
            int i = 0;
            int count = 0;
            final int MAX = maze.getHeight()*maze.getWidth();
            while (count<MAX && i<value){
                try {
                    Position position = getRandomCell(maze);
                    setTrap(position.getY(),  position.getX(), entry.getKey().generateTrap());
                    i++;
                    count++;
                } catch (IllegalStateException e) {
                    break;
                }
            }
        }
    }



    /** 
     * @param maze
     * @return Position
     */
    public Position getRandomCell(Maze maze){

        int x;
        int y;
        Position position;
        int attempts = 0;
        final int MAX_ATTEMPTS = traps.length * traps[0].length * 10;

        do {
            y = RANDOM.nextInt(traps.length);
            x = RANDOM.nextInt(traps[y].length);
            position = new Position(x, y);
            attempts++;

            if (attempts > MAX_ATTEMPTS) {
                throw new IllegalStateException("Cannot find available cell for trap placement");
            }
        } while (!(getTrap(y,x) instanceof NoneTrap) ||
                position.equals(maze.getExitPosition()) ||
                position.equals(maze.getEntryPosition()));
        return position;
    }

    public void fillPath(){
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
    Trap getTrap(int y, int x){
        return traps[y][x];
    }

    /** 
     * @param y
     * @param x
     * @param trap
     */
    public void setTrap(int y, int x, Trap trap){
        traps[y][x] = trap;
    }




}
