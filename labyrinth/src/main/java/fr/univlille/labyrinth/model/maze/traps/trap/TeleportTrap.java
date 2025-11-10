package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.traps.TrapSetup;

public class TeleportTrap extends Trap {
    @Override
    public void onUse(Position position, Position oldPosition, ObservableMaze maze) {
        maze.setPlayerPosition(TrapSetup.getRandomCell(maze));
    }

    @Override
    public String name() {
        return "TRAP_TELEPORT";
    }
}
