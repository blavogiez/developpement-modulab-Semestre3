package fr.univlille.labyrinth.model.gamemode;

import fr.univlille.labyrinth.model.gamemode.config.FreeModeConfig;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.FreeModeVictoryHandler;
import fr.univlille.labyrinth.model.gamemode.victory.VictoryHandler;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

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

    public void createMaze() {
        getMazeManager().createMaze(config);
    }

    @Override
    protected void handleVictory(PlayerEntity winner) {
        this.lastWinner = winner;
        super.handleVictory(winner);
    }

    @Override
    public PlayerEntity getWinner() {
        return lastWinner;
    }

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
