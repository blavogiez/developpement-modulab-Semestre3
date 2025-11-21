package fr.univlille.labyrinth.model.maze;

import java.util.Random;


/**
 * Utilitaire pour la suppression de murs dans un labyrinthe.
 * Cette classe fournit des méthodes pour compter et supprimer aléatoirement des murs dans un labyrinthe.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public abstract class WallRemover { //Classe présente pour des futurs implementations
    private WallRemover(){}
    /**
     * Compte le nombre total de murs dans un labyrinthe.
     *
     * @param maze le labyrinthe à analyser
     * @return le nombre total de murs (horizontaux et verticaux) dans le labyrinthe
     */
    public static int countWalls(Maze maze){
        int count = 0;
        for(boolean[] line : maze.getMurHorizontaux()){
            for(boolean wall : line){
                if (wall){
                    count++;
                }
            }
        }
        for(boolean[] line : maze.getMurVerticaux()){
            for(boolean wall : line){
                if (wall){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Supprime aléatoirement un pourcentage de murs dans un labyrinthe.
     * La méthode supprime à la fois des murs horizontaux et verticaux.
     *
     * @param percentage le pourcentage de murs à supprimer
     * @param maze le labyrinthe dans lequel supprimer les murs
     */
    public static void randomWallRemoval(double percentage, Maze maze){
        int nbWallToRemove = (int)(countWalls(maze)*percentage);
        randomVerticalWallRemoval(nbWallToRemove/2,maze);
        randomHorizontalWallRemoval(nbWallToRemove/2,maze);
    }

    private static final Random RANDOM = new Random();

    /**
     * @param nbWall
     * @param maze
    /**
     * Supprime aléatoirement un nombre spécifié de murs verticaux dans un labyrinthe.
     *
     * @param nbWall le nombre de murs verticaux à supprimer
     * @param maze le labyrinthe dans lequel supprimer les murs
     */
    public static void randomVerticalWallRemoval(int nbWall, Maze maze){

        while(nbWall > 0){
            int i = RANDOM.nextInt(maze.getWidth()-1);
            int j= RANDOM.nextInt(maze.getHeight());
            if(maze.getMurVerticaux()[i][j]){
                maze.getMurVerticaux()[i][j] = false;
                nbWall--;
            }
        }
    }

    /**
     * Supprime aléatoirement un nombre spécifié de murs horizontaux dans un labyrinthe.
     *
     * @param nbWall le nombre de murs horizontaux à supprimer
     * @param maze le labyrinthe dans lequel supprimer les murs
     */
    public static void randomHorizontalWallRemoval(int nbWall,Maze maze){
        while(nbWall > 0){
            int i = RANDOM.nextInt(maze.getWidth());
            int j= RANDOM.nextInt(maze.getHeight()-1);
            if(maze.getMurHorizontaux()[j][i]){
                maze.getMurHorizontaux()[j][i] = false;
                nbWall--;
            }
        }
    }
}
