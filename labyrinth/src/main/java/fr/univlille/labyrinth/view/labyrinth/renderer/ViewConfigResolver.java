package fr.univlille.labyrinth.view.labyrinth.renderer;

import java.util.HashMap;
import java.util.Map;

import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.traps.trap.*;
import fr.univlille.labyrinth.view.GameViewConfig;

public class ViewConfigResolver {

    private final Map<EntityType, GameViewConfig> entityConfigMap;
    private final Map<Class<? extends Trap>, GameViewConfig> trapConfigMap;
    private final Map<Integer, GameViewConfig> playerConfigMap;

    public ViewConfigResolver() {
        entityConfigMap = new HashMap<>();
        entityConfigMap.put(EntityType.EXIT, GameViewConfig.EXIT);
        entityConfigMap.put(EntityType.MONSTER, GameViewConfig.MONSTER);
        entityConfigMap.put(EntityType.PLAYER, GameViewConfig.PLAYER);

        trapConfigMap = new HashMap<>();
        trapConfigMap.put(RandomTrap.class, GameViewConfig.TRAP_RANDOM);
        trapConfigMap.put(UsedTrap.class, GameViewConfig.TRAP_USED);
        trapConfigMap.put(TeleportTrap.class, GameViewConfig.TRAP_TELEPORT);
        trapConfigMap.put(FakeTrap.class, GameViewConfig.TRAP_FAKE_EXIT);
        trapConfigMap.put(PushTrap.class, GameViewConfig.TRAP_PUSH);
        trapConfigMap.put(GenerateTrap.class, GameViewConfig.TRAP_GENERATE);
        trapConfigMap.put(StunTrap.class, GameViewConfig.TRAP_STUN);
        trapConfigMap.put(TeleportExitTrap.class, GameViewConfig.TRAP_TELEPORT_EXIT);
        trapConfigMap.put(LavaTrap.class, GameViewConfig.TRAP_LAVA);

        playerConfigMap = new HashMap<>();
        playerConfigMap.put(0, GameViewConfig.PLAYER0);
        playerConfigMap.put(1, GameViewConfig.PLAYER1);
        playerConfigMap.put(2, GameViewConfig.PLAYER2);
        playerConfigMap.put(3, GameViewConfig.PLAYER3);
    }

    public GameViewConfig getConfigForEntity(Entity entity) {
        if (entity instanceof PlayerEntity) {
            int id = ((PlayerEntity) entity).getID();
            return getConfigForPlayer(id);
        }
        return entityConfigMap.getOrDefault(entity.getEntityType(), GameViewConfig.PLAYER);
    }

    public GameViewConfig getConfigForTrap(Trap trap) {
        return trapConfigMap.getOrDefault(trap.getClass(), GameViewConfig.TRAP_RANDOM);
    }

    public GameViewConfig getConfigForPlayer(int playerId) {
        return playerConfigMap.getOrDefault(playerId, GameViewConfig.PLAYER);
    }
}
