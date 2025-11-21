package fr.univlille.labyrinth.model.gamemode;

import fr.univlille.labyrinth.model.gamemode.config.FreeModeConfig;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.FreeModeVictoryHandler;
import fr.univlille.labyrinth.model.gamemode.victory.VictoryHandler;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

/**
 * Mode de jeu libre où le gagnant peut être différentes personnes, contrairement au mode progression.
 * Dans ce mode, les joueurs peuvent jouer librement sans contrainte de progression.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class FreeMode extends GameMode {
    private PlayerEntity lastWinner;

    /**
     * Constructeur principal avec injection complète des dépendances (respecte le DIP)
     * @param mazeManager le gestionnaire de labyrinthe
     * @param victoryHandler le gestionnaire de victoire
     * @param config la configuration du mode libre
     */
    public FreeMode(MazeManager mazeManager, VictoryHandler victoryHandler, FreeModeConfig config) {
        super(mazeManager, victoryHandler, config);
    }

    /**
     * Constructeur de compatibilité avec création des dépendances
     * @param config la configuration du mode libre
     */
    public FreeMode(FreeModeConfig config) {
        this(new MazeManager(), new FreeModeVictoryHandler(), config);
    }

    /**
     * Constructeur par défaut avec configuration par défaut
     */
    public FreeMode() {
        this(new FreeModeConfig());
    }

    /**
     * Crée le labyrinthe pour le mode libre en utilisant la configuration actuelle.
     */
    public void createMaze() {
        getMazeManager().createMaze(config);
    }

    /**
     * Gère la victoire d'un joueur dans le mode libre.
     *
     * @param winner Le joueur gagnant
     */
    @Override
    protected void handleVictory(PlayerEntity winner) {
        this.lastWinner = winner;
        super.handleVictory(winner);
    }

    /**
     * Retourne le dernier joueur gagnant dans le mode libre.
     *
     * @return Le joueur gagnant, ou null s'il n'y a pas de gagnant
     */
    @Override
    public PlayerEntity getWinner() {
        return lastWinner;
    }

    /**
     * Retourne une représentation textuelle du mode libre avec ses informations.
     *
     * @return Une chaîne de caractères décrivant le mode libre
     */
    @Override
    public String toString() {
        String info = "Labyrinthe d'algorithme " + config.getAlgorithm().getClass().getSimpleName() + " ; \n";
        info += "Dimensions : " + config.getWidth() + "x" + config.getHeight();
        if (config.isPerfectAlgorithm()) {
            int distance=getCurrentMaze()!=null ? getCurrentMaze().getDistanceBetweenEntryAndExit() : config.getDistanceBetweenEntryAndExit() ;
            info += ", Distance entrée / sortie : " + distance;
        } else {
            info += ", Pourcentage : " + (int) (config.getWallPercentage() * 100) + "%";
        }
        return info;
    }
}
