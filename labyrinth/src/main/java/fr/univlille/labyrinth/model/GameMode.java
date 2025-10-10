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
            throw new RuntimeException("The maze wasn't generated");

        }
    }

    public boolean isPlayerPositionAtExit() {
        return currentMaze.isPlayerPositionAtExit();
    }

    public Maze getCurrentMaze() {
        return currentMaze;
    }
}
