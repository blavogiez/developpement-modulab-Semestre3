package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.Direction;
import fr.univlille.labyrinth.model.Position;

import java.util.*;

/**
 * Cette classe est un algorithme de labyrinthe, générant un labyrinthe avec l'algorithme du parcours en profondeur de type parfait
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class MazeAlgorithmPerfect extends MazeAlgorithmTemplateProfondeur {


    /**
     * Cette méthode permet de générer un labyrinthe de taille width et height
     *
     * @param width Permet de mettre la largeur du labyrinthe.
     * @param height Permet de mettre la hauteur du labyrinthe.
     * @param percentageOfWall Permet de mettre un pourcentage de mur (entre 0 et 0.5)
     */
    public boolean[][] createMaze(int width, int height, double percentageOfWall){
        int w = (width % 2 == 0) ? width - 1 : width;
        int h = (height % 2 == 0) ? height - 1 : height;
        maze=new boolean[w][h];
        percentageWall=percentageOfWall+0.5;

        tracePath();
        removePercentageWall();
        return maze;
    }



    /** 
     * @return Position
     */
    public Position getStart(){
        return new Position(1, 1);
    }

    /** 
     * @return Position
     */
    public Position getEnd(){
        return new Position(maze.length-2, maze[0].length-2);
    }

    protected void tracePath(){
        Stack<CellAlgorithmBoolean> cellAlgorithmBooleanStack = new Stack<>();
        cellAlgorithmBooleanStack.push(new CellAlgorithmBoolean(1,1));
        markCell(cellAlgorithmBooleanStack.peek());
        while (!cellAlgorithmBooleanStack.isEmpty()){
            boolean result = findPath(cellAlgorithmBooleanStack);
            if (!result) {
                cellAlgorithmBooleanStack.pop();
            }
        }

    }

    /** 
     * @param cellAlgorithmBooleanStack
     * @return boolean
     */
    protected boolean findPath(Stack<CellAlgorithmBoolean> cellAlgorithmBooleanStack) {
        List<Direction> directions = new ArrayList<>(Arrays.stream(Direction.values()).toList());
        Collections.shuffle(directions);
        while (!directions.isEmpty()){
            Direction direction = directions.remove(0);
            CellAlgorithmBoolean cellAlgorithmBooleanCheck = cellAlgorithmBooleanStack.peek().add(direction.getX()*2,direction.getY()*2);
            if (isWall(cellAlgorithmBooleanCheck)){
                markPathBetweenCell(cellAlgorithmBooleanStack.peek(), cellAlgorithmBooleanCheck);
                cellAlgorithmBooleanStack.push(cellAlgorithmBooleanCheck);
                return true;
            }
        }
        return false;
    }
    private static MazeAlgorithmPerfect instance;


    /** 
     * @return MazeAlgorithmTemplate
     */
    public static MazeAlgorithmTemplate getInstance() {
        if (instance==null){
            instance=new MazeAlgorithmPerfect();

        }
        return instance;
    }
    
}
