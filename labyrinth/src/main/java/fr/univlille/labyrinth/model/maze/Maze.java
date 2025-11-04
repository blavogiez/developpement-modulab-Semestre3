package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.algorithm.Cell;

import java.util.ArrayList;
import java.util.List;

import static fr.univlille.labyrinth.model.algorithm.PerfectAlgorithm.adjacent;
import static fr.univlille.labyrinth.model.algorithm.PerfectAlgorithm.positionCorrecte;

/**
 * Maze est une classe qui permet récupérer un labyrinthe, et gérer les intéractions des joueurs.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Maze {

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

    /**
     *
     * @param ligne
     * @param colonne
     * @param ligne1
     * @param colonne1
     * @return true s'il y a un mur entre la Positionule située entre la Positionule à la
     *         position (ligne,colonne) et la Positionule à la position
     *         (ligne1,colonne1), false sinon
     */

    public boolean isWall(int ligne, int colonne, int ligne1, int colonne1) {

        if (!adjacent(ligne, colonne, ligne1, colonne1))
            throw new RuntimeException();

        if (!positionCorrecte(ligne, colonne) || !positionCorrecte(ligne1, colonne1)) {
            return true;
        }

        if (colonne == colonne1) {
            return murHorizontaux[Math.min(ligne,ligne1)][colonne];
        }
        if (ligne == ligne1) {
            return murVerticaux[Math.min(colonne,colonne1)][ligne];
        }
        return true;
    }


    private final int width;
    private final int height;
    private final Cell[][] grid;
    private final Position playerPosition;
    private final Position entryPosition;
    private final Position exitPosition;
    private final boolean[][] murVerticaux;
    private final boolean[][] murHorizontaux;

    public Maze(int width, int height, boolean[][] murVerticaux, boolean[][] murHorizontaux, Cell[][] mazeCells) {
        this.observers=new ArrayList<>();
        this.width = width;
        this.height = height;
        this.grid = mazeCells;
        this.murVerticaux = murVerticaux;
        this.murHorizontaux = murHorizontaux;
        this.entryPosition = new Position(0, 0);
        this.playerPosition = new Position(0, 0);
        this.exitPosition = new Position(1, 1);

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
