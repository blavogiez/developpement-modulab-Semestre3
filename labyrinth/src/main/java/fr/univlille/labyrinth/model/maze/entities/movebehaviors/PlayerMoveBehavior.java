package fr.univlille.labyrinth.model.maze.entities.movebehaviors;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.MazeWallChecker;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;

public class PlayerMoveBehavior implements MoveBehavior {
    private int stuntDuration = 0;
    /**
     * Cette méthode permet de diriger l'entité joueur vers une direction
     *
     * @param direction une direction parmi haut, bas, droite, gauche.
     */
    public void move(Entity entity, Direction direction, ObservableMaze maze) {
        if (stuntDuration==0){
            Position position = entity.getPosition();
            if (!MazeWallChecker.isWall(maze,position.getY(),position.getX(),position.getY()+direction.getY(),position.getX()+direction.getX())) {
                position.addX(direction.getX());
                position.addY(direction.getY());
                moving=true;
            } else {
                moving=false;
            }
        } else {
            stuntDuration--;
            moving=false;
        }

    }



    private boolean moving = false;

    /** 
     * @return boolean
     */
    public boolean isMoving() {
        return moving;
    }


    public void setStuntDuration(int stuntDuration) {
        this.stuntDuration = stuntDuration;
    }
}
