package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;

import java.util.*;

public class PerfectAlgorithm implements MazeAlgorithm {


    public void generateMaze(Maze maze) {
        boolean[][] murHorizontaux = maze.getMurHorizontaux();
        boolean[][] murVerticaux = maze.getMurVerticaux();
        int largeur = maze.getWidth();
        int hauteur = maze.getHeight();
        int distanceBetweenEntryAndExit = maze.getDistanceBetweenEntryAndExit();

        allAreTrue(murHorizontaux);
        allAreTrue(murVerticaux);

        algoProfondeur(largeur, hauteur, murHorizontaux, murVerticaux);

        Random random = new Random();
        Position entryPosition = new Position(random.nextInt(largeur), random.nextInt(hauteur));

        BreadthFirstSearch.DistanceResult distResult = BreadthFirstSearch.calculateAllDistances(maze, entryPosition, distanceBetweenEntryAndExit);

        Position exitPosition = distResult.positions().get(random.nextInt(distResult.positions().size()));

        maze.setEntry(entryPosition);
        maze.setExit(exitPosition);
        maze.setDistanceBetweenEntryAndExit(distResult.actualDistance());
    }

    private static void algoProfondeur(int largeur, int hauteur, boolean[][] murHorizontaux, boolean[][] murVerticaux) {
        Random random = new Random();
        boolean[][] visite = new boolean[largeur][hauteur];
        Stack<Position> positionStack = new Stack<>();
        Position start = new Position(random.nextInt(largeur), random.nextInt(hauteur));
        visitePosition(positionStack, start, visite);

        while (!positionStack.isEmpty()) {
            Position position = getRandomPositionNotVisited(positionStack.peek(), visite);
            if (position == null) {
                positionStack.pop();
            } else {
                visite[position.getX()][position.getY()] = true;
                enleverMur(positionStack.peek(), position, murHorizontaux, murVerticaux);
                positionStack.push(position);
            }
        }
    }

    private static void enleverMur(Position start, Position next, boolean[][] murHorizontaux, boolean[][] murVerticaux) {
        Direction diff = start.diff(next);
        Position min = start.min(next);
        switch (diff) {
            case LEFT, RIGHT -> {
                murVerticaux[min.getX()][min.getY()] = false;
            }
            case UP, DOWN -> {
                murHorizontaux[min.getY()][min.getX()] = false;
            }
        }
    }

    private static Position getRandomPositionNotVisited(Position peek, boolean[][] visite) {
        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        Collections.shuffle(directions);
        while (!directions.isEmpty()) {
            Direction direction = directions.remove(directions.size() - 1);
            Position next = peek.add(direction.getX(), direction.getY());
            if (positionCorrecte(next.getX(), next.getY(), visite)) {
                if (!visite[next.getX()][next.getY()]) {
                    return next;
                }
            }
        }
        return null;
    }

    private static void visitePosition(Stack<Position> positionStack, Position start, boolean[][] visite) {
        positionStack.push(start);
        visite[start.getX()][start.getY()] = true;
    }

    private static void allAreTrue(boolean[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            Arrays.fill(tab[i], true);
        }
    }
    
    public static boolean positionCorrecte(int ligne, int colonne, boolean[][] tab) {
        return ligne>=0 && ligne < tab.length && colonne >=0 && colonne < tab[0].length;
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