package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.Position;

import java.util.*;

/**
 * Cette classe est un algorithme de labyrinthe, générant un labyrinthe avec l'algorithme du parcours en profondeur de type parfait avec entrée/sortie aléatoire.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class MazeAlgorithmPerfectRandom extends MazeAlgorithmPerfect {
        protected Position[] positions = new Position[2];
        



    /**
     * Cette méthode permet de générer un labyrinthe de taille width et height. Elle génère de plus l'entrée et la sortie de manière aléatoire
     *
     * @param width Permet de mettre la largeur du labyrinthe.
     * @param height Permet de mettre la hauteur du labyrinthe.
     * @param percentageOfWall Permet de mettre un pourcentage de mur (entre 0 et 0.5)
     */
        public boolean[][] createMaze(int width, int height, double percentageOfWall){
            int w = (width % 2 == 0) ? width - 1 : width;
            int h = (height % 2 == 0) ? height - 1 : height;

            if (width<5 || height < 5) throw new MazeSizeException("The maze's size is too small to have distinct start and end placement");

            generateRandomStartAndEnd(width, height);
            
            maze=new boolean[w][h];
            percentageWall=percentageOfWall+0.5;

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
            cellAlgorithmBooleanStack.push(new CellAlgorithmBoolean(positions[0]));
            markCell(cellAlgorithmBooleanStack.peek());
            while (!cellAlgorithmBooleanStack.isEmpty()){
                boolean result = findPath(cellAlgorithmBooleanStack);
                if (!result) {
                    cellAlgorithmBooleanStack.pop();
                }
            }

        }
    private static MazeAlgorithmPerfectRandom instance;

    public static MazeAlgorithmPerfectRandom getInstance(){
        if (instance==null){
            instance=new MazeAlgorithmPerfectRandom();

        }
        return instance;
    }
}
