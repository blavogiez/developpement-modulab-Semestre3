package fr.univlille.labyrinth.model.gamemode.victory;

import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerDatabase;
import fr.univlille.labyrinth.model.gamemode.Timer;

public class ProgressionModeVictoryHandler implements VictoryHandler {
    private Player player;
    private Challenge challenge;
    private Timer timer;

    public ProgressionModeVictoryHandler(Player player, Challenge challenge, Timer timer) {
        this.player = player;
        this.challenge = challenge;
        this.timer = timer;
    }

    /** 
     * @param winner
     */
    @Override
    public void handleVictory(PlayerEntity winner) {
        long completionTime = timer != null ? timer.getChrono() : 0;
        player.getProgress().markChallengeCompleted(challenge, completionTime);
        PlayerDatabase.savePlayer(player);
    }

    @Override
    public void handleLoose() {
        PlayerDatabase.savePlayer(player);
    }

    /** 
     * @param timer
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /** 
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }
}
