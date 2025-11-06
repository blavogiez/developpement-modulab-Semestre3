package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;

public class PlayerEntity extends Entity {
    public PlayerEntity() {
        super(new Position(0, 0), new PlayerMoveBehavior());
    }

    public PlayerEntity(Position position) {
        super(position, new PlayerMoveBehavior());
    }

    /**
     * Cette méthode renvoie true si le joueur se situe à la sortie.
     */
    public boolean isPlayerPositionAtExit(Maze maze) {
        return this.position.equals(maze.getExitPosition());
    }

    @Override
    public boolean move(Maze maze, Direction direction) {
        if (!maze.isWall(position.getY(),position.getX(),position.getY()+direction.getY(),position.getX()+direction.getX())) {
            position.addX(direction.getX());
            position.addY(direction.getY());
            return true ;
        }
        return false ;
    }
}
