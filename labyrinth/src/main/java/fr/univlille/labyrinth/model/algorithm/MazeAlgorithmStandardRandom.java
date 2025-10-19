package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Cette classe est un algorithme de labyrinthe, générant un labyrinthe avec l'algorithme du parcours en profondeur de type standard avec entrée/sortie aléatoire.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class MazeAlgorithmStandardRandom extends MazeAlgorithmStandard{
    protected Position[] positions = new Position[2];

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

        generateRandomStartAndEnd(width, height);

        tracePath();

        removePercentageWall();
        return maze;
    }

    protected void generateRandomStartAndEnd(int width, int height) {
        positions[0] = new Position( random.nextInt(width /2 -1)*2+1, random.nextInt(height /2 -1)*2+1);
        do {
            positions[1] = new Position( random.nextInt(width /2 -1)*2+1, random.nextInt(height /2 -1)*2+1);
        } while (positions[0].equals(positions[1]));

    }

    public Position getStart(){
        return positions[0];
    }

    public Position getEnd(){
        return positions[1];
    }


    protected void tracePath(){
        Stack<CellAlgorithmBoolean> cellAlgorithmBooleanStack = new Stack<>();
        Set<CellAlgorithmBoolean> visitedCellAlgorithmBoolean = new HashSet<>();
        cellAlgorithmBooleanStack.push(new CellAlgorithmBoolean(positions[0]));
        markCell(cellAlgorithmBooleanStack.peek());
        CellAlgorithmBoolean cellAlgorithmBooleanEnd = new CellAlgorithmBoolean(positions[1]);
        while (!cellAlgorithmBooleanStack.isEmpty() && !cellAlgorithmBooleanStack.peek().equals(cellAlgorithmBooleanEnd)){
            boolean result = findPath(cellAlgorithmBooleanStack, visitedCellAlgorithmBoolean);
            if (!result) {
                CellAlgorithmBoolean oldCellAlgorithmBoolean = cellAlgorithmBooleanStack.pop();
                unmarkPathBetweenCell(cellAlgorithmBooleanStack.peek(), oldCellAlgorithmBoolean);
                visitedCellAlgorithmBoolean.add(oldCellAlgorithmBoolean);
            }
        }

    }
    private static MazeAlgorithmStandardRandom instance;

    public static MazeAlgorithmStandardRandom getInstance(){
        if (instance==null){
            instance=new MazeAlgorithmStandardRandom();

        }
        return instance;
    }
}
