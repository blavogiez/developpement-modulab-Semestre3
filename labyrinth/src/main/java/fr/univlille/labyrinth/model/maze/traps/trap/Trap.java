package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.EntityManager;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;

import java.util.Random;

public abstract class Trap {
    Position position;

    public abstract void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze);

    /** 
     * @param positon
     * @param traps
     */
    protected void revealTrap(Position positon, Trap[][] traps) {
        traps[positon.getY()][positon.getX()] = TrapFactory.USED.generateTrap();
    }

    /** 
     * @return String
     */
    public String name(){
        return "NONE";
    }

    /** 
     * @param maze
     * @return Position
     */
    protected static Position getRandomCell(ObservableMaze maze){
        Random random = new Random();
        Trap[][] traps = maze.getTrapManager().getTraps();
        EntityManager entityManager = maze.getEntityManager();
        int x, y;
        Position position;
        do {
            y = random.nextInt(traps.length);
            x = random.nextInt(traps[y].length);
            position = new Position(x, y);
        } while (!isFree(traps,entityManager,position));
        return position;
    }

    /** 
     * @param traps
     * @param entitymanager
     * @param position
     * @return boolean
     */
    private static boolean isFree(Trap[][] traps, EntityManager entitymanager, Position position){
        return traps[position.getY()][position.getX()] instanceof NoneTrap && !entitymanager.isEntityOnCell(position);
    }

}
