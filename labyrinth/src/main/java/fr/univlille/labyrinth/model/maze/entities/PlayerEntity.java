package fr.univlille.labyrinth.model.maze.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import fr.univlille.labyrinth.model.algorithm.pathsearch.DistanceResult;
import fr.univlille.labyrinth.model.algorithm.pathsearch.MazeDistance;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;

/**
 * Chaque entité de joueur a un ID différent pour permettre un mouvement distinct lors du multijoueur.
 * Si le multijoueur n'est pas activé, alors il n'y a que le joueur d'ID 0 qui est manipulé.
 * OCP mieux respecté de ce fait
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class PlayerEntity extends Entity {
    private int id;

    /**
     * Constructeur du joueur avec un comportement de déplacement par défaut.
     *
     * @param position la position initiale du joueur
     */
    public PlayerEntity(Position position) {
        this(position, new PlayerMoveBehavior());
    }

    /**
     * Constructeur du joueur avec un comportement de déplacement spécifié.
     *
     * @param position la position initiale du joueur
     * @param moveBehavior le comportement de déplacement du joueur
     */
    public PlayerEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    /**
     * Obtient une position initiale pour un joueur dans le labyrinthe.
     * Cette méthode calcule une position qui respecte une certaine distance par rapport à la sortie
     * et qui n'est pas occupée par une autre entité.
     *
     * @param maze le labyrinthe dans lequel positionner le joueur
     * @return une position initiale appropriée pour le joueur
     */
    public static Position getInitialPosition(ObservableMaze maze) {
        Position entryPos = maze.getEntryPosition();
        if(!maze.getEntityManager().isEntityOnCell(entryPos)) return entryPos;

        DistanceResult distResult;
        int cpt = 0;
        boolean stuckToMaximum = false;

        do {
            int distanceBetweenEntryAndExit = maze.getDistanceBetweenEntryAndExit();
            distanceBetweenEntryAndExit += cpt;
            Position exitPosition = maze.getExitPosition();

            List<Position> positionsWithEntities = new ArrayList<>();
            distResult = MazeDistance.calculateAllDistances(maze, exitPosition, distanceBetweenEntryAndExit);
            for(Position positionCheck : distResult.positions()) {
                if(maze.getEntityManager().isEntityOnCell(positionCheck)) positionsWithEntities.add(positionCheck);
            }
            distResult.positions().removeAll(positionsWithEntities);
            cpt = stuckToMaximum ? cpt - 1 : cpt + 1;
            if(cpt > 20) {
                stuckToMaximum = true;
                cpt = -1;
            }
            if(cpt<-20) break ;
        } while (distResult.positions().isEmpty());

        return distResult.positions().get(RANDOM.nextInt(distResult.positions().size()));
    }

    private static final Random RANDOM = new Random();

    /** 
     * @param id
    /**
     * Définit l'identifiant du joueur.
     *
     * @param id le nouvel identifiant du joueur
     */
    public void setId(int id) {
        this.id =id;
    }

    /**
     * Retourne l'identifiant du joueur.
     *
     * @return l'identifiant du joueur
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le type de l'entité joueur.
     *
     * @return EntityType.PLAYER
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    /**
     * Retourne le comportement de déplacement du joueur.
     *
     * @return le MoveBehavior du joueur
     */
    @Override
    public MoveBehavior getMoveBehavior() {
        return super.getMoveBehavior();
    }

    /**
     * Vérifie si la position du joueur est sur la sortie du labyrinthe.
     *
     * @param maze le labyrinthe à vérifier
     * @return true si le joueur est sur la sortie, false sinon
     */
    public boolean isPlayerPositionAtExit(Maze maze) {
        return this.position.equals(maze.getExitPosition());
    }
}
