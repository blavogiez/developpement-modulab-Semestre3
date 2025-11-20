package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

public class LavaTrap extends Trap {
    /**
     * Action effectuée lorsque le piège est utilisé par un joueur.
     *
     * @param playerID    l'identifiant du joueur déclenchant le piège
     * @param position    la position actuelle du joueur
     * @param oldPosition l'ancienne position du joueur
     * @param maze        le labyrinthe observable sur lequel le piège agit
     */
    @Override
    public void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze) {
        PlayerEntity player = maze.getEntityManager().getPlayerEntityByID(playerID);
        maze.getEntityManager().kill(player);
    }

    /**
     * Renvoie le nom du piège.
     *
     * @return le nom du piège sous forme de {@code String}
     */
    @Override
    public String name() {
        return "TRAP_LAVA";
    }
}
