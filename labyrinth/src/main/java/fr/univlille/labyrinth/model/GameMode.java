package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.Main;

import java.util.List;
import java.util.Scanner;

public abstract class GameMode {
    protected Maze currentMaze;

    public abstract void start();
    public abstract void playerWin();

    public void createMaze(int width, int height, int wallPercentage) {
        this.currentMaze = new Maze(width,height,wallPercentage);
    }

//    public void navigate(){
//        if (currentMaze==null) {
//            System.out.println("The maze wasn't generated");
//            return; //need to be updated
//        }
//        while (!isPlayerPositionAtExit()){
//            Direction direction = askDirection();
//            movePlayerPosition(direction);
//        }
//        if (isPlayerPositionAtExit() && this instanceof FreeMode) ;//The user access to the victory screen, then goes back to the menu
//        else if (isPlayerPositionAtExit() && this instanceof ProgressionMode progressionMode){
//            progressionMode.getPlayer().getProgress().markChallengeCompleted(null); //set the challenge complete (idk where he supposed to be stocked)
//            Main.getInstance().getScenes().pop();
//        } else {
//            throw new RuntimeException("The GameMode isn't recognizable");
//        }
//
//
//    }
    private List<Observer> observers;



    public void movePlayerPosition(Direction direction) {
        if (currentMaze!=null && currentMaze.getPlayerPosition()!=null){
            Position playerPosition = currentMaze.getPlayerPosition();
            if ( currentMaze.isWall(playerPosition.getX()+direction.x,playerPosition.getY()+direction.y)){
                //set error
            } else {
                currentMaze.movePlayer(direction);

            }
        }
    }
//    playerPosition.getX()+direction.x<0
//            || playerPosition.getX()+direction.x>=currentMaze.getHeight()
//            || playerPosition.getY()+direction.y<0
//            || playerPosition.getY()+direction.y>=currentMaze.getWidth()
//            ||
//    Useful if there are no walls around the maze.

    public boolean isPlayerPositionAtExit() {
        return currentMaze.isPlayerPositionAtExit();
    }

    public Maze getCurrentMaze() {
        return currentMaze;
    }


}
