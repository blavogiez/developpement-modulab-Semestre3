package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;

import java.util.ArrayList;
import java.util.List;

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
        playerPosition.addX(direction.x);
        playerPosition.addY(direction.y);
        notifyObserver();
    }

    /**
     * Cette méthode renvoie si les coordonnées demandées sont un mur ou non
     *
     * @param x coordonnées des abscisses.
     * @param y coordonnées des ordonnées.
     */
    public boolean isWall(int x, int y){
        return !grid[x][y];
    }


    private final int width;
    private final int height;
    private final boolean[][] grid;
    private final Position playerPosition;
    private final Position entryPosition;
    private final Position exitPosition;

    /**
     * Cette méthode permet de générer un labyrinthe
     *
     * @param width La largeur du labyrinthe
     * @param height La hauteur du labyrinthe
     * @param wallPercentage Le pourcentage de mur entre 0 et 0.5
     */
    public Maze(int width, int height, double wallPercentage) {
        this.observers=new ArrayList<>();
        this.grid = MazeAlgorithmFactory.STANDARDRANDOM.getAlgorithm().createMaze(width, height, wallPercentage);
        this.width = this.grid.length;
        this.height = this.grid[0].length;
        this.playerPosition = MazeAlgorithmFactory.STANDARDRANDOM.getAlgorithm().getStart();
        this.entryPosition = MazeAlgorithmFactory.STANDARDRANDOM.getAlgorithm().getStart(); //inutile??
        this.exitPosition = MazeAlgorithmFactory.STANDARDRANDOM.getAlgorithm().getEnd();
    }

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
    public boolean[][] getGrid() {
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


}
