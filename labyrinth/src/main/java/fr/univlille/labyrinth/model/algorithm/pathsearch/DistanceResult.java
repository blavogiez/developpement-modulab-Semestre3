package fr.univlille.labyrinth.model.algorithm.pathsearch;

import fr.univlille.labyrinth.model.maze.Position;

import java.util.List;

/*
 * Record simple qui retourne la liste de toutes les positions demandées à la distance demandée
 * actualDistance corrige la distance si elle était impossible (supérieure à la distance maximale) et va changer le parametre distance dans le labyrinthe
 * Donc si on met une distance de 300 000 et que le maximum est à 140, le labyrinthe corrige et c'est bien la distance effective de 140 qui sera affichée lors du toString du labyrinthe.
 */
public record DistanceResult(List<Position> positions, int actualDistance) {}
