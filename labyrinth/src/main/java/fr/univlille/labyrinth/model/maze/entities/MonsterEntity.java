package fr.univlille.labyrinth.model.maze.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MonsterMoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

public class MonsterEntity extends Entity {
    public MonsterEntity(Position position) {
        this(position, new MonsterMoveBehavior());
    }

    public MonsterEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    /** 
     * @param maze
     * @return Position
     */
    public static Position getInitialPosition(ObservableMaze maze) {
        Position normalPos = maze.getExitPosition();
        if(!maze.getEntityManager().isEntityOnCell(normalPos))return normalPos;

        BreadthFirstSearch.DistanceResult distResult;
        int cpt = 0 ;
        boolean stuckToMaximum = false ;
        do {
            int distanceBetweenEntryAndExit = maze.getDistanceBetweenEntryAndExit();
            distanceBetweenEntryAndExit+=cpt;
            Position entryPosition = maze.getEntryPosition();

            List<Position> positionsWithEntities = new ArrayList<>();
            distResult = BreadthFirstSearch.calculateAllDistances(maze, entryPosition, distanceBetweenEntryAndExit);
            for(Position positionCheck : distResult.positions()) {
                if(maze.getEntityManager().isEntityOnCell(positionCheck)) positionsWithEntities.add(positionCheck);
            }
            distResult.positions().removeAll(positionsWithEntities);
            cpt = stuckToMaximum ? cpt - 1 : cpt + 1;
            if(cpt>5) {
                stuckToMaximum=true ; 
                cpt= - 1 ;
            }
        } while (distResult.positions().size()==0);
        Random random = new Random();
        Position monsterPosition = distResult.positions().get(random.nextInt(distResult.positions().size()));
        return monsterPosition;
        
    }

    /** 
     * @return EntityType
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.MONSTER;
    }

    /** 
     * @return MoveBehavior
     */
    @Override
    public MoveBehavior getMoveBehavior() {
        return super.getMoveBehavior();
    }

    /** 
     * @return String
     */
    @Override
    public String getDefType() {
        return"triangle marron";
    }

}
