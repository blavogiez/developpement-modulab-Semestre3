package fr.univlille.labyrinth.model.gamemode.victory;

import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

public interface VictoryHandler {
    void handleVictory(PlayerEntity winner);
    void handleLoose();
}
