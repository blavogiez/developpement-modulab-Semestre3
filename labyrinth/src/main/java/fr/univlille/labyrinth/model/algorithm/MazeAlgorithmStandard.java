package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.Direction;
import fr.univlille.labyrinth.model.Position;

import java.util.*;

/**
 * Cette classe est un algorithme de labyrinthe, générant un labyrinthe avec l'algorithme du parcours en profondeur de type standard
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class MazeAlgorithmStandard extends MazeAlgorithmTemplate{
    

        /**
         * Cette méthode permet de générer un labyrinthe de taille width et height
         *
         * @param width Permet de mettre la largeur du labyrinthe.
         * @param height Permet de mettre la hauteur du labyrinthe.
         * @param percentageOfWall Permet de mettre un pourcentage de mur (entre 0 et 1)
         */
        public boolean[][] createMaze(int width, int height, double percentageOfWall){

            maze=new boolean[width][height];
            
            percentageWall=percentageOfWall;

            tracePath();
            
            removePercentageWall();
            return maze;
        }

    public Position getStart(){
        return new Position(1, 1);
    }

    public Position getEnd(){
        return new Position(maze.length-2, maze[0].length-2);
    }


        
        protected void tracePath(){
            Stack<CellAlgorithmBoolean> cellAlgorithmBooleanStack = new Stack<>();
            Set<CellAlgorithmBoolean> visitedCellAlgorithmBoolean = new HashSet<>();
            cellAlgorithmBooleanStack.push(new CellAlgorithmBoolean(1,1));
            markCell(cellAlgorithmBooleanStack.peek());
            CellAlgorithmBoolean cellAlgorithmBooleanEnd = new CellAlgorithmBoolean(getEnd().getX(),getEnd().getY());
            while (!cellAlgorithmBooleanStack.isEmpty() && !cellAlgorithmBooleanStack.peek().equals(cellAlgorithmBooleanEnd)){
                boolean result = findPath(cellAlgorithmBooleanStack, visitedCellAlgorithmBoolean);
                if (!result) {
                    CellAlgorithmBoolean oldCellAlgorithmBoolean = cellAlgorithmBooleanStack.pop();
                    unmarkPathBetweenCell(cellAlgorithmBooleanStack.peek(), oldCellAlgorithmBoolean);
                    visitedCellAlgorithmBoolean.add(oldCellAlgorithmBoolean);
                }
            }

        }
        
        protected  void unmarkPathBetweenCell(int x1, int y1, int x2, int y2){
            unmarkCell(x2,y2);
            unmarkCell((x1+x2)/2,(y1+y2)/2);
        }

    protected  void unmarkPathBetweenCell(CellAlgorithmBoolean c1, CellAlgorithmBoolean c2){
        unmarkPathBetweenCell(c1.x,c1.y,c2.x,c2.y);
    }

    private  void unmarkCell(int x1, int y1) {
        maze[x1][y1]=WALL;
    }

    protected  boolean findPath(Stack<CellAlgorithmBoolean> cellAlgorithmBooleanStack, Set<CellAlgorithmBoolean> visitedCellAlgorithmBoolean) {
            List<Direction> directions = new ArrayList<>(Arrays.stream(Direction.values()).toList());
            Collections.shuffle(directions);
            while (!directions.isEmpty()){
                Direction direction = directions.remove(0);
                CellAlgorithmBoolean cellAlgorithmBooleanCheck = cellAlgorithmBooleanStack.peek().add(direction.getX(),direction.getY());
                if (isWall(cellAlgorithmBooleanCheck) && !visitedCellAlgorithmBoolean.contains(cellAlgorithmBooleanCheck)){
                    markPathBetweenCell(cellAlgorithmBooleanStack.peek(), cellAlgorithmBooleanCheck);
                    cellAlgorithmBooleanStack.push(cellAlgorithmBooleanCheck);
                    return true;
                }
            }
            return false;
        }

    private static MazeAlgorithmStandard instance;

    public static MazeAlgorithmStandard getInstance(){
        if (instance==null){
            instance=new MazeAlgorithmStandard();

        }
        return instance;
    }
}
