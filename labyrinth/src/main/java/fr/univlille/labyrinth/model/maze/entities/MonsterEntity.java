package fr.univlille.labyrinth.model.maze.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.DistanceResult;
import fr.univlille.labyrinth.model.algorithm.pathsearch.MazeDistance;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MonsterMoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

/**
 * Entité représentant un monstre dans le labyrinthe.
 * Le monstre se déplace selon un comportement spécifique et a une position initiale calculée par rapport à l'entrée du labyrinthe.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class MonsterEntity extends Entity {
    /**
     * Constructeur du monstre avec un comportement de déplacement par défaut.
     *
     * @param position la position initiale du monstre
     */
    public MonsterEntity(Position position) {
        this(position, new MonsterMoveBehavior());
    }

    /**
     * Constructeur du monstre avec un comportement de déplacement spécifié.
     *
     * @param position la position initiale du monstre
     * @param moveBehavior le comportement de déplacement du monstre
     */
    public MonsterEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    /**
     * Obtient une position initiale pour le monstre dans le labyrinthe.
     * Cette méthode calcule une position qui respecte une certaine distance par rapport à l'entrée
     * et qui n'est pas occupée par une autre entité.
     *
     * @param maze le labyrinthe dans lequel positionner le monstre
     * @return une position initiale appropriée pour le monstre
     */
    public static Position getInitialPosition(ObservableMaze maze) {
        Position normalPos = maze.getExitPosition();
        if(!maze.getEntityManager().isEntityOnCell(normalPos))return normalPos;

        DistanceResult distResult;
        int cpt = 0 ;
        boolean stuckToMaximum = false ;
        do {
            int distanceBetweenEntryAndExit = maze.getDistanceBetweenEntryAndExit();
            distanceBetweenEntryAndExit+=cpt;
            Position entryPosition = maze.getEntryPosition();

            List<Position> positionsWithEntities = new ArrayList<>();
            distResult = MazeDistance.calculateAllDistances(maze, entryPosition, distanceBetweenEntryAndExit);
            for(Position positionCheck : distResult.positions()) {
                if(maze.getEntityManager().isEntityOnCell(positionCheck)) positionsWithEntities.add(positionCheck);
            }
            distResult.positions().removeAll(positionsWithEntities);
            cpt = stuckToMaximum ? cpt - 1 : cpt + 1;
            if(cpt>20) {
                stuckToMaximum=true ; 
                cpt= - 1 ;
            }
            if(cpt<-20) break ;
        } while (distResult.positions().size()==0);
        Random random = new Random();
        Position monsterPosition = distResult.positions().get(random.nextInt(distResult.positions().size()));
        return monsterPosition;
        
    }

    /**
     * Retourne le type de l'entité monstre.
     *
     * @return EntityType.MONSTER
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.MONSTER;
    }

    /**
     * Retourne le comportement de déplacement du monstre.
     *
     * @return le MoveBehavior du monstre
     */
    @Override
    public MoveBehavior getMoveBehavior() {
        return super.getMoveBehavior();
    }
}
