package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

import java.util.*;

public class PerfectAlgorithm {
    private static int largeur, hauteur;
    private static boolean[][] murVerticaux, murHorizontaux;
    private static Cell[][] mazeCells;

    // Construit un labyrinthe parfait avec largeur colonnes et hauteur ligne
    public static Maze createMaze(int largeur, int hauteur) {
        PerfectAlgorithm.largeur = largeur;
        PerfectAlgorithm.hauteur = hauteur;
        murVerticaux = new boolean[largeur - 1][hauteur];
        murHorizontaux = new boolean[hauteur - 1][largeur];
        allAreTrue(murHorizontaux);
        allAreTrue(murVerticaux);
        algoProfondeur();
        return new Maze(largeur, hauteur, murHorizontaux, murVerticaux, mazeCells);
    }

    private static void algoProfondeur() {
        Random random = new Random();
        boolean[][] visite = new boolean[largeur][hauteur];
        Stack<Position> PositionStack = new Stack<Position>();
        Position start = new Position(random.nextInt(largeur),random.nextInt(hauteur));
        visitePosition(PositionStack,start,visite);

        while (!PositionStack.isEmpty()){
            Position Position = getRandomPositionNotVisited(PositionStack.peek(),visite);
            if (Position==null) PositionStack.pop();
            else {
                visite[Position.getX()][Position.getY()] = true;
                enleverMur( PositionStack.peek(), Position);
                PositionStack.push(Position);

            }
        }

    }
    private static void enleverMur(Position start, Position next){
        Direction diff = start.diff(next);
        Position min = start.min(next);
        switch (diff){
            case LEFT, RIGHT -> {
                murVerticaux[min.getX()][min.getY()]=false;
            }
            case UP, DOWN -> {
                murHorizontaux[min.getY()][min.getX()]=false;
            }
        }

    }

    private static Position getRandomPositionNotVisited(Position peek, boolean[][] visite) {
        List<Direction> directions = new ArrayList<>(Arrays.stream(Direction.values()).toList());
        Collections.shuffle(directions);
        while (!directions.isEmpty()){
            Direction direction = directions.remove(directions.size()-1);
            Position next = peek.add(direction.getX(),direction.getY());
            if (positionCorrecte(next.getX(),next.getY(),visite))
                if(!visite[next.getX()][next.getY()])
                    return next;

        }
        return null;


    }

    private static void visitePosition(Stack<Position> PositionStack, Position start,boolean[][] visite) {
        PositionStack.push(start);
        visite[start.getX()][start.getY()] = true;
    }

    private static void allAreTrue(boolean[][] tab) {
        for (int i = 0; i<tab.length;i++){
            for (int j = 0;j<tab[0].length;j++){
                tab[i][j]=true;
            }
        }
    }


    public static int getLargeur() {
        return largeur;
    }

    public static int getHauteur() {
        return hauteur;
    }



    public static boolean positionCorrecte(int ligne, int colonne) {
        return ligne >= 0 && ligne < hauteur && colonne >= 0 && colonne < largeur;
    }

    public static boolean positionCorrecte(int ligne, int colonne, boolean[][] tab) {
        return ligne>=0 && ligne < tab.length && colonne >=0 && colonne < tab[0].length;
    }

    public static boolean adjacent(int ligne, int colonne, int ligne1, int colonne1) {
        return (ligne == ligne1 && (colonne == colonne1 - 1 || colonne == colonne1 + 1))
                || (colonne == colonne1 && (ligne == ligne1 - 1 || ligne == ligne1 + 1));
    }
}
