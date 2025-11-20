package fr.univlille.labyrinth.model.gamemode;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.VictoryObserver;
import fr.univlille.labyrinth.model.gamemode.config.GameConfig;
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
    protected GameConfig config;
    private List<VictoryObserver<GameMode>> victoryObservers = new ArrayList<>();

    protected GameMode(MazeManager mazeManager, VictoryHandler victoryHandler, GameConfig config) {
        this.mazeManager = mazeManager;
        this.victoryHandler = victoryHandler;
        this.config = config;
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
            PlayerEntity winner = maze.getEntityManager().checkPlayerOnExit();
            if (winner != null) {
                handleVictory(winner);
            } else if (this.monsterBeatLastPlayer(maze) || this.trapBeatLastPlayer(maze, playerID, oldPosition) ){
                handleLoose();
            }
        }
    }

    public boolean monsterBeatLastPlayer(ObservableMaze maze){
        maze.getEntityManager().checkMonsterOnPlayer();
        return (!maze.getEntityManager().containsType(PlayerEntity.class));
    }

    public boolean trapBeatLastPlayer(ObservableMaze maze,int playerID,Position oldPosition){
        maze.trapEffect(playerID, oldPosition);
        return (!maze.getEntityManager().containsType(PlayerEntity.class));
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
     * @return GameConfig
     */
    public GameConfig getConfig() {
        return config;
    }

    /**
     * @param observer
     */
    public void addVictoryObserver(VictoryObserver<GameMode> observer) {
        victoryObservers.add(observer);
    }

    protected void notifyVictory() {
        for (VictoryObserver<GameMode> observer : victoryObservers) {
            observer.onVictory();
        }
    }

    protected void notifyDefeat() {
        for (VictoryObserver<GameMode> observer : victoryObservers) {
            observer.onDefeat(this);
        }
    }

    /** 
     * @param winner
     */
    protected void handleVictory(PlayerEntity winner) {
        victoryHandler.handleVictory(winner);
        notifyVictory();
    }

    protected void handleLoose() {
        victoryHandler.handleLoose();
        notifyDefeat();
    }

    public abstract PlayerEntity getWinner();

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
