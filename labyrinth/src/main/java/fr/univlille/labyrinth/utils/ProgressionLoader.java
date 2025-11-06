package fr.univlille.labyrinth.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Level;
import fr.univlille.labyrinth.model.save.PlayerProgress;
import fr.univlille.labyrinth.model.save.ViewType;
import fr.univlille.labyrinth.model.save.score.ScoreCalculatorFactory;

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

        int maxLevel = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    int levelNumber = Integer.parseInt(parts[3]);
                    if (levelNumber > maxLevel) maxLevel = levelNumber;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la détection des niveaux: " + e.getMessage(), e);
        }

        Level[] levels = new Level[maxLevel];
        for (int i = 0; i < maxLevel; i++) {
            levels[i] = new Level(i + 1);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length != 10) continue;

                ScoreCalculatorFactory scoreFactory = ScoreCalculatorFactory.valueOf(parts[0]);
                MazeAlgorithm algorithm = MazeAlgorithmFactory.valueOf(parts[1]).getAlgorithm();
                ViewType viewType = ViewType.valueOf(parts[2]);
                int levelNumber = Integer.parseInt(parts[3]);
                int challengeIndex = Integer.parseInt(parts[4]);
                String difficulty = parts[5];
                int width = Integer.parseInt(parts[6]);
                int height = Integer.parseInt(parts[7]);
                double wallPercentage = Double.parseDouble(parts[8]);
                int distanceBetweenEntryAndExit = Integer.parseInt(parts[9]);

                Challenge challenge = new Challenge(
                    algorithm,
                    viewType,
                    difficulty,
                    width,
                    height,
                    wallPercentage,
                    distanceBetweenEntryAndExit,
                    scoreFactory.create()
                );
                levels[levelNumber - 1].getChallenges()[challengeIndex] = challenge;
            }

            return new PlayerProgress(levels);

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement de la progression par défaut: " + e.getMessage(), e);
        }
    }
}
