package fr.univlille.labyrinth.controller.freemode;

import fr.univlille.labyrinth.model.maze.MazeThreats;

public interface MazeThreatConfiguration {
    public String type();
    public int quantity();
    public MazeThreats getType();
}
