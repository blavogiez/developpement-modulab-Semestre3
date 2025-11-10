package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.maze.traps.trap.*;

import java.util.function.Function;
import java.util.function.Supplier;

public enum TrapFactory {
    NONE(NoneTrap::new),
    RANDOM_TRAP(RandomTrap::new),
    USED(UsedTrap::new),
    TELEPORTER_TRAP(TeleportTrap::new),
    FAKE_EXIT_TRAP(FakeTrap::new),
    PUSH_TRAP(PushTrap::new),
    STUN_TRAP(StunTrap::new),
    HIDE_WALL_TRAP(HideWallTrap::new),
    TELEPORT_EXIT_TRAP(TeleportExitTrap::new),
    LAVA_TRAP(LavaTrap::new);

    private Supplier<Trap> trap;

    TrapFactory(Supplier<Trap> trap){
        this.trap=trap;
    }

    public Trap generateTrap(){
        return trap.get();
    }



}
