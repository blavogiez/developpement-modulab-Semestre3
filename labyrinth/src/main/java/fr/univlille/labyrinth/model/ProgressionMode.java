package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.Main;

public class ProgressionMode extends GameMode {
    private Player player;
    public static PlayerProgress defaultProgress;

    @Override
    public void start() {

    }

    public Maze createMaze(Challenge chosenChallenge) {
        return null;


    }

    public void loadPlayer(String name) {

    }

    public Player getPlayer() {
        return player;
    }
}
