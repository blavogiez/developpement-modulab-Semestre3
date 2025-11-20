package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.EntityManager;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;

import java.util.Random;

public abstract class Trap {
    Position position;

    /**
     * Action effectuée lorsqu'un joueur déclenche le piège.
     *
     * @param playerID    l'identifiant du joueur déclenchant le piège
     * @param position    la position actuelle du joueur
     * @param oldPosition l'ancienne position du joueur
     * @param maze        le labyrinthe observable sur lequel le piège agit
     */
    public abstract void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze);

    /**
     * Remplace le piège à la position donnée par un piège "utilisé".
     *
     * @param position la position du piège à révéler
     * @param traps    le tableau de pièges du labyrinthe
     */
    protected void revealTrap(Position position, Trap[][] traps) {
        traps[position.getY()][position.getX()] = TrapFactory.USED.generateTrap();
    }

    /**
     * Renvoie le nom du piège.
     *
     * @return le nom du piège sous forme de {@code String}
     */
    public String name() {
        return "NONE";
    }

    /**
     * Renvoie une position libre aléatoire dans le labyrinthe pour placer un piège.
     *
     * @param maze le labyrinthe dans lequel chercher une cellule libre
     * @return une position libre aléatoire
     */
    protected static Position getRandomCell(ObservableMaze maze) {
        Random random = new Random();
        Trap[][] traps = maze.getTrapManager().getTraps();
        EntityManager entityManager = maze.getEntityManager();
        int x, y;
        Position position;
        do {
            y = random.nextInt(traps.length);
            x = random.nextInt(traps[y].length);
            position = new Position(x, y);
        } while (!isFree(traps, entityManager, position));
        return position;
    }

    /**
     * Vérifie si une position est libre pour y placer un piège.
     * <p>
     * Une position est libre si elle contient un {@code NoneTrap} et qu'aucune entité ne s'y trouve.
     *
     * @param traps         le tableau de pièges du labyrinthe
     * @param entityManager le gestionnaire des entités du labyrinthe
     * @param position      la position à tester
     * @return {@code true} si la position est libre, sinon {@code false}
     */
    private static boolean isFree(Trap[][] traps, EntityManager entityManager, Position position) {
        return traps[position.getY()][position.getX()] instanceof NoneTrap
                && !entityManager.isEntityOnCell(position);
    }
}
