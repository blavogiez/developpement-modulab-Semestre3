package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.model.gamemode.GameMode;

public interface VictoryObserver<T extends GameMode> {
    public void update(T type);

    public void onVictory();
}
