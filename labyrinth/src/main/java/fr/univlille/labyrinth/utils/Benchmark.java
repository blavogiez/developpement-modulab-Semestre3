package fr.univlille.labyrinth.utils;

import fr.univlille.labyrinth.model.algorithm.MazeSizeException;
import fr.univlille.labyrinth.model.maze.Maze;

/**
 * Benchmark est la classe utilitaire servant à mesurer le temps d'éxecution des différents algorithmes, avec un retour par l'affichage en sortie standard ou en sortie CSV.
 * Les données sorties au format CSV sont ensuites modélisées par le script "..." python
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Benchmark {
    public static void main(String[] args) {
        Benchmark.visualBench(10, 500, 5);
    }

    public static void visualBench(int taille, int fin) {
        Benchmark.visualBench(taille, fin, 1);
    }

    public static void visualBench(int taille, int fin, int ecart) {
        if((fin<0 || fin < taille) || taille < 2) throw new MazeSizeException("Fin supérieure à la taille");

        while (taille<fin){
            taille+=ecart;
            Timer timer = new Timer() ;
            timer.start();
            new Maze(taille,taille*2,0.5);
            timer.stop();
            System.out.print("Labyrinthe de taille " + taille + "*" + taille*2 + ", ");
            System.out.println(timer);
        }
    }   

    public static void csvBench(int taille, int fin, int ecart) {
        if((fin<0 || fin < taille) || taille < 2) throw new MazeSizeException("Fin supérieure à la taille");

        while (taille<fin){
            taille+=ecart;
            Timer timer = new Timer() ;
            timer.start();
            new Maze(taille,taille*2,0.5);
            timer.stop();
            
            // ecrire valeurs csv (plus tard)...
        }
    }
}
