package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.Direction;
import fr.univlille.labyrinth.model.Maze;

public class LabyrinthControler {
    public LabyrinthControler(){

    }

    public void movePlayer(Direction direction){
        Main.getInstance().getGameMode().movePlayerPosition(direction);
    }
}
