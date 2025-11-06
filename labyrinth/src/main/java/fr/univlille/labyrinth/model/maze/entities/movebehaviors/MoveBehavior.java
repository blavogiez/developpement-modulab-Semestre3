package fr.univlille.labyrinth.model.maze.entities.movebehaviors;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.entities.Entity;

/*
 * Comportement qu'il se produit à chaque mouvement du labyrinthe.
 */
public interface MoveBehavior {
    void move(Entity entity, Maze maze);
}
