package fr.univlille.labyrinth.model.gamemode;

import fr.univlille.labyrinth.model.gamemode.config.FreeModeConfig;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.FreeModeVictoryHandler;

public class FreeMode extends GameMode {
    private FreeModeConfig config;

    public FreeMode(FreeModeConfig config) {
        super(new MazeManager(), new FreeModeVictoryHandler());
        this.config = config;
    }

    public FreeMode() {
        this(new FreeModeConfig());
    }

    public void createMaze() {
        getMazeManager().createMaze(config);
    }

    public FreeModeConfig getConfig() {
        return config;
    }

    public String toString() {
        String info = "Labyrinthe d'algorithme " + config.getAlgorithmFactory().name() + " ; \n";
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
