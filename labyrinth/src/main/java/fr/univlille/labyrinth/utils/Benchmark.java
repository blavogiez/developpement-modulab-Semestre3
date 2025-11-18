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
    // Pourcentage de murs par défaut
    public static final double WALL_PERCENTAGE = 0.5 ;

    // Nilpotence du programme vis-à-vis des fichiers CSV
    public static final boolean delete = false ;

    // Nombre de tours que fera le benchmark pour calculer la moyenne
    public static final int PRECISION = 3; 
    /** 
     * @param args
     */
    public static void main(String[] args) {
        if(delete) deleteAllCsvFiles();
        Benchmark.csvBench(5, 400, 5);
    }

    /**
     * Pour tous les algorithmes présents, enregistre les données mesurées dans des fichiers aux noms des algorithmes.
     */
    public static void csvBench(int taille, int fin, int ecart) {
        for (MazeAlgorithmFactory algo : MazeAlgorithmFactory.values()) {
            if(algo.name().equals("FUSIONTEST")) {
                Benchmark.csvBench(algo, taille, fin, ecart, false);
                Benchmark.csvBench(algo, taille, fin, ecart, true);
            }
        }
    }

    /**
     * Enregistre les données mesurées dans un fichier au nom de l'algorithme.
     * showDistance détermine si nous comptons l'exécution de la distance entrée / sortie.
     */
    public static void csvBench(MazeAlgorithmFactory algo, int taille, int fin, int ecart, boolean showDistance) {
        if ((fin < 0 || fin < taille) || taille < 2) {
            throw new IndexOutOfBoundsException("Fin supérieure à la taille ou taille impossible");
        }
        if (ecart <= 0) {
            throw new IndexOutOfBoundsException("Écart nul ou décroissant, boucle infinie");
        }

        File dir = new File("res/benchmarks");
        dir.mkdir();
        String distanceText = showDistance ? "_DISTANCE" : "_NO_DISTANCE";
        String filename = "res/benchmarks/" + algo.name() + "_TAILLE_" + taille + "_A_" + fin + "_ECART_" + ecart + distanceText + ".csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("taille,temps(ms)\n");

            while (taille < fin) {
                
                long sommeTemps = 0;
                // executer PRECISION fois pour calculer la moyenne
                for (int i = 0; i < PRECISION; i++) {
                    Timer timer = new Timer();
                    int distance = showDistance ? taille : 0 ;
                    timer.start();
                    new Maze(taille, taille * 2, 0.5, distance, algo.getAlgorithm());
                    timer.stop();
                    sommeTemps += timer.getChrono();
                }
                taille+=ecart ;
                long tempsMoyen = sommeTemps / PRECISION;
                writer.write(taille + "," + tempsMoyen + "\n");
                System.out.println("Algorithme: " + algo.name() + ", Taille: " + taille + ", Temps moyen: " + tempsMoyen + " ms");
            }

            System.out.println("Fichier créé: " + filename);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier: " + e.getMessage());
        }
    }

    /**
     * Supprime tous les fichiers CSV du dossier res/benchmarks
     */
    public static void deleteAllCsvFiles() {
        File dir = new File("res/benchmarks");
        if (dir.exists()) {
            for (File file : dir.listFiles((d, name) -> name.endsWith(".csv"))) {
                file.delete();
            }
        }
    }
}
