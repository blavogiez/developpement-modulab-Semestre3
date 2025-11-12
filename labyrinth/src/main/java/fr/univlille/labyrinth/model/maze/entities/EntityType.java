package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

public enum EntityType {

    PLAYER{
        @Override
        public Entity create(ObservableMaze maze, MoveBehavior moveBehavior) {
            return new PlayerEntity(PlayerEntity.getInitialPosition(maze), moveBehavior);
        }

        @Override
        public Entity create(ObservableMaze maze) {
            return new PlayerEntity(PlayerEntity.getInitialPosition(maze));
        }
    },

    EXIT {
        @Override
        public Entity create(ObservableMaze maze, MoveBehavior moveBehavior) {
            return new ExitEntity(ExitEntity.getInitialPosition(maze), moveBehavior);
        }

        @Override
        public Entity create(ObservableMaze maze) {
            return new ExitEntity(ExitEntity.getInitialPosition(maze));
        }
    },

    MONSTER {
        @Override
        public Entity create(ObservableMaze maze, MoveBehavior moveBehavior) {
            return new MonsterEntity(MonsterEntity.getInitialPosition(maze), moveBehavior);
        }

        @Override
        public Entity create(ObservableMaze maze) {
            return new MonsterEntity(MonsterEntity.getInitialPosition(maze));
        }
    };

    public abstract Entity create(ObservableMaze maze);
    public abstract Entity create(ObservableMaze maze, MoveBehavior moveBehavior);
}
