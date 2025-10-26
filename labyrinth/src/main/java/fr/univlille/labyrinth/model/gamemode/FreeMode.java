package fr.univlille.labyrinth.model.gamemode;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;

/**
 * Freemode est une extension de GameMode pour le mode libre (la plus simple possible).
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class FreeMode extends GameMode {
    //ces attributs peuvent être amenés à être changés par le controlleur afin d'assurer une persistance par session (le joueur n'a pas à resaisir plein de fois son entrée !)
    public static MazeAlgorithmFactory algorithm = MazeAlgorithmFactory.STANDARDLARGEUR ;
    public static int mazeWidth = 20;
    public static int mazeHeight = 20;
    public static double mazeWallPercentage = 0.4 ;

    // surcharge de createMaze étendu pour l'appeler avec les arguments de la classe, et sans distance entrée/sortie (non demandée dans le sujet pour le mode libre)!
    public void createMaze() {
        createMaze(algorithm, mazeWidth, mazeHeight, mazeWallPercentage);
    }
}
