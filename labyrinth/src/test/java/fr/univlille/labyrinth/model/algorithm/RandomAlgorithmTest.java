package fr.univlille.labyrinth.model.algorithm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.maze.Maze;

/*
 * Cet algorithme hérite d'un parfait, ainsi seulement ses fonctions aléatoires seront testées.
 */
public class RandomAlgorithmTest {
    
    /*
     * L'objectif de ce test est de vérifier qu'après passage de l'algorithme aléatoire, le labyrinthe initialement parfait est maintenant imparfait avec le pourcentage voulu de murs.
     */
    @Test
    void shouldRespectWallPercentageStatistically() {
        double targetPercentage = 0.4;

        double seuilOuCestOk = 0.05;

        int randomMazeWalls = 0 ;
        int perfectMazeWalls = 0 ;

        for (int i = 0; i < 100; i++) {
            Maze maze = new Maze(20, 20, 1.0, MazeAlgorithmFactory.PERFECT.getAlgorithm());
            perfectMazeWalls = getTotalWalls(maze);

            RandomAlgorithm.getInstance().applyRandomRemoval(maze, targetPercentage);
            randomMazeWalls = getTotalWalls(maze);
        }

        double actualPercentage = (double) randomMazeWalls / perfectMazeWalls;
        assertTrue(Math.abs(actualPercentage - targetPercentage) < seuilOuCestOk);
    }

    //helper pour wall percentage
    private int getTotalWalls(Maze maze) {
        int walls = 0 ;
        for (int y = 0; y < maze.getHeight() - 1; y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (maze.getMurHorizontaux()[y][x]) {
                    walls++;
                }
            }
        }

        for (int x = 0; x < maze.getWidth() - 1; x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                if (maze.getMurVerticaux()[x][y]) {
                    walls++;
                }
            }
        }
        return walls ;
    }
}
