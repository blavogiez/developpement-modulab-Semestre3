package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.maze.traps.trap.*;

public enum TrapFactory {
    NONE,
    RANDOM,
    USED,
    TELEPORTER(new TeleportTrap()),
    FAKE(new FakeTrap()),
    PUSH(new PushTrap()),
    STUN(new StunTrap()),
    REGENERATE_WALL(new HideWallTrap()),
    TELEPORT_EXIT(new TeleportExitTrap()),
    LAVA(new LavaTrap());

    private final Trap trap;

    TrapFactory(Trap trap){
        this.trap=trap;
    }

    TrapFactory(){
        this.trap=new NoneTrap();
    }

    public Trap getTrap() {
        return trap;
    }
}
