package fr.univlille.labyrinth.utils;

import fr.univlille.labyrinth.model.*;
import java.io.*;

// Utilitaire pour charger la progression par défaut depuis un fichier CSV
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
                if (parts.length != 7) {
                    continue; // Ignorer les lignes invalides
                }

                // Parser les données du challenge
                int levelNumber = Integer.parseInt(parts[0]);
                int challengeIndex = Integer.parseInt(parts[1]);
                String difficulty = parts[2];
                int width = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
                double wallPercentage = Double.parseDouble(parts[5]);
                int distanceBetweenEntryAndExit = Integer.parseInt(parts[6]);

                // Créer le challenge et le placer dans le bon niveau
                Challenge challenge = new Challenge(
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
