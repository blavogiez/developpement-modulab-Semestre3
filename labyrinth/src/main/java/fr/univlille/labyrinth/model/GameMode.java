package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.Main;

import java.util.List;
import java.util.Scanner;

public abstract class GameMode {
    protected Maze currentMaze;

    public abstract void start();

    public void createMaze(int width, int height, int wallPercentage) {
        this.currentMaze = new Maze(width,height,wallPercentage);
    }

    public void navigate(){
        if (currentMaze==null) {
            System.out.println("The maze wasn't generated");
            return; //need to be updated
        }
        while (!isPlayerPositionAtExit()){
            Direction direction = askDirection();
            movePlayerPosition(direction);
        }
        if (isPlayerPositionAtExit() && this instanceof FreeMode) ;//The user access to the victory screen, then goes back to the menu
        else if (isPlayerPositionAtExit() && this instanceof ProgressionMode progressionMode){
            progressionMode.getPlayer().getProgress().markChallengeCompleted(null); //set the challenge complete (idk where he supposed to be stocked)
            Main.getInstance().getScenes().pop();
        } else {
            throw new RuntimeException("The GameMode isn't recognizable");
        }


    }
    private List<Observer> observers;



    private Direction askDirection() {
        System.out.println("Please, choose a direction");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()){
            case "right":
                return Direction.RIGHT;
            case "left":
                return Direction.LEFT;
            case "up":
                return Direction.UP;
            case "down":
                return Direction.DOWN;
        }
        return Direction.RIGHT;
    }
    //That's a TMP method, it'll be changed with javafx 's implementation

    public void movePlayerPosition(Direction direction) {
        if (currentMaze!=null && currentMaze.getPlayerPosition()!=null){
            Position playerPosition = currentMaze.getPlayerPosition();
            if (playerPosition.getX()+direction.x<0
                    || playerPosition.getX()+direction.x>=currentMaze.getHeight()
                    || playerPosition.getX()+direction.y<0
                    || playerPosition.getX()+direction.y>=currentMaze.getWidth())
                System.out.println("You tried to move to the "+direction.name());
            else {
                currentMaze.movePlayer(direction);
                System.out.println("You moved to the "+direction.name());
            }
        }

    }

    public boolean isPlayerPositionAtExit() {
        return currentMaze.isPlayerPositionAtExit();
    }

    public Maze getCurrentMaze() {
        return currentMaze;
    }
}
