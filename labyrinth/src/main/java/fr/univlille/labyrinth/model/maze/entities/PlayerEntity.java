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

/*
Chaque entité de joueur a un ID différent pour permettre un mouvement distinct lors du multijoueur.
Si le multijoueur n'est pas activé, alors il n'y a que le joueur d'ID 0 qui est manipulé.
OCP mieux respecté de ce fait
 */
public class PlayerEntity extends Entity {
    private int ID;

    public PlayerEntity(Position position) {
        this(position, new PlayerMoveBehavior());
    }

    public PlayerEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    /** 
     * @param maze
     * @return Position
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
        } while (distResult.positions().size() == 0);

        Random random = new Random();
        Position thisPlayerPosition = distResult.positions().get(random.nextInt(distResult.positions().size()));
        return thisPlayerPosition;
    }

    /** 
     * @param id
     */
    public void setID(int id) {
        this.ID=id;
    }

    /** 
     * @return int
     */
    public int getID() {
        return ID ;
    }

    /** 
     * @return EntityType
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    /** 
     * @return MoveBehavior
     */
    @Override
    public MoveBehavior getMoveBehavior() {
        return super.getMoveBehavior();
    }

    /** 
     * @param maze
     * @return boolean
     */
    public boolean isPlayerPositionAtExit(Maze maze) {
        return this.position.equals(maze.getExitPosition());
    }
}
