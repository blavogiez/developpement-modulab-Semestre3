package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.exceptions.maze.MazeWallAdjacentException;

/*
 * Classe helper contenant les vérifications de murs
 * Sert à ne pas surcharger la classe Maze de méthodes en réalité plus static.
 */
public class MazeWallChecker {

    /**
     *
     * @param maze Maze de labyrinthe
     * @return true s'il y a un mur entre la Positionule située entre la Positionule à la
     *         position (ligne,colonne) et la Positionule à la position
     *         (ligne1,colonne1), false sinon
     */
    
    public static boolean isWall(Maze maze) {
        int y1=maze.entryPosition.getY();
        int x1=maze.entryPosition.getX();
        int y2=maze.exitPosition.getY(); 
        int x2=maze.exitPosition.getX(); 
        if (!adjacent(maze))
            throw new MazeWallAdjacentException("Les positions d’entrée et de sortie ne sont pas adjacentes !");

        if (!positionCorrecte(y1, x1, maze) || !positionCorrecte(y2, x2, maze)) {
            return true;
        }

        if (x1 == x2) {
            return maze.murHorizontaux[Math.min(y1, y2)][x1];
        }
        if (y1 == y2) {
            return maze.murVerticaux[Math.min(x1, x2)][y1];
        }


        //IMPOSSIBLE
        return true;
    }

    /** 
     * @param maze
     * @param y1
     * @param x1
     * @param y2
     * @param x2
     * @return boolean
     */
    public static boolean isWall(Maze maze,int y1,int x1,int y2,int x2) {
        if (!adjacent(maze,y1, x1, y2, x2))
            throw new MazeWallAdjacentException("Les positions("+y1+","+x1+") et ("+y2+","+x2+") ne sont pas adjacentes");
        Position p1 =new Position(x1, y1);
        Position p2 =new Position(x2, y2);
        if (!positionCorrecte(p1,maze) || !positionCorrecte(p2,maze)) {
            return true;
        }

        if (x1 == x2) {
            return maze.murHorizontaux[Math.min(y1, y2)][x1];
        }
        if (y1 == y2) {
            return maze.murVerticaux[Math.min(x1, x2)][y1];
        }


        //IMPOSSIBLE
        return true;

    }

    /** 
     * @param maze
     * @return boolean
     */
    /*
     * La méthode permet de savoir si la position se situe dans le labyrinthe.
     */
    public static boolean positionCorrecte(Maze maze) {
        int y=maze.entryPosition.getY();
        int x=maze.entryPosition.getX();
        int height=maze.getHeight();
        int width=maze.getWidth();
        return y >= 0 && y < height && x >= 0 && x < width;
    }

    /** 
     * @param y
     * @param x
     * @param maze
     * @return boolean
     */
    public static boolean positionCorrecte(int y, int x, Maze maze) {
        int height=maze.getHeight();
        int width=maze.getWidth();
        return y >= 0 && y < height && x >= 0 && x < width;
    }

    /** 
     * @param position
     * @param maze
     * @return boolean
     */
    public static boolean positionCorrecte(Position position,Maze maze) {
        int height=maze.getHeight();
        int width=maze.getWidth();
        return position.getY() >= 0 && position.getY() < height && position.getX() >= 0 && position.getX() < width;
    }

    /** 
     * @param maze
     * @return boolean
     */
    public static boolean adjacent(Maze maze) {
        int y1=maze.entryPosition.getY();
        int x1=maze.entryPosition.getX();
        int y2=maze.exitPosition.getY(); 
        int x2=maze.exitPosition.getX(); 
        return (y1 == y2 && (x1 == x2 - 1 || x1 == x2 + 1))
                || (x1 == x2 && (y1 == y2 - 1 || y1 == y2 + 1));
    }

    /** 
     * @param maze
     * @param y1
     * @param x1
     * @param y2
     * @param x2
     * @return boolean
     */
    public static boolean adjacent(Maze maze,int y1,int x1,int y2,int x2) {
        return (y1 == y2 && (x1 == x2 - 1 || x1 == x2 + 1))
                || (x1 == x2 && (y1 == y2 - 1 || y1 == y2 + 1));
    }
}
