package fr.univlille.labyrinth.model.maze.entities.factory;

import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MovingStepBehavior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveBehaviorFactoryTest {

    @Test
    void shouldCreatePlayerMoveBehavior() {
        MoveBehavior behavior = MoveBehaviorFactory.create("PLAYER");

        assertNotNull(behavior);
        assertInstanceOf(PlayerMoveBehavior.class, behavior);
    }

    @Test
    void shouldCreateMovingStepBehavior() {
        MoveBehavior behavior = MoveBehaviorFactory.create("MOVING");

        assertNotNull(behavior);
        assertInstanceOf(MovingStepBehavior.class, behavior);
    }

    @Test
    void shouldReturnNullForDefault() {
        MoveBehavior behavior = MoveBehaviorFactory.create("DEFAULT");

        assertNull(behavior);
    }

    @Test
    void shouldReturnNullForNull() {
        MoveBehavior behavior = MoveBehaviorFactory.create(null);

        assertNull(behavior);
    }

    @Test
    void shouldHandleCaseInsensitive() {
        MoveBehavior behavior1 = MoveBehaviorFactory.create("player");
        MoveBehavior behavior2 = MoveBehaviorFactory.create("moving");

        assertInstanceOf(PlayerMoveBehavior.class, behavior1);
        assertInstanceOf(MovingStepBehavior.class, behavior2);
    }

    @Test
    void shouldThrowExceptionForUnknownBehavior() {
        assertThrows(IllegalArgumentException.class, () -> {
            MoveBehaviorFactory.create("UNKNOWN");
        });
    }

    @Test
    void shouldCreateNewInstancesEachTime() {
        MoveBehavior behavior1 = MoveBehaviorFactory.create("PLAYER");
        MoveBehavior behavior2 = MoveBehaviorFactory.create("PLAYER");

        assertNotSame(behavior1, behavior2);
    }
}
