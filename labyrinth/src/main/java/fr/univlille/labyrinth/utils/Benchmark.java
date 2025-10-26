package fr.univlille.labyrinth.utils;

import java.io.IOException;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
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
        //Benchmark.visualBench(10, 500, 5);
        //Benchmark.csvBench(10, 500, 5);
        //Benchmark.csvBench(MazeAlgorithmFactory)
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
            new Maze(MazeAlgorithmFactory.STANDARDLARGEUR, taille,taille*2,0.5);
            timer.stop();
            System.out.print("Labyrinthe de taille " + taille + "*" + taille*2 + ", ");
            System.out.println(timer);
        }
    }   

    /**
     * Pour tous les algorithmes présents, enregistre les données mesurées dans des fichiers aux noms des algorithmes.
     */
    public static void csvBench(int taille, int fin, int ecart) {
        for (MazeAlgorithmFactory algo : MazeAlgorithmFactory.values()) {
            Benchmark.csvBench(algo, taille, fin, ecart);
        }
    }

    /**
     * Enregistre les données mesurées dans un fichier au nom de l'algorithme.
     */
    public static void csvBench(MazeAlgorithmFactory algo, int taille, int fin, int ecart) {
        if((fin<0 || fin < taille) || taille < 2) throw new MazeSizeException("Fin supérieure à la taille");

        while (taille<fin){
            taille+=ecart;
            Timer timer = new Timer() ;
            timer.start();
            new Maze(algo, taille,taille*2,0.5);
            timer.stop();
            
            // ecrire valeurs csv (plus tard)...
        }
    }
}
