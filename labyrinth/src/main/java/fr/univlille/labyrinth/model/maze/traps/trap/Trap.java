package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.EntityManager;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;
import fr.univlille.labyrinth.model.maze.traps.TrapManager;

import java.util.Random;

public abstract class Trap {
    Position position;

    public abstract void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze);

    /** 
     * @param positon
     * @param trapManager
     */
    protected void revealTrap(Position positon, TrapManager trapManager) {
        trapManager.setTrap(positon.getY(),positon.getX(),TrapFactory.USED.generateTrap());
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
        TrapManager trapManager = maze.getTrapManager();
        EntityManager entityManager = maze.getEntityManager();
        int x, y;
        Position position;
        do {
            y = random.nextInt(trapManager.height());
            x = random.nextInt(trapManager.width());
            position = new Position(x, y);
        } while (!isFree(trapManager.getTrap(y,x),entityManager,position));
        return position;
    }

    /** 
     * @param trap
     * @param entitymanager
     * @param position
     * @return boolean
     */
    private static boolean isFree(Trap trap, EntityManager entitymanager, Position position){
        return trap instanceof NoneTrap && !entitymanager.isEntityOnCell(position);
    }

}
