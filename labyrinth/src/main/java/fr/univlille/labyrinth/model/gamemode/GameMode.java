package fr.univlille.labyrinth.model.gamemode;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.VictoryHandler;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

/**
 * GameMode est la classe abstraite qui gère le mode de jeu choisi par le joueur. Elle sera l'intermédiaire entre Labyrinthe et Joueur.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public abstract class GameMode {

    private MazeManager mazeManager;
    private VictoryHandler victoryHandler;
    private List<Observer<GameMode>> victoryObservers = new ArrayList<>();

    public GameMode(MazeManager mazeManager, VictoryHandler victoryHandler) {
        this.mazeManager = mazeManager;
        this.victoryHandler = victoryHandler;
    }

    /**
     * Cette méthode vérifie si le joueur peut se déplacer à l'endroit demandé, et envoie au labyrinthe de le déplacer si c'est le cas.
     *
     * @param direction la direction du déplacement.
     */
    public void movePlayerPosition(int playerID, Direction direction) {
        if (!mazeManager.hasMaze()) return;

        ObservableMaze maze = mazeManager.getCurrentMaze();
        if (maze.getPlayerPosition() == null) return;

        Position playerPosition = maze.getPlayerPosition().copy();
        if (maze.movePlayer(playerID, direction)) {
            if (maze.isPlayerAtExit()) {
                handleVictory();
            } else if(maze.getEntityManager().checkMonsterOnPlayer()) {
                handleLoose();
            } else {
                maze.trapEffect(playerPosition);
            }
        }
    }

    /**
     * @return Maze
     */
    public ObservableMaze getCurrentMaze() {
        return mazeManager.getCurrentMaze();
    }

    /**
     * @param maze set Maze
     */
    public void setCurrentMaze(ObservableMaze maze) {
        mazeManager.setCurrentMaze(maze);
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
        victoryHandler.handleVictory();
        notifyVictory();
    }

    protected void handleLoose() {
        victoryHandler.handleLoose();
        notifyVictory();
    }

    protected MazeManager getMazeManager() {
        return mazeManager;
    }

    protected VictoryHandler getVictoryHandler() {
        return victoryHandler;
    }

    /* Les dimensions demandées sont-elles possibles ?
    à déplacer (CF fichier SOLID.md)
     * @return boolean
     */
    public static boolean areDimensionsCorrect(int width, int height) {
        return width >= 1 && height >= 1 ;
    }
}
