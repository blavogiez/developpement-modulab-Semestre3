package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;

/**
 * Maze est une classe abstraite qui permet de représenter un labyrinthe.
 * La position du joueur est gérée dans la classe PlayerMaze (Qui sera la version observable), qui hérite de cette classe.
 * Afin de mieux respecter le S de S O L I D et de mieux retrouver les comportements exclusivements relatifs aux murs
 * <p>
 * Convention de coordonnées :
 * - (x, y) : position avec x = colonne (axe horizontal, largeur), y = ligne (axe vertical, hauteur)
 * - L'axe X est horizontal (de gauche à droite)
 * - L'axe Y est vertical (de haut en bas)
 * - murHorizontaux[y][x] représente les murs horizontaux entre (y, x) et (y, x+1)
 * - murVerticaux[y][x] représente les murs verticaux entre (y, x) et (y+1, x)
 * </p>
 * 
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Maze {
    protected int width;
    protected int height;
    protected Position entryPosition;
    protected Position exitPosition;
    protected int distanceBetweenEntryAndExit;
    protected boolean[][] murVerticaux;
    protected boolean[][] murHorizontaux;


    public Maze(int width, int height, int distanceBetweenEntryAndExit, MazeAlgorithm algo){
        this.width = width;
        this.height = height;
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit ;
        this.murHorizontaux = new boolean[height - 1][width];
        this.murVerticaux = new boolean[width - 1][height];
        algo.generateMaze(this);
        algo.generateExitAndPlayer(this);
    }

    // Surcharge avec algorithme par défaut
    public Maze(int width, int height, int distanceBetweenEntryAndExit) {
        this(width, height, distanceBetweenEntryAndExit, MazeAlgorithmFactory.PERFECT.getAlgorithm());
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

    public void setDistanceBetweenEntryAndExit(int distanceBetweenEntryAndExit) {
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit;
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

    public void trapEffect(int playerID, Position oldPosition) {}




}
