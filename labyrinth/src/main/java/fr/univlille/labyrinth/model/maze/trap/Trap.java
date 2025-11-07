package fr.univlille.labyrinth.model.maze.trap;

import fr.univlille.labyrinth.view.GameColors;

public enum Trap {
    PATH,
    RANDOM,
    USED(GameColors.TRAP_USED),
    TELEPORTER(GameColors.TELEPORTER_TRAP),
    FAKE(GameColors.EXIT),
    PUSH,
    STUN,
    REGENERATE_WALL,
    TURN_WALL,
    MONSTER_SPAWN,
    TELEPORT_EXIT,
    LAVA;


    Trap(GameColors color) {
        this.color = color;
    }

    Trap() {
        this.color = GameColors.PATH;
    }
    private GameColors color;

    public GameColors getColor() {
        return color;
    }
}
