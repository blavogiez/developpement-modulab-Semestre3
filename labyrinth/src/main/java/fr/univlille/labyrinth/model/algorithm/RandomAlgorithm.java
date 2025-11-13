package fr.univlille.labyrinth.model.algorithm;

import java.util.*;

import fr.univlille.labyrinth.model.maze.Maze;

/*
 * L'algorithme de génération de labyrinthes à pourcentage de murs vise à rendre imparfait un labyrinthe initialement parfait
 * Cette philosophie garantit qu'il n'y ait aucune cellule isolée, ou de cluster inaccessible.
 * 
 * De plus, par héritage, le code est grandement simplifié.
 */
public class RandomAlgorithm extends PerfectAlgorithm {

    /**
     * @param maze
     */
    @Override
    public void generateMaze(Maze maze) {
        super.generateMaze(maze);
        double wallPercentage = Math.max(0.0, Math.min(1.0, maze.getWallPercentage()));
        randomlyRemoveWalls(wallPercentage);
    }

    /**
     * Enlève (à false) les murs (true) par probabilité (1 - p)
     * @param wallPercentage Le pourcentage de murs à conserver (entre 0 et 1)
     */
    private void randomlyRemoveWalls(double wallPercentage) {
        Random random = new Random();

        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                if (horizontalsWalls[y][x] && random.nextDouble() < (1 - wallPercentage)) {
                    horizontalsWalls[y][x] = false;
                }
            }
        }

        for (int x = 0; x < width - 1; x++) {
            for (int y = 0; y < height; y++) {
                if (verticalsWalls[x][y] && random.nextDouble() < (1 - wallPercentage)) {
                    verticalsWalls[x][y] = false;
                }
            }
        }
    }

    private static RandomAlgorithm instance;

    /**
     * @return RandomAlgorithm
     */
    public static RandomAlgorithm getInstance(){
        if (instance == null){
            instance = new RandomAlgorithm();
        }
        return instance;
    }

    /**
     * @return String
     */
    public String toString() {
        return "Aléatoire";
    }
}