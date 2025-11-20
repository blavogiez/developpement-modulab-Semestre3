package fr.univlille.labyrinth.model.maze.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.DistanceResult;
import fr.univlille.labyrinth.model.algorithm.pathsearch.MazeDistance;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

public class ExitEntity extends Entity {
    private static final Random RANDOM = new Random();

    public ExitEntity(Position position) {
        this(position, null);
    }

    public ExitEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    /** 
     * @return EntityType
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.EXIT;
    }

    /** 
     * @param maze
     * @return Position
     */
    /*
     * Si la position n'est pas prise par une sortie, alors on la place ailleurs à la même distance entrée / sortie si possible, sinon on augmente.
     * On évite d'être bloqué pour toujours en changeant une valeur si il n'y a aucune cellule possible / libre à la distance demandée !
     * Si c'est trop augmenté et que ce n'est pas possible, on ira désormais dans les négatifs.
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

        Position thisExitPosition = distResult.positions().get(RANDOM.nextInt(distResult.positions().size()));
        return thisExitPosition;

    }
}