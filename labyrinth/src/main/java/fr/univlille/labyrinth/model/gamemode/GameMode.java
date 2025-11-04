package fr.univlille.labyrinth.model.gamemode;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.algorithm.PerfectAlgorithm;
import fr.univlille.labyrinth.model.algorithmold.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

/**
 * GameMode est la classe abstraite qui gère le mode de jeu choisi par le joueur. Elle sera l'intermédiaire entre Labyrinthe et Joueur.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public abstract class GameMode {

    private Maze currentMaze;
    private List<Observer<GameMode>> victoryObservers = new ArrayList<>();

    /** 
     * @param width
     * @param height
     * @param wallPercentage
     */
    /**

    /**
     * Cette méthode permet de générer un labyrinthe, afin de le stocker en paramètre.
     *
     * @param width la largeur du labyrinthe.
     * @param height la hauteur du labyrinthe.
     * @param wallPercentage le taux de mur entre 0 et 0.5.
     */
    public void createMaze(MazeAlgorithmFactory algorithm, int width, int height, double wallPercentage) {
        int maxDistance = (height - 3) + (width - 3);
        this.currentMaze = PerfectAlgorithm.createMaze(width, height, maxDistance);
    }

    // Surcharge pour prendre en compte une distance entre l'entrée et la sortie (Le sujet ne mentionne que pour la progression)
    public void createMaze(MazeAlgorithmFactory algorithm, int width, int height, double wallPercentage, int distanceBetweenEntryAndExit) {
        this.currentMaze = PerfectAlgorithm.createMaze(width, height, distanceBetweenEntryAndExit);
    }

    /**
     * Cette méthode vérifie si le joueur peut se déplacer à l'endroit demander, et envoie au labyrinthe de le déplacer si c'est le cas.
     *
     * @param direction la largeur du labyrinthe.
     */
    public void movePlayerPosition(Direction direction) {
        if (currentMaze!=null && currentMaze.getPlayerPosition()!=null){
            Position playerPosition = currentMaze.getPlayerPosition();
            if ( !currentMaze.isWall(playerPosition.getX(),playerPosition.getY(),playerPosition.getX()+direction.getX(),playerPosition.getY()+direction.getY())){
                currentMaze.movePlayer(direction);
                if (isPlayerAtEnd()) {
                    handleVictory();
                }
            }
        }
    }

    /** 
     * @return boolean
     */
    public boolean isPlayerAtEnd() {
        return currentMaze != null && currentMaze.getPlayerPosition().equals(currentMaze.getExitPosition());
    }

    /** 
     * @param currentMaze set Maze
     */
    public void setCurrentMaze(Maze currentMaze) {
        this.currentMaze = currentMaze;
    }

    /**
     * @return Maze
     */
    public Maze getCurrentMaze() {
        return currentMaze;
    }

    public void addVictoryObserver(Observer<GameMode> observer) {
        victoryObservers.add(observer);
    }

    protected void notifyVictory() {
        for (Observer<GameMode> observer : victoryObservers) {
            observer.update(this);
        }
    }

    protected void handleVictory() {
        notifyVictory();
    }

    /* Les dimensions demandées sont-elles possibles ?
     * @return boolean
     */
    public static boolean areDimensionsCorrect(int width, int height) {
        return width >= 3 && height >= 4 || width >= 4 && height >= 3 ;
    }
}
