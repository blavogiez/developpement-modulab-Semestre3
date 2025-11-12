package fr.univlille.labyrinth.model.gamemode.manager;

import fr.univlille.labyrinth.model.gamemode.config.GameConfig;
import fr.univlille.labyrinth.model.maze.ObservableMaze;

public class MazeManager {
    private ObservableMaze currentMaze;

    /** 
     * @param config
     */
    public void createMaze(GameConfig config) {
        if (config.isPerfectAlgorithm()) {
            this.currentMaze = new ObservableMaze(
                config.getWidth(),
                config.getHeight(),
                config.getDistanceBetweenEntryAndExit(),
                config.getEntitiesConfiguration(),
                    config.getAlgorithm()
            );
        } else {
            this.currentMaze = new ObservableMaze(
                config.getWidth(),
                config.getHeight(),
                1000,
                config.getEntitiesConfiguration(),
                    config.getAlgorithm()
            );
        }
    }

    /** 
     * @return ObservableMaze
     */
    public ObservableMaze getCurrentMaze() {
        return currentMaze;
    }

    /** 
     * @param maze
     */
    public void setCurrentMaze(ObservableMaze maze) {
        this.currentMaze = maze;
    }

    /** 
     * @return boolean
     */
    public boolean hasMaze() {
        return currentMaze != null;
    }
}
