package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

/**
 * Entité dans le labyrinthe qui réagit à chaque mouvement du joueur
 * Il peut s'agir du joueur, d'un monstre..
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public abstract class Entity {
    protected Position position ;
    protected MoveBehavior moveBehavior ;

    /**
     * Constructeur de l'entité.
     *
     * @param position la position initiale de l'entité
     * @param moveBehavior le comportement de déplacement de l'entité
     */
    public Entity(Position position, MoveBehavior moveBehavior) {
        this.position = position;
        this.moveBehavior = moveBehavior;
    }

    /**
     * Obtient le type de l'entité.
     *
     * @return le type de l'entité
     */
    public abstract EntityType getEntityType();

    /**
     * Retourne la position de l'entité.
     *
     * @return la position actuelle de l'entité
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Retourne le comportement de déplacement de l'entité.
     *
     * @return le MoveBehavior de l'entité
     */
    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    /**
     * Définit la position de l'entité.
     *
     * @param position la nouvelle position de l'entité
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Fait se déplacer l'entité dans la direction spécifiée.
     *
     * @param maze le labyrinthe dans lequel se déplacer
     * @param direction la direction du déplacement
     * @return true si le déplacement s'est bien effectué, false sinon
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
