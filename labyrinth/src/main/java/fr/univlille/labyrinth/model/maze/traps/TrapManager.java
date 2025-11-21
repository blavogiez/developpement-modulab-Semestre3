package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

public class TrapManager {
    private final ObservableMaze maze;
    private final Trap[][] trap;

    public TrapManager(ObservableMaze maze, String trapsConfiguration){
        this.maze=maze;
        this.trap = TrapSetup.getInstance().generate(maze,trapsConfiguration);
    }


    /** 
     * @param playerID
     * @param oldPosition
     */
    public void trapEffect(int playerID, Position oldPosition) {
        PlayerEntity player = maze.getEntityManager().getPlayerEntityByID(playerID);
        if (player == null) return;
        Position newPosition = player.getPosition();
        this.trap[newPosition.getY()][newPosition.getX()].onUse(playerID, newPosition, oldPosition, maze);
    }

    /** 
     * @return Trap[][]
     */
    public Trap getTrap(int y, int x) {
        return trap[y][x];
    }

    public void setTrap(int y, int x, Trap trap){
        this.trap[y][x]=trap;
    }
    
    public int height(){
        return this.trap.length;
    }

    public int width(){
        return this.trap[0].length;
    }
}
