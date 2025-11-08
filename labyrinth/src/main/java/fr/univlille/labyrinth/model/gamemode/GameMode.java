package fr.univlille.labyrinth.model.gamemode;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.VictoryHandler;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

public abstract class GameMode {

    private MazeManager mazeManager;
    private VictoryHandler victoryHandler;
    private List<Observer<GameMode>> victoryObservers = new ArrayList<>();

    public GameMode(MazeManager mazeManager, VictoryHandler victoryHandler) {
        this.mazeManager = mazeManager;
        this.victoryHandler = victoryHandler;
    }

    public void movePlayerPosition(Direction direction) {
        if (!mazeManager.hasMaze()) return;

        ObservableMaze maze = mazeManager.getCurrentMaze();
        if (maze.getPlayerPosition() == null) return;

        Position playerPosition = maze.getPlayerPosition();
        if (maze.movePlayer(direction)) {
            if (isPlayerAtEnd()) {
                handleVictory();
            } else {
                maze.trapEffect(playerPosition);
            }
        }
    }

    public boolean isPlayerAtEnd() {
        if (!mazeManager.hasMaze()) return false;
        ObservableMaze maze = mazeManager.getCurrentMaze();
        return maze.getPlayerPosition().equals(maze.getExitPosition());
    }

    public ObservableMaze getCurrentMaze() {
        return mazeManager.getCurrentMaze();
    }

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

    protected MazeManager getMazeManager() {
        return mazeManager;
    }

    protected VictoryHandler getVictoryHandler() {
        return victoryHandler;
    }

    public static boolean areDimensionsCorrect(int width, int height) {
        return width >= 1 && height >= 1 ;
    }
}
