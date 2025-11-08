package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;

import java.util.*;

public class PerfectAlgorithm extends MazeAlgorithm {

    @Override
    public void generateMaze(Maze maze) {
        super.generateMaze(maze);




        algoProfondeur(height, width);


    }

    private void algoProfondeur(int height, int width) {
        Random random = new Random();
        boolean[][] visite = new boolean[height][width];
        Stack<Position> positionStack = new Stack<>();
        Position start = new Position(random.nextInt(width), random.nextInt(height));
        visitePosition(positionStack, start, visite);

        while (!positionStack.isEmpty()) {
            Position position = getRandomPositionNotVisited(positionStack.peek(), visite);
            if (position == null) {
                positionStack.pop();
            } else {
                visite[position.getY()][position.getX()] = true;
                removeWall(positionStack.peek(), position);
                positionStack.push(position);
            }
        }
    }


    private static Position getRandomPositionNotVisited(Position peek, boolean[][] visite) {
        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        Collections.shuffle(directions);
        while (!directions.isEmpty()) {
            Direction direction = directions.remove(directions.size() - 1);
            Position next = peek.add(direction.getY(), direction.getX());
            if (positionCorrecte(next.getY(), next.getX(), visite)) {
                if (!visite[next.getY()][next.getX()]) {
                    return next;
                }
            }
        }
        return null;
    }

    private static void visitePosition(Stack<Position> positionStack, Position start, boolean[][] visite) {
        positionStack.push(start);
        visite[start.getY()][start.getX()] = true;
    }


    

    private static PerfectAlgorithm instance;

    public static PerfectAlgorithm getInstance(){
        if (instance==null){
            instance = new PerfectAlgorithm();
        }
        return instance;
    }

    public String toString() {
        return "Parfait";
    }
}