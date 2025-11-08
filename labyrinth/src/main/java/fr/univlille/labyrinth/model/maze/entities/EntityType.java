package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

public enum EntityType {

    PLAYER {
        @Override
        public Entity create(Position position) {
            return new PlayerEntity(position);
        }

        @Override
        public Entity create(Position position, MoveBehavior moveBehavior) {
            return new PlayerEntity(position, moveBehavior);
        }
    },
    
    EXIT {
        @Override
        public Entity create(Position position) {
            return new ExitEntity(position);
        }

        @Override
        public Entity create(Position position, MoveBehavior moveBehavior) {
            return new ExitEntity(position, moveBehavior);
        }
    },

    MONSTER {
        @Override
        public Entity create(Position position) {
            return new MonsterEntity(position);
        }

        @Override
        public Entity create(Position position, MoveBehavior moveBehavior) {
            return new MonsterEntity(position, moveBehavior);
        }
    };

    
    public abstract Entity create(Position position);
    public abstract Entity create(Position position, MoveBehavior moveBehavior);
}
