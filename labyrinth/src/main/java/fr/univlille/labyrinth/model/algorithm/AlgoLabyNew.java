package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.Direction;

import java.util.*;

/**
 * Cette classe est un algorithme de labyrinthe, générant un labyrinthe avec l'algorithme du parcours en profondeur
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class AlgoLabyNew {
    private static boolean[][] maze;
    private static double percentageWall;
    private static final boolean PATH = true;


    /**
     * Cette méthode permet de générer un labyrinthe de taille width et height
     *
     * @param width Permet de mettre la largeur du labyrinthe.
     * @param height Permet de mettre la hauteur du labyrinthe.
     * @param percentageOfWall Permet de mettre un pourcentage de mur (entre 0 et 0.5)
     */
    public static boolean[][] createMaze(int width, int height, double percentageOfWall){
        int w = (width % 2 == 0) ? width - 1 : width;
        int h = (height % 2 == 0) ? height - 1 : height;
        maze=new boolean[w][h];
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
        if (x<1 || x>= maze.length-1 || y<1 || y>= maze[0].length-1) return false;
        return !maze[x][y];
    }
    private static boolean isWall(Cell cell){
        return isWall(cell.x,cell.y);
    }



}
