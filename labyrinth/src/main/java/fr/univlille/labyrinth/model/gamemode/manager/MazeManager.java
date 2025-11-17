package fr.univlille.labyrinth.model.gamemode.manager;

import fr.univlille.labyrinth.model.gamemode.config.GameConfig;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.WallRemover;

public class MazeManager {
    private ObservableMaze currentMaze;

    /** 
     * Crée un labyrinthe selon le type d'algorithme (Spécifier la distance si c'est un parfait, sinon spécifier le pourcentage de murs)
     * @param config
     */
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
                config.getWallPercentage(),
                config.getEntitiesConfiguration(),
                    config.getAlgorithm(),
                    config.getTrapsConfiguration()
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
    public void randomWallRemoval(double pourcentage) {
        WallRemover.randomWallRemoval(pourcentage,currentMaze);
    }
}
