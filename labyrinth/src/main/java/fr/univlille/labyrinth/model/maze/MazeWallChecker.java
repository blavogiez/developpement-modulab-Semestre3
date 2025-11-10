package fr.univlille.labyrinth.model.maze;

public class  MazeWallChecker {

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
        if (!maze.adjacent(y1, x1, y2, x2))
            throw new RuntimeException();

        if (!maze.positionCorrecte(y1, x1) || !maze.positionCorrecte(y2, x2)) {
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

    /*
     * La méthode permet de savoir si la position se situe dans le labyrinthe.
     */
    public boolean positionCorrecte(Maze maze) {
        int y=maze.entryPosition.getY();
        int x=maze.entryPosition.getX();
        int height=maze.getHeight();
        int width=maze.getWidth();
        return y >= 0 && y < height && x >= 0 && x < width;
    }

    public boolean adjacent(Maze maze) {
        int y1=maze.entryPosition.getY();
        int x1=maze.entryPosition.getX();
        int y2=maze.exitPosition.getY(); 
        int x2=maze.exitPosition.getX(); 
        return (y1 == y2 && (x1 == x2 - 1 || x1 == x2 + 1))
                || (x1 == x2 && (y1 == y2 - 1 || y1 == y2 + 1));
    }
}
