package fr.univlille.labyrinth.model;

public abstract class GameMode {
    protected Maze currentMaze;

    public abstract void start();

    public Maze createMaze(int width, int height, int wallPercentage) {
        return null;
    }

    public void movePlayerPosition(String direction) {

    }

    public boolean isPlayerPositionAtExit() {
        return false;
    }

    public Maze getCurrentMaze() {
        return currentMaze;
    }
}
