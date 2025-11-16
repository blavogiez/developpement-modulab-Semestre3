package fr.univlille.labyrinth.view.labyrinth.animation;

import java.util.Map;

import fr.univlille.labyrinth.model.maze.ObservableMaze;

public interface AnimatableView {
    ObservableMaze getCurrentMaze();
    Map<Integer, Double> getPlayerXMap();
    Map<Integer, Double> getPlayerYMap();
    void draw();
}
