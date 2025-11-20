package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.*;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

/*
 * Entité dans le labyrinthe qui réagit à chaque mouvement du joueur
 * Il peut s'agir du joueur, d'un monstre..
 */
public abstract class Entity {
    protected Position position ;
    protected MoveBehavior moveBehavior ;

    protected Entity(Position position, MoveBehavior moveBehavior) {
        this.position = position;
        this.moveBehavior = moveBehavior;
    }

    public abstract EntityType getEntityType();

    public abstract String getDefType();

    /** 
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    /** 
     * @return MoveBehavior
     */
    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    /** 
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /** 
     * @param maze
     * @param direction
     * @return boolean
     */
    /*
     * Retourne true si l'action s'est bien déroulée.
     */
    public boolean move(ObservableMaze maze, Direction direction) {
        if(moveBehavior!=null) {
            moveBehavior.move(this,direction,maze);
            return true ;
        } return false ;
    }
}
