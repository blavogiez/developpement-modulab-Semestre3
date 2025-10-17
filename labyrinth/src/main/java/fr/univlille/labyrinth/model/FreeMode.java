package fr.univlille.labyrinth.model;

public class FreeMode extends GameMode {

    //valeurs par défaut
    public static int mazeWidth = 20;
    public static int mazeHeight = 20;
    public static double mazeWallPercentage = 0.0 ;

    // In FreeMode, the player doesn't exist as nothing is saved, thus no name'll be asked
    @Override
    public void start() {
        // stats chosen by the player
        // will be communicated by the controller for the future
        int width = mazeWidth ;
        int height = mazeHeight ;
        double wallPercentage = mazeWallPercentage ;

        // Create the wanted maze
        createMaze(width, height, wallPercentage);

        // Enter into the main loop for as long as exit isn't chosen
//        navigate();
    }
}
