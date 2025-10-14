package fr.univlille.labyrinth.parcours;

import fr.univlille.labyrinth.model.*;
import java.util.Stack;
import java.util.HashSet;
import java.util.Set;

public class DepthStackSearch {

    private static Set<Position> marked;

    // Algorithm from TP02 / Effidev
    public static boolean isExitPossible(Maze maze) {
        marked = new HashSet<>();

        Stack<Position> stack = new Stack<>();

        Position start = new Position(maze.getEntryPosition().getX(), maze.getEntryPosition().getY());
        stack.push(start);

        marked.add(start);

        while (!stack.isEmpty()) {
            try {
                Thread.sleep(10);
            }
            catch(InterruptedException e) {
                System.out.println(e);
            }

            Position current = stack.peek();

            if (current.equals(maze.getExitPosition())) {
                System.out.println("Exit found !!!");
                return true;
            }

            else {
                boolean neighborFound = false;
                for (Position neighbor : getNeighbors(current, maze)) {
                    if (neighbor != null && !neighborFound) {
                        if (isValid(neighbor, maze)) {
                            if (!marked.contains(neighbor) && !maze.isWall(neighbor.getX(), neighbor.getY())) {
                                System.out.println(neighbor.getX() + "," + neighbor.getY());
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

        System.out.println("No path from entry to exit");
        return false;
    }

    private static Position[] getNeighbors(Position pos, Maze maze) {
        Position[] neighbors = new Position[4];
        neighbors[0] = new Position(pos.getX() + 1, pos.getY());
        neighbors[1] = new Position(pos.getX() - 1, pos.getY());
        neighbors[2] = new Position(pos.getX(), pos.getY() + 1);
        neighbors[3] = new Position(pos.getX(), pos.getY() - 1);
        return neighbors;
    }

    private static boolean isValid(Position pos, Maze maze) {
        return pos.getX() >= 0 && pos.getX() < maze.getWidth() &&
               pos.getY() >= 0 && pos.getY() < maze.getHeight();
    }
}