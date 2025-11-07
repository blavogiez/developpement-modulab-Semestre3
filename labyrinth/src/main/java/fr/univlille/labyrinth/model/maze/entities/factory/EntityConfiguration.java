package fr.univlille.labyrinth.model.maze.entities.factory;

import fr.univlille.labyrinth.model.maze.entities.EntityType;

/*
 * Paramètres nécessaires à la configuration d'une entité
 * à éventuellement étendre pour pouvoir gérer plusieurs mêmes entités avec mouvements différents mais on verra si besoin
 */
public record EntityConfiguration(
    EntityType type,
    int quantity,
    String moveBehaviorName
) {}
