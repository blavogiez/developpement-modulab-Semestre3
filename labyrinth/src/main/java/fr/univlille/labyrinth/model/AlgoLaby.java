package fr.univlille.labyrinth.model;


import java.util.Random;

public class AlgoLaby {

    Random random = new Random();

//    public static void main(String[] args) {
//        AlgoLaby algo = new AlgoLaby();
//        int[][] maze = algo.generateGrid(21,21);
//        maze = algo.generatePath(maze);
//        boolean[][] newMaze = algo.exportMaze(maze);
//        System.out.println(algo.mazeToString(newMaze));
//    }

    public boolean[][] createLabyrinthe(int width, int height, int percentageWall){

        int[][] maze = generateGrid(width,height,percentageWall);
        maze = generatePath(maze);
        return exportMaze(maze);
    }

//    public void testShow(int[][] maze){
//        int height = maze.length;
//        int width = maze[0].length;
//        for (int l = 0; l<height;l++){
//            for (int c = 0; c<width;c++){
//                if(maze[l][c]==-1) System.out.print("=");
//                else System.out.print(maze[l][c]);
//            }
//            System.out.println("");
//        }
//    }

    public int[][] generateGrid(int width, int height, int percentageWall){
        int[][] maze = new int[width][height];
        for (int l = 0; l<width;l++){
            for (int c = 0; c<height;c++){
                if (c%2==0 || l%2==0) maze[l][c] = -1;
                else maze[l][c] = random.nextInt(percentageWall);
            }
        }
        return maze;
    }

    public boolean[][] exportMaze(int[][] maze){

        int height = maze.length;
        int width = maze[0].length;
        boolean[][] exportedMaze = new boolean[height][width];
        for (int l = 0; l<height;l++){
            for (int c = 0; c<width;c++){
                if (maze[l][c]==maze[1][1]) exportedMaze[l][c]=true;
            }
        }
        return exportedMaze;

    }

//    public String mazeToString(boolean[][] maze){
//        StringBuilder res = new StringBuilder();
//        int height = maze.length;
//        int width = maze[0].length;
//        for (int l = 0; l < height;l++) {
//            for (int c = 0; c < width; c++) {
//                if (l==1 && c==1) res.append("S");
//                else if (l==height-2 && c==width-2) res.append("E");
//                else if (maze[l][c]) res.append(" ");
//                else res.append("W");
//            }
//            res.append("\n");
//        }
//        return res.toString();
//    }

    public int[][] generatePath(int[][] maze){
        while (!AllTheSameNumber(maze)){
            maze = modifyMaze(maze);
//            testShow(maze);
        }
        return maze;
    }

    public boolean AllTheSameNumber(int[][] maze){ //TODO à améliorer
        int number = maze[1][1];
        int height = maze.length;
        int width = maze[0].length;
        for (int l = 0; l<height; l++) {
            for (int c = 0; c < width; c++) {
                if (l%2==1 && c%2==1){
                    if (maze[l][c] != -1 && maze[l][c] != number) {
                        return false;
                    }
                }

            }
        }
        return true;

    }

    public int[][] modifyMaze(int[][] maze){
        int height = maze.length;
        int width = maze[0].length;
        int[] cell = generateRandomCell(maze);
        Direction direction = generateRandomDirection();
        int[] otherCell = addDirection(cell[0],cell[1],direction);
        if (otherCell[0]<=0 || otherCell[0]>=height || otherCell[1]<=0 || otherCell[1]>=width) return maze;

        int otherCellValue = maze[otherCell[0]][otherCell[1]];
        int cellValue = maze[cell[0]][cell[1]];

        if (cellValue==otherCellValue) return maze;

        int value = takeRandomValueBetweenCellsValues(cellValue,otherCellValue);
        maze[cell[0]][cell[1]] = value;
        maze[otherCell[0]][otherCell[1]] = value;
        maze[(cell[0]+otherCell[0])/2][(cell[1]+otherCell[1])/2] = value;
        return maze;

    }

    private int takeRandomValueBetweenCellsValues(int cellValue, int otherCellValue) {
        if (random.nextBoolean()){
            return cellValue;
        }
        return otherCellValue;
    }

    public int[] generateRandomCell(int[][] maze){
        int height = maze.length;
        int width = maze[0].length;
        int[] cell = new int[]{0,0};
        do {
            cell[0]=random.nextInt(height);
            cell[1]=random.nextInt(width);
        } while (cell[0]%2!=1 || cell[1]%2!=1);
        return cell;



    }

    private Direction generateRandomDirection() {
        int randomDirection = random.nextInt(4);
        if (randomDirection==0) return Direction.UP;
        else if (randomDirection==1) return Direction.LEFT;
        else if (randomDirection==2) return Direction.DOWN;
        else return Direction.RIGHT;
    }

    private int[] addDirection(int x, int y, Direction direction){
        int[] res = new int[2];
        if (direction.equals(Direction.UP)) y+=2;
        else if (direction.equals(Direction.DOWN)) y-=2;
        else if (direction.equals(Direction.LEFT)) x-=2;
        else x+=2;
        res[0]=x;
        res[1]=y;
        return res;
    }


}
