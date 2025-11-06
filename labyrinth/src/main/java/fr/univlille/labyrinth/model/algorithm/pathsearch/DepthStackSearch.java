package fr.univlille.labyrinth.model.algorithm.parcours;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

import java.util.Stack;
import java.util.HashSet;
import java.util.Set;

// Algorithme tiré du TP02 de dev efficace ; utile pour les tests et sera demandé en dev efficace.
public class DepthStackSearch {

   private static Set<Position> marked;

   /**
    * @param maze
    * @return boolean
    */
   // Algorithm from TP02 / Effidev
   public static boolean isExitPossible(Maze maze) {
       marked = new HashSet<>();

       Stack<Position> stack = new Stack<>();

       Position start = new Position(maze.getEntryPosition().getY(), maze.getEntryPosition().getX());
       stack.push(start);

       marked.add(start);

       while (!stack.isEmpty()) {
           Position current = stack.peek();

           if (current.equals(maze.getExitPosition())) {
               ///System.out.println("Exit found !!!");
               return true;
           }

           else {
               boolean neighborFound = false;
               for (Position neighbor : getNeighbors(current, maze)) {
                   if (neighbor != null && !neighborFound) {
                       if (isValid(neighbor, maze)) {
                           if (!marked.contains(neighbor) && !maze.isWall(current.getY(), current.getX(), neighbor.getY(), neighbor.getX())) {
                               System.out.println(neighbor.getY() + "," + neighbor.getY());
                               marked.add(neighbor);
                               stack.push(neighbor);
                               neighborFound = true;
                           }
                       }
                   }
               }
               if (!neighborFound) {
                   stack.pop();
               }
           }
       }

       ///System.out.println("No path from entry to exit");
       return false;
   }

   /**
    * @param pos
    * @param maze
    * @return Position[]
    */
   private static Position[] getNeighbors(Position pos, Maze maze) {
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