package fr.univlille.labyrinth.model.gamemode.manager;

import fr.univlille.labyrinth.model.gamemode.config.GameConfig;
import fr.univlille.labyrinth.model.maze.ObservableMaze;

public class MazeManager {
    private ObservableMaze currentMaze;

    public void createMaze(GameConfig config) {
        if (config.isPerfectAlgorithm()) {
            this.currentMaze = new ObservableMaze(
                config.getWidth(),
                config.getHeight(),
                config.getDistanceBetweenEntryAndExit(),
                config.getEntitiesConfiguration(),
                    config.getAlgorithm(),
                    config.getTrapsConfiguration()
            );
        } else {
            this.currentMaze = new ObservableMaze(
                config.getWidth(),
                config.getHeight(),
                1000,
                config.getEntitiesConfiguration(),
                    config.getAlgorithm(),
                    config.getTrapsConfiguration()
            );
        }
    }

    public ObservableMaze getCurrentMaze() {
        return currentMaze;
    }

    public void setCurrentMaze(ObservableMaze maze) {
        this.currentMaze = maze;
    }

    public boolean hasMaze() {
        return currentMaze != null;
    }
}
