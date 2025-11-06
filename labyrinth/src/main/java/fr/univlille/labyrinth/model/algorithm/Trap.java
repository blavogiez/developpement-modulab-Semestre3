package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.view.GameColors;

public enum Trap {
    PATH,
    RANDOM,
    USED(GameColors.TRAP_USED),
    TELEPORTER(GameColors.TELEPORTER_TRAP),
    FAKE(GameColors.EXIT),
    PUSH,
    STUN;

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
