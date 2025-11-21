package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

public class NoneTrap extends Trap {
    /** 
     * @param playerID
     * @param position
     * @param oldPosition
     * @param maze
     */
    @Override
    public void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze) {
        /* Its the trap suggesting that there is not trap so that can't be used ! */
    }

    /** 
     * @return String
     */
    @Override
    public String name() {
        return "PATH";
    }
}
