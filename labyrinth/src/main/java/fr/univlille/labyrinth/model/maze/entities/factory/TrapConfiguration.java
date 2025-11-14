package fr.univlille.labyrinth.model.maze.entities.factory;

import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;

/*
 * Paramètres nécessaires à la configuration d'une entité
 * à éventuellement étendre pour pouvoir gérer plusieurs mêmes entités avec mouvements différents mais on verra si besoin
 */
public record TrapConfiguration(
        TrapFactory trap,
        int quantity
) {}
