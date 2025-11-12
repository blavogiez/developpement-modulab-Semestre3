package fr.univlille.labyrinth.model.maze.entities.movebehaviors;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;

/*
 * Comportement qu'il se produit à chaque mouvement du joueur dans le labyrinthe (action reçue).
 */
public interface MoveBehavior {
    void move(Entity entity, Direction direction, ObservableMaze maze);
}
