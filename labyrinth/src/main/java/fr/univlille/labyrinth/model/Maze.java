package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.Main;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    private List<Observer> observers;

    public boolean add(Observer observer){return observers.add(observer);}

    public void notifyObserver(){
        for (Observer observer : observers){
            observer.update(this);
        }
    }

    public void movePlayer(Direction direction){
        playerPosition.addX(direction.x);
        playerPosition.addY(direction.y);

        System.out.println("x= "+playerPosition.getX());

        System.out.println("y= "+playerPosition.getY());

        notifyObserver();
    }


    private int width;
    private int height;
    private boolean[][] grid;
    private Position playerPosition;
    private Position entryPosition;
    private Position exitPosition;

    public Maze(int width, int height, int wallPercentage) {
        this.observers=new ArrayList<>();
        this.width = width * 2 + 1;
        this.height = height * 2 + 1;
        this.grid = Main.getAlgo().createLabyrinthe(this.width, this.height, wallPercentage);

        this.playerPosition = new Position(1, 1);
        this.entryPosition = new Position(1, 1);
        this.exitPosition = new Position(width -1, height -1);

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
