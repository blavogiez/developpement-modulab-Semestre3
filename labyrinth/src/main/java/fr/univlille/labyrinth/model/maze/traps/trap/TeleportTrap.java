package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Observable;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityManager;
import fr.univlille.labyrinth.model.maze.traps.TrapSetup;

import java.util.Random;

public class TeleportTrap extends Trap {
    /** 
     * @param playerID
     * @param position
     * @param oldPosition
     * @param maze
     */
    @Override
    public void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze) {
        maze.setPlayerPosition(playerID, getRandomCell(maze));
    }



    /** 
     * @return String
     */
    @Override
    public String name() {
        return "TRAP_TELEPORT";
    }
}
