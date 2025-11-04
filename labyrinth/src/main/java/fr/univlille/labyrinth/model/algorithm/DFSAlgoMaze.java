package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSAlgoMaze {
    private final Maze maze;


    public DFSAlgoMaze(Maze maze) {
        this.maze = maze;
    }

    public List<int[]> voisins(int ligne, int colonne, boolean[][] visite){
        List<int[]> res = new ArrayList<>();
        if(ligne > 0 && !visite[ligne-1][colonne]){
            res.add(new int[]{ligne-1,colonne});
        }
        if(this.maze.getWidth() -1 > ligne && !visite[ligne+1][colonne]){
            res.add(new int[]{ligne+1,colonne});
        }
        if(colonne > 0 && !visite[ligne][colonne-1]){
            res.add(new int[]{ligne,colonne-1});
        }
        if(this.maze.getHeight() -1  > colonne && !visite[ligne][colonne+1]){
            res.add(new int[]{ligne,colonne+1});
        }
        return res;
    }

    public boolean estMur(int ligne, int colonne, int ligne1, int colonne1) {

        if (!adjacent(ligne, colonne, ligne1, colonne1))
            throw new RuntimeException();

        if (!positionCorrecte(ligne, colonne) || !positionCorrecte(ligne1, colonne1)) {
            return true;
        }

        if (colonne == colonne1) {
            return maze.getMurHorizontaux()[Math.min(ligne,ligne1)][colonne];
        }
        if (ligne == ligne1) {
            return maze.getMurVerticaux()[Math.min(colonne,colonne1)][ligne];
        }
        return true;
    }

    private boolean positionCorrecte(int ligne, int colonne) {
        return ligne >= 0 && ligne < maze.getHeight() && colonne >= 0 && colonne < maze.getWidth() ;
    }

    private boolean adjacent(int ligne, int colonne, int ligne1, int colonne1) {
        return (ligne == ligne1 && (colonne == colonne1 - 1 || colonne == colonne1 + 1))
                || (colonne == colonne1 && (ligne == ligne1 - 1 || ligne == ligne1 + 1));
    }


}
