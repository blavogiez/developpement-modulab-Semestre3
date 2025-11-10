package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

public class TeleportExitTrap extends Trap {
    @Override
    public void onUse(Position position, Position oldPosition, ObservableMaze maze) {

    }

    @Override
    public String name() {
        return "TRAP_TELEPORT_EXIT";
    }
}
