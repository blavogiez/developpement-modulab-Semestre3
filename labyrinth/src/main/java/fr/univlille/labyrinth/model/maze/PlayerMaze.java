package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.algorithm.Cell;

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
    private final Position playerPosition;
    private final List<Observer<Maze>> observers;

    /**
     * Cette méthode permet d'ajouter un observateur à Maze, afin qu'il puisse être alerté d'une modification
     *
     * @param observer un observateur de Maze.
     */
    public boolean add(Observer<Maze> observer){return observers.add(observer);}


    private void notifyObserver(){
        for (Observer<Maze> observer : observers){
            observer.update(this);
        }
    }

    /**
     * Cette méthode permet de diriger le joueur vers une direction
     *
     * @param direction une direction parmi haut, bas, droite, gauche.
     */
    public void movePlayer(Direction direction){
        playerPosition.addX(direction.getX());
        playerPosition.addY(direction.getY());
        notifyObserver();
    }


    public PlayerMaze(int width, int height, boolean[][] murVerticaux, boolean[][] murHorizontaux, Cell[][] mazeCells, Position entryPosition, Position exitPosition) {
        super(width, height, murVerticaux, murHorizontaux, mazeCells, entryPosition, exitPosition) ;
        this.observers = new ArrayList<>();
        this.playerPosition = entryPosition;
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
     * Cette méthode renvoie la largeur du labyrinthe.
     */
    public int getWidth()    {
        return width;
    }

    /**
     * Cette méthode renvoie la hauteur du labyrinthe.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Cette méthode renvoie le labyrinthe sous un tableau de booleans.
     */
    public Cell[][] getGrid() {
        return grid;
    }

    /**
     * Cette méthode renvoie la position du joueur.
     */
    public Position getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Cette méthode renvoie la position de l'entrée.
     */
    public Position getEntryPosition() {
        return entryPosition;
    }

    /**
     * Cette méthode renvoie la position de la sortie.
     */
    public Position getExitPosition() {
        return exitPosition;
    }

    public boolean[][] getMurHorizontaux() {
        return murHorizontaux;
    }

    public boolean[][] getMurVerticaux() {
        return murVerticaux;
    }

}
