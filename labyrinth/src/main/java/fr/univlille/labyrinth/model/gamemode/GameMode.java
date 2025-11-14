package fr.univlille.labyrinth.model.gamemode;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.VictoryObserver;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.VictoryHandler;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

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
    private List<VictoryObserver<GameMode>> victoryObservers = new ArrayList<>();

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
        PlayerEntity player = maze.getEntityManager().getPlayerEntityByID(playerID);
        if (player == null) return;

        Position oldPosition = player.getPosition().copy();
        if (maze.movePlayer(playerID, direction)) {
            if (maze.isPlayerAtExit()) {
                handleVictory();
            } else {
                maze.getEntityManager().checkMonsterOnPlayer();
                if (maze.getEntityManager().getPlayerEntities().isEmpty()) {
                    handleLoose();
                } else {
                    maze.trapEffect(playerID, oldPosition);
                }
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

    /** 
     * @param observer
     */
    public void addVictoryObserver(VictoryObserver<GameMode> observer) {
        victoryObservers.add(observer);
    }

    protected void notifyVictory() {
        for (VictoryObserver<GameMode> observer : victoryObservers) {
            observer.handleVictory();
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

    /** 
     * @return MazeManager
     */
    protected MazeManager getMazeManager() {
        return mazeManager;
    }

    /** 
     * @return VictoryHandler
     */
    protected VictoryHandler getVictoryHandler() {
        return victoryHandler;
    }

    /** 
     * @param width
     * @param height
     * @return boolean
     */
    /* Les dimensions demandées sont-elles possibles ?
    à déplacer (CF fichier SOLID.md)
     * @return boolean
     */
    public static boolean areDimensionsCorrect(int width, int height) {
        return width >= 1 && height >= 1 ;
    }
}
