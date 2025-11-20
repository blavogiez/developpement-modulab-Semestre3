package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.exceptions.UnknownTrapException;
import fr.univlille.labyrinth.model.maze.traps.trap.*;

import java.util.function.Supplier;

public enum TrapFactory {
    NONE(NoneTrap::new,""),
    RANDOM_TRAP(RandomTrap::new,"R"),
    USED(UsedTrap::new,"U"),
    TELEPORTER_TRAP(TeleportTrap::new,"T"),
    FAKE_EXIT_TRAP(FakeTrap::new,"F"),
    PUSH_TRAP(PushTrap::new,"P"),
    GENERATE_TRAP(GenerateTrap::new,"G"),
    STUN_TRAP(StunTrap::new,"S"),
    TELEPORT_EXIT_TRAP(TeleportExitTrap::new,"TE"),
    LAVA_TRAP(LavaTrap::new,"L");

    private final Supplier<Trap> trap;
    private final String compactedName;

    public static TrapFactory compactedValueOf(String compactedName){
        for (TrapFactory factory : values()){
            if (compactedName.equals(factory.getCompactedName())) {
                return factory;
            }
        }
        throw new UnknownTrapException(compactedName);
    }

    public String getCompactedName(){
        return this.compactedName;
    }

    TrapFactory(Supplier<Trap> trap, String compactedName){
        this.trap=trap;
        this.compactedName=compactedName;
    }

    public Trap generateTrap(){
        return trap.get();
    }

}
