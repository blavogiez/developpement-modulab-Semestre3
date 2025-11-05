package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.algorithm.Cell;
import fr.univlille.labyrinth.model.algorithm.PerfectAlgorithm;

import static fr.univlille.labyrinth.model.algorithm.PerfectAlgorithm.adjacent;
import static fr.univlille.labyrinth.model.algorithm.PerfectAlgorithm.positionCorrecte;

/**
 * Maze est une classe abstraite qui permet de représenter un labyrinthe.
 * La position du joueur est gérée dans la classe PlayerMaze (Qui sera la version observable), qui hérite de cette classe.
 * Afin de mieux respecter le S de S O L I D et de mieux retrouver les comportements exclusivements relatifs aux murs
 * 
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public abstract class Maze {
    protected final int width;
    protected final int height;
    protected final Cell[][] grid;
    protected Position entryPosition;
    protected Position exitPosition;
    protected final int distanceBetweenEntryAndExit;
    protected final boolean[][] murVerticaux;
    protected final boolean[][] murHorizontaux;

    public Maze(int width, int height, Cell[][] mazeCells, int distanceBetweenEntryAndExit) {
        this.width = width;
        this.height = height;
        this.grid = mazeCells;
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit ;
        this.murVerticaux = new boolean[width - 1][height];
        this.murHorizontaux = new boolean[height - 1][width];
        PerfectAlgorithm.generateMaze(this);
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
    
    /*
     * Cette méthode renvoie la distance entre l'entrée et la sortie du labyrinthe
     */
    public int getDistanceBetweenEntryAndExit() {
        return distanceBetweenEntryAndExit;
    }

    /**
     * Cette méthode renvoie le labyrinthe sous un tableau de booleans.
     */
    public Cell[][] getGrid() {
        return grid;
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

    public void setEntry(Position entryPosition) {
        this.entryPosition=entryPosition;
    }

    public void setExit(Position exitPosition) {
        this.exitPosition=exitPosition;
    }

}
