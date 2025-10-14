package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.Direction;

import java.util.*;

public class AlgoLaby2 {
    private static boolean[][] maze;
    private static double percentageWall;
    static final boolean WALL = false, PATH = true;

    public static boolean[][] createMaze(int height, int width, double percentageOfWall){
        maze=new boolean[height*2+1][width*2+1];
        percentageWall=percentageOfWall+0.5;

        tracePath();
        removePercentageWall();
        return maze;
    }

    private static void removePercentageWall() {
        Random random = new Random();
        for (int i = 1; i< maze.length-1;i++){
            for (int j = 1; j<maze[0].length-1;j++){
                if (!maze[i][j] && random.nextDouble(1)>percentageWall) maze[i][j]=true;
            }
        }
    }



    private static void markCell(int x, int y){
        maze[x][y]=PATH;
    }
    private static void markCell(Cell cell){
        maze[cell.x][cell.y]=PATH;
    }

    private static void markPathBetweenCell(int x1, int y1, int x2, int y2){
        markCell(x1,y1);
        markCell(x2,y2);
        markCell((x1+x2)/2,(y1+y2)/2);
    }

    private static void markPathBetweenCell(Cell cell1, Cell cell2){
        markPathBetweenCell(cell1.x,cell1.y,cell2.x,cell2.y);
    }

    private static void tracePath(){
        Stack<Cell> cellStack = new Stack<>();
        cellStack.push(new Cell(1,1));
        markCell(cellStack.peek());
        while (!cellStack.isEmpty()){
            boolean result = findPath(cellStack);
            if (!result) {
                cellStack.pop();
            }
        }

    }

    private static boolean findPath(Stack<Cell> cellStack) {
        List<Direction> directions = new ArrayList<>(Arrays.stream(Direction.values()).toList());
        Collections.shuffle(directions);
        while (!directions.isEmpty()){
            Direction direction = directions.remove(0);
            Cell cellCheck = cellStack.peek().add(direction.getX()*2,direction.getY()*2);
            if (isWall(cellCheck)){
                markPathBetweenCell(cellStack.peek(),cellCheck);
                cellStack.push(cellCheck);
                return true;
            }
        }
        return false;
    }

    private static boolean isWall(int x, int y){
        if (x<1 || x> maze.length-1 || y<1 || y>maze[0].length-1) return false;
        return !maze[x][y];
    }
    private static boolean isWall(Cell cell){
        return isWall(cell.x,cell.y);
    }



}
