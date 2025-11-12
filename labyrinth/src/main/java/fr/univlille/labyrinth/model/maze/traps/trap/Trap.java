package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;

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

}
