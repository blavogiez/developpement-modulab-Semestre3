package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.Main;

public class Maze {

    private int width;
    private int height;
    private boolean[][] grid;
    private Position playerPosition;
    private Position entryPosition;
    private Position exitPosition;

    public Maze(int width, int height, int wallPercentage) {
        this.width=width*2+1;
        this.height=height*2+1;
        this.grid = Main.getInstance().getAlgo().createLabyrinthe(width, height, wallPercentage);

        this.playerPosition=new Position(1,1);
        this.entryPosition=new Position(1,1);
        this.exitPosition=new Position(width*2,height*2);

    }


    public boolean isPlayerPositionAtExit() {
        return playerPosition.equals(exitPosition);
    }

    public int getWidth()    {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }

    public Position getEntryPosition() {
        return entryPosition;
    }

    public Position getExitPosition() {
        return exitPosition;
    }
}
