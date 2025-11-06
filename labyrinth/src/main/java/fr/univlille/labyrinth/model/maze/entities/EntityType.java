package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.Position;

/**
 * Enum factory pour gérer les types d'entités dans le labyrinthe
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public enum EntityType {

    /**
     * Type d'entité : Joueur
     */
    PLAYER {
        @Override
        public Entity create(Position position) {
            return new PlayerEntity(position);
        }
    };
    
    /**
     * Crée une instance de l'entité correspondante
     * Pattern Factory Method implémenté dans l'enum
     *
     * @return Entity l'instance créée
     */
    public abstract Entity create(Position position);
}
