package fr.univlille.labyrinth.model;

/**
 * Freemode est une extension de GameMode, elle configure le fonctionnement de start pour le mode libre.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class FreeMode extends GameMode {

    public static int mazeWidth = 20;
    public static int mazeHeight = 20;
    public static double mazeWallPercentage = 0.0 ;

    /**
     * Dans le mode libre, un labyrinthe sera généré avec les paramètres entrés par l'utilisateur.
     */
    @Override
    public void start() {

        int width = mazeWidth ;
        int height = mazeHeight ;
        double wallPercentage = mazeWallPercentage ;

        // Create the wanted maze
        createMaze(width, height, wallPercentage);
    }
}
