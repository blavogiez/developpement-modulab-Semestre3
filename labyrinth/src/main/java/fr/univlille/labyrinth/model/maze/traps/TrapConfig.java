package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.controller.freemode.MazeThreatConfiguration;

public class TrapConfig implements MazeThreatConfiguration {
    TrapFactory type;
    int quantity;

    public TrapConfig(TrapFactory type, int quantity) {
        this.type=type;
        this.quantity=quantity;
    }

    @Override
    public String type() {
        return type.name();
    }

    @Override
    public int quantity() {
        return quantity;
    }

    public TrapFactory getType() {
        return type;
    }
}
