package fr.univlille.labyrinth.model.algorithm.pathsearch;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.MazeWallChecker;
import fr.univlille.labyrinth.model.maze.Position;

import java.util.*;

// Algorithme tiré du TP02 de dev efficace ; utile pour les tests et sera demandé en dev efficace.
public abstract class DepthStackSearch {
    private DepthStackSearch(){}

   private static Set<Position> marked;

   /**
    * @param maze
    * @return boolean
    */
   public static boolean isExitPossible(Maze maze) {
       marked = new HashSet<>();

       Deque<Position> stack = new ArrayDeque<>();

       Position start = maze.getEntryPosition();
       stack.push(start);

       marked.add(start);

       while (!stack.isEmpty()) {
           Position current = stack.peek();

           if (current.equals(maze.getExitPosition())) {
               return true;
           }

           else {
               boolean neighborFound = false;
               neighborFound = isNeighborFound(maze, current, neighborFound, stack);
               if (!neighborFound) {
                   stack.pop();
               }
           }
       }
       return false;
   }

    private static boolean isNeighborFound(Maze maze, Position current, boolean neighborFound, Deque<Position> stack) {
        for (Position neighbor : getNeighbors(current)) {
            if (neighbor != null && !neighborFound && isValid(neighbor, maze) &&!marked.contains(neighbor) && !MazeWallChecker.isWall(maze, current.getY(), current.getX(), neighbor.getY(), neighbor.getX())) {
                        //System.out.println(neighbor.getY() + "," + neighbor.getX());
                        marked.add(neighbor);
                        stack.push(neighbor);
                        neighborFound = true;
                    }


        }
        return neighborFound;
    }

    /**
    * @param pos
    * @return Position[]
    */
   private static Position[] getNeighbors(Position pos) {
       Position[] neighbors = new Position[4];
       neighbors[0] = new Position(pos.getX() + 1, pos.getY());
       neighbors[1] = new Position(pos.getX() - 1, pos.getY());
       neighbors[2] = new Position(pos.getX(), pos.getY() + 1);
       neighbors[3] = new Position(pos.getX(), pos.getY() - 1);
       return neighbors;
   }

   /**
    * @param pos
    * @param maze
    * @return boolean
    */
   private static boolean isValid(Position pos, Maze maze) {
       return pos.getX() >= 0 && pos.getX() < maze.getWidth() &&
              pos.getY() >= 0 && pos.getY() < maze.getHeight();
   }
}