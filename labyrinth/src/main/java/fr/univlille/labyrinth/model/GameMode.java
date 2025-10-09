package fr.univlille.labyrinth.model;

import java.awt.*;

public abstract class GameMode {
    protected Maze currentMaze;

    public abstract void start();

    public Maze createMaze(int width, int height, int wallPercentage) {
        return null ;
    }

    public void movePlayerPosition(Direction direction) {
        if (currentMaze!=null && currentMaze.getPlayerPosition()!=null){
            Position playerPosition = currentMaze.getPlayerPosition();
            switch (direction) {
                case RIGHT:
                    if (playerPosition.getX()+2<currentMaze.getWidth()){
                        playerPosition.setX(playerPosition.getX()+2);
                        System.out.println("You are moving to the right");
                    }
                    System.out.println("You tried to move to the right");

                    break;
                case LEFT:
                    if (playerPosition.getX()-2<currentMaze.getWidth()){
                        playerPosition.setX(playerPosition.getX()-2);
                        System.out.println("You are moving to the left");
                    }
                    System.out.println("You tried to move to the left");

                    break;
                case UP:
                    if (playerPosition.getY()+2<currentMaze.getHeight()){
                        playerPosition.setX(playerPosition.getY()+2);
                        System.out.println("You are moving to the top");
                    }
                    System.out.println("You tried to move to the top");
                    break;
                case DOWN:
                    if (playerPosition.getY()-2<currentMaze.getHeight()){
                        playerPosition.setX(playerPosition.getY()-2);
                        System.out.println("You are moving to the bottom");
                    }
                    System.out.println("You tried to move to the bottom");
                    break;

                default:
                    break;
            }
        } else {
            throw new RuntimeException("The maze isn't generated");
        }

    }

    public boolean isPlayerPositionAtExit() {
        return false;
    }

    public Maze getCurrentMaze() {
        return currentMaze;
    }
}
