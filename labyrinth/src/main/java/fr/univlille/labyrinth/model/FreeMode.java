package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.controller.LabyrinthControler;
import fr.univlille.labyrinth.view.LabyrinthScene;

public class FreeMode extends GameMode {


    // In FreeMode, the player doesn't exist as nothing is saved, thus no name'll be asked
    @Override
    public void start() {
        // stats chosen by the player
        // will be communicated by the controller for the future
        int width = 20 ;
        int height = 20 ;
        int wallPercentage = 80 ;

        // Create the wanted maze
        createMaze(width, height, wallPercentage);
        //null.setControler(new LabyrinthControler(this));
        // Enter into the main loop for as long as exit isn't chosen
//        navigate();
    }

    @Override
    public void playerWin() {

    }
}
