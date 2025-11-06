package fr.univlille.labyrinth.model.maze.entities.movebehaviors;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;

public class PlayerMoveBehavior implements MoveBehavior {
    public void move(Entity entity, Maze maze) {
//        Position playerPosition = entity.getPosition();
//        Position newPos = new Position(pos.getY(), pos.getX());
//
//        maze.notifyObserver();
        
    }

    /**
     * Cette méthode permet de diriger le joueur vers une direction
     *
     * @param direction une direction parmi haut, bas, droite, gauche.
     */
    public void movePlayer(Direction direction){

    }
}
