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
    protected double wallPercentage;
    protected boolean[][] murVerticaux;
    protected boolean[][] murHorizontaux;

    /**
     * Crée un labyrinthe avec tous les paramètres.
     *
     * @param width largeur du labyrinthe
     * @param height hauteur du labyrinthe
     * @param wallPercentage pourcentage de murs (0.0 à 1.0)
     * @param distanceBetweenEntryAndExit distance souhaitée entre l'entrée et la sortie
     * @param algo algorithme utilisé pour générer le labyrinthe
     */
    public Maze(int width, int height, double wallPercentage, int distanceBetweenEntryAndExit, MazeAlgorithm algo){
        this.width = width;
        this.height = height;
        this.wallPercentage = wallPercentage;
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit ;
        this.murHorizontaux = new boolean[height - 1][width];
        this.murVerticaux = new boolean[width - 1][height];
        algo.generateMaze(this);
        algo.generateExitAndPlayer(this);
    }

    /**
     * Crée un labyrinthe avec distance maximale par défaut et algorithme donné.
     *
     * @param width largeur du labyrinthe
     * @param height hauteur du labyrinthe
     * @param distanceBetweenEntryAndExit distance souhaitée entre l'entrée et la sortie
     * @param algo algorithme utilisé pour générer le labyrinthe
     */
    public Maze(int width, int height, int distanceBetweenEntryAndExit, MazeAlgorithm algo){
        this(width, height, 1.0, distanceBetweenEntryAndExit, algo);
    }

    /**
     * Crée un labyrinthe avec pourcentage de murs donné et distance maximale.
     *
     * @param width largeur du labyrinthe
     * @param height hauteur du labyrinthe
     * @param wallPercentage pourcentage de murs (0.0 à 1.0)
     * @param algo algorithme utilisé pour générer le labyrinthe
     */
    public Maze(int width, int height, double wallPercentage, MazeAlgorithm algo) {
        this(width, height, wallPercentage, Integer.MAX_VALUE, algo);
    }

    /**
     * Crée un labyrinthe avec seulement le pourcentage de murs.
     *
     * @param width largeur du labyrinthe
     * @param height hauteur du labyrinthe
     * @param wallPercentage pourcentage de murs (0.0 à 1.0)
     */
    public Maze(int width, int height, double wallPercentage) {
        this(width, height, wallPercentage, MazeAlgorithmFactory.RANDOM.getAlgorithm());
    }

    /**
     * Crée un labyrinthe avec distance maximale et algorithme parfait.
     *
     * @param width largeur du labyrinthe
     * @param height hauteur du labyrinthe
     * @param distanceBetweenEntryAndExit distance souhaitée entre l'entrée et la sortie
     */
    public Maze(int width, int height, int distanceBetweenEntryAndExit) {
        this(width, height, distanceBetweenEntryAndExit, MazeAlgorithmFactory.PERFECT.getAlgorithm());
    }

    /**
     * Crée un labyrinthe par défaut avec distance maximale et algorithme parfait.
     *
     * @param width largeur du labyrinthe
     * @param height hauteur du labyrinthe
     */
    public Maze(int width, int height) {
        this(width, height, Integer.MAX_VALUE);
    }
    

    /**
     * Retourne la largeur du labyrinthe en nombre de cellules.
     *
     * @return la largeur du labyrinthe
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur du labyrinthe en nombre de cellules.
     *
     * @return la hauteur du labyrinthe
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retourne le pourcentage de murs présents dans le labyrinthe.
     *
     * @return le pourcentage de murs (valeur entre 0.0 et 1.0)
     */
    public double getWallPercentage() {
        return this.wallPercentage;
    }

    
    /**
     * Retourne la distance entre l'entrée et la sortie du labyrinthe.
     *
     * @return la distance entre l'entrée et la sortie
     */
    public int getDistanceBetweenEntryAndExit() {
        return distanceBetweenEntryAndExit;
    }

    /**
     * Définit la distance entre l'entrée et la sortie du labyrinthe.
     *
     * @param distanceBetweenEntryAndExit la distance à définir
     */
    public void setDistanceBetweenEntryAndExit(int distanceBetweenEntryAndExit) {
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit;
    }

    /**
     * Retourne la position de l'entrée du labyrinthe.
     *
     * @return la position de l'entrée
     */
    public Position getEntryPosition() {
        return entryPosition;
    }

    /**
     * Retourne la position de la sortie du labyrinthe.
     *
     * @return la position de la sortie
     */
    public Position getExitPosition() {
        return exitPosition;
    }

    /**
     * Retourne les murs horizontaux du labyrinthe.
     *
     * @return un tableau 2D de booléens représentant les murs horizontaux
     */
    public boolean[][] getMurHorizontaux() {
        return murHorizontaux;
    }

    /**
     * Retourne les murs verticaux du labyrinthe.
     *
     * @return un tableau 2D de booléens représentant les murs verticaux
     */
    public boolean[][] getMurVerticaux() {
        return murVerticaux;
    }

    /**
     * Définit la position de l'entrée du labyrinthe.
     *
     * @param entryPosition la position de l'entrée
     */
    public void setEntry(Position entryPosition) {
        this.entryPosition = entryPosition;
    }

    /**
     * Définit la position de la sortie du labyrinthe.
     *
     * @param exitPosition la position de la sortie
     */
    public void setExit(Position exitPosition) {
        this.exitPosition = exitPosition;
    }

    /**
     * Applique l'effet d'un piège à un joueur.
     *
     * @param playerID l'identifiant du joueur affecté
     * @param oldPosition la position précédente du joueur
     */
    public void trapEffect(int playerID, Position oldPosition) {}

    /**
     * Définit les murs horizontaux du labyrinthe.
     *
     * @param murHorizontaux un tableau 2D de booléens représentant les murs horizontaux
     */
    public void setMurHorizontaux(boolean[][] murHorizontaux) {
        this.murHorizontaux = murHorizontaux;
    }

    /**
     * Définit les murs verticaux du labyrinthe.
     *
     * @param murVerticaux un tableau 2D de booléens représentant les murs verticaux
     */
    public void setMurVerticaux(boolean[][] murVerticaux) {
        this.murVerticaux = murVerticaux;
    }
}
