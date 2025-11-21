package fr.univlille.labyrinth.model.gamemode;


import fr.univlille.labyrinth.model.gamemode.config.ProgressionModeConfig;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.ProgressionModeVictoryHandler;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerProgress;
import fr.univlille.labyrinth.model.save.ProgressionLoader;

/**
 * Mode de jeu progression où le joueur progresse à travers différents défis.
 * Ce mode gère la progression du joueur à travers une série de labyrinthes avec des objectifs spécifiques.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class ProgressionMode extends GameMode {
    private Challenge challenge;
    private ProgressionModeVictoryHandler victoryHandler;
    public static PlayerProgress defaultProgress;

    // execute at start to init default progress
    static {
        ProgressionMode.initDefaultProgress();
    }

    /**
     * Constructeur principal avec injection complète des dépendances (respecte le DIP)
     *
     * @param mazeManager le gestionnaire de labyrinthe
     * @param victoryHandler le gestionnaire de victoire
     * @param player le joueur
     * @param challenge le défi
     */
    public ProgressionMode(MazeManager mazeManager, ProgressionModeVictoryHandler victoryHandler, Player player, Challenge challenge) {
        super(mazeManager, victoryHandler, new ProgressionModeConfig(challenge));
        this.challenge = challenge;
        this.victoryHandler = victoryHandler;
    }

    /**
     * Constructeur de compatibilité avec création des dépendances
     *
     * @param player le joueur
     * @param challenge le défi
     */
    public ProgressionMode(Player player, Challenge challenge) {
        this(new MazeManager(), new ProgressionModeVictoryHandler(player, challenge, null), player, challenge);
    }

    /**
     * Initialise l'objet defaultProgress à un objet PlayerProgress basé sur le fichier "default_progression.csv", par la classe utilitaire ProgressionLoader.
     */
    public static void initDefaultProgress() {
        ProgressionMode.defaultProgress = ProgressionLoader.loadDefaultProgress();
    }


    /**
     * Crée le labyrinthe pour le mode progression en utilisant la configuration du défi.
     */
    public void createMaze() {
        getMazeManager().createMaze(config);
    }

    /**
     * Retourne le joueur associé à ce mode progression.
     *
     * @return Le joueur, ou null s'il n'y a pas de gestionnaire de victoire
     */
    public Player getPlayer() {
        return victoryHandler != null ? victoryHandler.getPlayer() : null;
    }

    /**
     * Retourne le défi associé à ce mode progression.
     *
     * @return Le défi en cours
     */
    public Challenge getChallenge() {
        return challenge;
    }

    /**
     * Définit le chronomètre pour le mode progression.
     *
     * @param chrono Le chronomètre à utiliser
     */
    public void setChronometre(Timer chrono) {
        if (victoryHandler != null) {
            victoryHandler.setTimer(chrono);
        }
    }

    /**
     * Retourne le joueur gagnant dans le mode progression.
     * Dans ce mode, il n'y a pas de gagnant spécifique jusqu'à la victoire.
     *
     * @return null, car le gagnant n'est pas défini dans ce mode
     */
    @Override
    public PlayerEntity getWinner() {
        return null;
    }

    /**
     * Retourne une représentation textuelle du mode progression avec ses informations.
     *
     * @return Une chaîne de caractères décrivant le mode progression
     */
    @Override
    public String toString() {
        String info = "Dimensions : " + config.getWidth() + "x" + config.getHeight();
        info += ", Pourcentage : " + (int) (config.getWallPercentage() * 100) + "%";
        int distance = getCurrentMaze()!=null ? getCurrentMaze().getDistanceBetweenEntryAndExit() : config.getDistanceBetweenEntryAndExit();
        info += ", Distance entrée/sortie : " + distance;
        return info;
    }
}
