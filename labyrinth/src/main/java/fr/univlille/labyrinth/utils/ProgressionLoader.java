package fr.univlille.labyrinth.utils;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Level;
import fr.univlille.labyrinth.model.save.PlayerProgress;

import java.io.*;

/**
 * Classe utilitaire pour charger la progression.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */

public class ProgressionLoader {
    private static final String DEFAULT_PROGRESSION_FILE = "res/default_progression.csv";

    /**
     * Charge la progression par défaut depuis le fichier CSV
     *
     * @return PlayerProgress la progression par défaut
     */
    public static PlayerProgress loadDefaultProgress() {
        File file = new File(DEFAULT_PROGRESSION_FILE);
        if (!file.exists()) {
            throw new RuntimeException("Le fichier de progression par défaut n'existe pas: " + DEFAULT_PROGRESSION_FILE);
        }

        // Créer les 3 niveaux
        Level[] levels = new Level[3];
        levels[0] = new Level(1);
        levels[1] = new Level(2);
        levels[2] = new Level(3);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Ignorer la ligne d'en-tête
            reader.readLine();

            // Lire chaque ligne du CSV
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 8) {
                    continue; // Ignorer les lignes invalides
                }

                // Parser les données du challenge
                MazeAlgorithmFactory algorithm = MazeAlgorithmFactory.valueOf(parts[0]);
                int levelNumber = Integer.parseInt(parts[1]);
                int challengeIndex = Integer.parseInt(parts[2]);
                String difficulty = parts[3];
                int width = Integer.parseInt(parts[4]);
                int height = Integer.parseInt(parts[5]);
                double wallPercentage = Double.parseDouble(parts[6]);
                int distanceBetweenEntryAndExit = Integer.parseInt(parts[7]);

                // Créer le challenge et le placer dans le bon niveau
                Challenge challenge = new Challenge(
                    algorithm,
                    difficulty,
                    width,
                    height,
                    wallPercentage,
                    distanceBetweenEntryAndExit
                );
                levels[levelNumber - 1].getChallenges()[challengeIndex] = challenge;
            }

            return new PlayerProgress(levels);

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement de la progression par défaut: " + e.getMessage(), e);
        }
    }
}
