package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

public class FakeTrap extends Trap {

    @Override
    public void onUse(Position position, Position oldPosition, ObservableMaze maze) {
        revealTrap(position,maze.getTrapManager().getTraps());
    }

    @Override
    public String name() {
        return "TRAP_FAKE_EXIT";
    }
}
