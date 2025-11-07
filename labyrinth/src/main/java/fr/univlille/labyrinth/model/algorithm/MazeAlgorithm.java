package fr.univlille.labyrinth.model.algorithm;

import java.io.Serializable;

import fr.univlille.labyrinth.model.maze.Maze;

/*
 * Interface pour les algorithmes
 * Permet le DIP
 * 
 * Serializable car attribut d'un défi.
 */
public interface MazeAlgorithm extends Serializable{
    public abstract void generateMaze(Maze maze);
}
