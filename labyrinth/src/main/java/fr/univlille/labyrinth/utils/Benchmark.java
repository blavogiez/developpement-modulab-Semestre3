package fr.univlille.labyrinth.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.maze.Maze;

/**
 * Benchmark est la classe utilitaire servant à mesurer le temps d'exécution des différents algorithmes, 
 * avec un retour par l'affichage en sortie standard ou en sortie CSV.
 * Les données sorties au format CSV sont ensuite modélisées par le script Python correspondant.
 *
 * @author 
 * Antonin, Angel, Baptiste, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Benchmark {
    // Nombre de tours que fera le benchmark pour calculer la moyenne
    public static final int PRECISION = 100; 
    public static void main(String[] args) {
        Benchmark.csvBench(10, 500, 5);
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
        if ((fin < 0 || fin < taille) || taille < 2) {
            throw new IndexOutOfBoundsException("Fin supérieure à la taille ou taille impossible");
        }
        if (ecart <= 0) {
            throw new IndexOutOfBoundsException("Écart nul ou décroissant, boucle infinie");
        }

        File dir = new File("res/benchmarks");
        dir.mkdir();
        String filename = "res/benchmarks/" + algo.name() + "_TAILLE_" + taille + "_A_" + fin + "_ECART_" + ecart + ".csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("taille,temps(ms)\n");

            while (taille < fin) {
                taille += ecart;
                long sommeTemps = 0;

                // executer PRECISION fois pour calculer la moyenne
                for (int i = 0; i < PRECISION; i++) {
                    Timer timer = new Timer();
                    timer.start();
                    //new Maze(algo, taille, taille * 2, 0.5);
                    new Maze(taille, taille * 2, taille);
                    timer.stop();
                    sommeTemps += timer.getChrono();
                }

                long tempsMoyen = sommeTemps / PRECISION;
                writer.write(taille + "," + tempsMoyen + "\n");
                System.out.println("Algorithme: " + algo.name() + ", Taille: " + taille + ", Temps moyen: " + tempsMoyen + " ms");
            }

            System.out.println("Fichier créé: " + filename);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier: " + e.getMessage());
        }
    }
}
