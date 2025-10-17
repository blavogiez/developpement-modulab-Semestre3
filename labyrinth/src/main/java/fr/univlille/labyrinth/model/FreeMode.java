package fr.univlille.labyrinth.model;

public class FreeMode extends GameMode {

    public static int mazeWidth = 20;
    public static int mazeHeight = 20;
    public static int mazeWallPercentage = 80;

    public static int getWidth() {
        return mazeWidth;
    }

    public static int getHeight() {
        return mazeHeight;
    }

    public static int getWallPercentage() {
        return mazeWallPercentage;
    }

    // In FreeMode, the player doesn't exist as nothing is saved, thus no name'll be asked
    @Override
    public void start() {
        // stats chosen by the player
        // will be communicated by the controller for the future
        int width = mazeWidth ;
        int height = mazeHeight ;
        int wallPercentage = mazeWallPercentage ;

        // Create the wanted maze
        createMaze(width, height, wallPercentage);

        // Enter into the main loop for as long as exit isn't chosen
//        navigate();
    }
}
