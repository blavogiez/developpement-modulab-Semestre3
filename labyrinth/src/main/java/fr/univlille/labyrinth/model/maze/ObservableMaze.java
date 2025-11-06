package fr.univlille.labyrinth.model.maze;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.maze.entities.EntityManager;
import fr.univlille.labyrinth.model.maze.trap.TrapManager;
import fr.univlille.labyrinth.model.maze.trap.TrapSetup;

/**
 * Implémentation de Maze en version dynamique, observable
 *
 * Afin de mieux respecter le S de S O L I D 
 * 
 *
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */

public class ObservableMaze extends Maze {
    protected EntityManager entityManager ;
     protected TrapManager trapManager ;
    // protected EventManager eventManager ;

    protected Position playerPosition;
    protected final List<Observer<ObservableMaze>> observers;


    /**
     * Cette méthode permet d'ajouter un observateur à Maze, afin qu'il puisse être alerté d'une modification
     *
     * @param observer un observateur de Maze.
     */
    public boolean add(Observer<ObservableMaze> observer){
        return observers.add(observer);
    }


    protected void notifyObserver(){
        for (Observer<ObservableMaze> observer : observers){
            observer.update(this);
        }
    }

    /**
     * Cette méthode permet de diriger le joueur vers une direction
     *
     * @param direction une direction parmi haut, bas, droite, gauche.
     */
    public boolean movePlayer(Direction direction){
        if ( !isWall(playerPosition.getY(),playerPosition.getX(),playerPosition.getY()+direction.getY(),playerPosition.getX()+direction.getX())) {
            playerPosition.addX(direction.getX());
            playerPosition.addY(direction.getY());
            notifyObserver();
            //notifyEntities();
            return true;
        }
        return false;
    }


    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit) {
        super(width, height, distanceBetweenEntryAndExit) ;
        this.observers = new ArrayList<>();
        this.playerPosition = new Position(entryPosition.getX(),entryPosition.getY());
    }
    
    /**
     * Cette méthode permet de générer un labyrinthe avec la longueur de chemin minimale par défaut (maximale). Cette méthode sera notamment appelée par le createMaze du mode libre
     *
     * @param width La largeur du labyrinthe
     * @param height La hauteur du labyrinthe
     * @param wallPercentage Le pourcentage de mur entre 0 et 0.5
     */

    /**
     * Cette méthode renvoie true si le joueur se situe à la sortie.
     */
    public boolean isPlayerPositionAtExit() {
        return playerPosition.equals(exitPosition);
    }

    /**
     * Cette méthode renvoie la position du joueur.
     */
    public Position getPlayerPosition() {
        return playerPosition;
    }

    //TrapHandler
    //EventHandler
    //MoveBehavoirHandler


    @Override
    public void trapEffect(Position position) {
        trapManager.trapEffect(position);
    }
}
