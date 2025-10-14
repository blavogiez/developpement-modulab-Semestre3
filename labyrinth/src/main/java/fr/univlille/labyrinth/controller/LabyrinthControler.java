package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.Direction;
import fr.univlille.labyrinth.model.GameMode;
import fr.univlille.labyrinth.model.Maze;

public class LabyrinthControler {
    GameMode gameMode;
    public LabyrinthControler(GameMode gameMode){
        this.gameMode=gameMode;
    }

    public void movePlayer(Direction direction){
        gameMode.movePlayerPosition(direction);
    }


    public void playerWin() {
        gameMode.playerWin();
    }
}
