package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.Main;

public class Maze {
    private int width;
    private int height;
    private boolean[][] grid;
    private Position playerPosition;
    private Position entryPosition;
    private Position exitPosition;

    public Maze(int width, int height, double wallPercentage) {
        this.width=width;
        this.height=height;
        this.grid = Main.getAlgo().createLabyrinthe(0, 0, 0);
        
        
    }

    public boolean isPlayerPositionAtExit() {
        return false;
    }

    public int getWidth() {
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

    public void setPlayerPosition(Position playerPosition) {

    }
}
