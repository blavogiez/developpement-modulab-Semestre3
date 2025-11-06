package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.algorithm.Trap;

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
    protected Trap[][] grid;
    protected Position entryPosition;
    protected Position exitPosition;
    protected int distanceBetweenEntryAndExit;
    protected boolean[][] murVerticaux;
    protected boolean[][] murHorizontaux;


    // cell grid à gérer ?
    public Maze(int width, int height, int distanceBetweenEntryAndExit) {
        this.width = width;
        this.height = height;
        this.grid = new Trap[width][height];
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit ;
        this.murHorizontaux = new boolean[height - 1][width];
        this.murVerticaux = new boolean[width - 1][height];
        //eventuellement faire la gene ailleurs ?
        MazeAlgorithmFactory.PERFECT.getAlgorithm().generateMaze(this);
    }

    public void setGrid(Trap[][] grid) {
        this.grid = grid;
    }

    /**
     *
     * @param y1 Ordonnée de la cellule de départ
     * @param x1 Abscisse de la cellule de départ
     * @param y2 Ordonnée de la cellule d'arrivé
     * @param x2 Abscisse de la cellule d'arrivé
     * @return true s'il y a un mur entre la Positionule située entre la Positionule à la
     *         position (ligne,colonne) et la Positionule à la position
     *         (ligne1,colonne1), false sinon
     */

    public boolean isWall(int y1, int x1, int y2, int x2) {

        if (!adjacent(y1, x1, y2, x2))
            throw new RuntimeException();

        if (!positionCorrecte(y1, x1) || !positionCorrecte(y2, x2)) {
            return true;
        }

        if (x1 == x2) {
            return murHorizontaux[Math.min(y1, y2)][x1];
        }
        if (y1 == y2) {
            return murVerticaux[Math.min(x1, x2)][y1];
        }
        return true;
    }

    /*
     * La méthode permet de savoir si la position se situe dans le labyrinthe.
     */
    public boolean positionCorrecte(int y, int x) {
        return y >= 0 && y < height && x >= 0 && x < width;
    }

    public boolean positionCorrecte(Position position) {
        return position.getY() >= 0 && position.getY() < height && position.getX() >= 0 && position.getX() < width;
    }

    public boolean adjacent(int y1, int x1, int y2, int x2) {
        return (y1 == y2 && (x1 == x2 - 1 || x1 == x2 + 1))
                || (x1 == x2 && (y1 == y2 - 1 || y1 == y2 + 1));
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
    public Trap[][] getGrid() {
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

    public void trapEffect(Position position) {}


}
