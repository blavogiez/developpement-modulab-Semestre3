package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.maze.traps.trap.*;

import java.util.function.Supplier;

public enum TrapFactory {
    NONE(NoneTrap::new,""),
    RANDOM_TRAP(RandomTrap::new,"R"),
    USED(UsedTrap::new,"U"),
    TELEPORTER_TRAP(TeleportTrap::new,"T"),
    FAKE_EXIT_TRAP(FakeTrap::new,"F"),
    PUSH_TRAP(PushTrap::new,"P"),
    STUN_TRAP(StunTrap::new,"S"),
    HIDE_WALL_TRAP(HideWallTrap::new,"H"),
    TELEPORT_EXIT_TRAP(TeleportExitTrap::new,"TE"),
    LAVA_TRAP(LavaTrap::new,"L");

    private Supplier<Trap> trap;
    private String compactedName;

    public static TrapFactory compactedValueOf(String compactedName) throws Exception{
        for (int i = 0; i<values().length;i++){
            if (compactedName.equals(values()[i].getCompactedName())) return values()[i];
        }
        throw new Exception("Le trap n'existe pas");
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
