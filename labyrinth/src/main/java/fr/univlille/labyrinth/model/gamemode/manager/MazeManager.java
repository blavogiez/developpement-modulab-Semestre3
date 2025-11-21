package fr.univlille.labyrinth.model.gamemode.manager;

import fr.univlille.labyrinth.model.gamemode.config.GameConfig;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.WallRemover;

/**
 * Gestionnaire de labyrinthe permettant de créer, configurer et manipuler les labyrinthes dans le jeu.
 * Cette classe contrôle la création des labyrinthes selon différentes configurations.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class MazeManager {
    private ObservableMaze currentMaze;

    /**
     * Crée un labyrinthe selon le type d'algorithme (Spécifier la distance si c'est un parfait, sinon spécifier le pourcentage de murs)
     *
     * @param config La configuration du jeu utilisée pour créer le labyrinthe
     */
    public void createMaze(GameConfig config) {
        if (config.isPerfectAlgorithm()) {
            this.currentMaze = new ObservableMaze(
                config.getWidth(),
                config.getHeight(),
                config.getDistanceBetweenEntryAndExit(),
                config.getEntitiesConfiguration(),
                    config.getAlgorithm(),
                    config.getTrapsConfiguration()
            );
        } else {
            this.currentMaze = new ObservableMaze(
                config.getWidth(),
                config.getHeight(),
                config.getWallPercentage(),
                config.getEntitiesConfiguration(),
                    config.getAlgorithm(),
                    config.getTrapsConfiguration()
            );
        }
    }

    /**
     * Retourne le labyrinthe actuellement géré.
     *
     * @return Le labyrinthe actuel ou null s'il n'y en a pas
     */
    public ObservableMaze getCurrentMaze() {
        return currentMaze;
    }

    /**
     * Définit le labyrinthe actuellement géré.
     *
     * @param maze Le nouveau labyrinthe à gérer
     */
    public void setCurrentMaze(ObservableMaze maze) {
        this.currentMaze = maze;
    }

    /**
     * Vérifie si un labyrinthe est actuellement géré.
     *
     * @return true si un labyrinthe est présent, false sinon
     */
    public boolean hasMaze() {
        return currentMaze != null;
    }

    /**
     * Vérifie si les dimensions spécifiées sont valides pour un labyrinthe.
     * Les dimensions sont correctes si la largeur et la hauteur sont supérieures ou égales à 1.
     *
     * @param width La largeur à vérifier
     * @param height La hauteur à vérifier
     * @return true si les dimensions sont correctes, false sinon
     */
    public static boolean areDimensionsCorrect(int width, int height) {
        return width >= 1 && height >= 1 ;
    }
}
