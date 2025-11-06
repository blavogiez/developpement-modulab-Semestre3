package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.algorithm.Trap;

import java.util.ArrayList;
import java.util.List;
/**
 * Implémentation de Maze avec la position du joueur
 *
 * Afin de mieux respecter le S de S O L I D 
 * 
 *
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class PlayerMaze extends Maze {
    protected Position playerPosition;
    protected final List<Observer<PlayerMaze>> observers;


    /**
     * Cette méthode permet d'ajouter un observateur à Maze, afin qu'il puisse être alerté d'une modification
     *
     * @param observer un observateur de Maze.
     */
    public boolean add(Observer<PlayerMaze> observer){
        return observers.add(observer);
    }


    protected void notifyObserver(){
        for (Observer<PlayerMaze> observer : observers){
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
            return true;
        }
        return false;
    }


    public PlayerMaze(int width, int height, int distanceBetweenEntryAndExit) {
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



}
