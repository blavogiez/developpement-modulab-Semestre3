package fr.univlille.labyrinth.model.maze.traps;


import fr.univlille.labyrinth.model.maze.traps.trap.Trap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrapFactoryTest {

    @Test
    public void onCreateTrapTest(){
        Trap trap = TrapFactory.NONE.generateTrap();
        Trap trap2 = TrapFactory.NONE.generateTrap();

        assertNotSame(trap, trap2);
    }
}
