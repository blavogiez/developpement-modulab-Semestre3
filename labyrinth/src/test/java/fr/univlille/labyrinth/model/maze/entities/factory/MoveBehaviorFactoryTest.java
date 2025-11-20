package fr.univlille.labyrinth.model.maze.entities.factory;

import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MovingStepBehavior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveBehaviorFactoryTest {

    @Test
    void should_create_player_move_behavior() {
        MoveBehavior behavior = MoveBehaviorFactory.create("PLAYER");

        assertNotNull(behavior);
        assertInstanceOf(PlayerMoveBehavior.class, behavior);
    }

    @Test
    void should_create_moving_step_behavior() {
        MoveBehavior behavior = MoveBehaviorFactory.create("MOVING");

        assertNotNull(behavior);
        assertInstanceOf(MovingStepBehavior.class, behavior);
    }

    @Test
    void should_return_null_for_default() {
        MoveBehavior behavior = MoveBehaviorFactory.create("DEFAULT");

        assertNull(behavior);
    }

    @Test
    void should_return_null_for_null() {

        MoveBehavior behavior = MoveBehaviorFactory.create(null);
        assertNull(behavior);
    }

    @Test
    void should_handle_case_insensitive() {
        MoveBehavior behavior1 = MoveBehaviorFactory.create("player");
        MoveBehavior behavior2 = MoveBehaviorFactory.create("moving");

        assertInstanceOf(PlayerMoveBehavior.class, behavior1);
        assertInstanceOf(MovingStepBehavior.class, behavior2);
    }

    @Test
    void should_throw_exception_for_unknown_behavior() {
        assertThrows(IllegalArgumentException.class, () -> {
            MoveBehaviorFactory.create("UNKNOWN");
        });
    }

    @Test
    void should_create_new_instances_each_time() {
        MoveBehavior behavior1 = MoveBehaviorFactory.create("PLAYER");
        MoveBehavior behavior2 = MoveBehaviorFactory.create("PLAYER");

        assertNotSame(behavior1, behavior2);
    }
}
