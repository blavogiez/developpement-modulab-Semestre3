package fr.univlille.labyrinth.model.maze.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;

/*
Chaque entité de joueur a un ID différent pour permettre un mouvement distinct lors du multijoueur.
 */
public class PlayerEntity extends Entity {
    private int ID;
    public PlayerEntity(Position position) {
        this(position, new PlayerMoveBehavior());
    }

    public PlayerEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    public static Position getInitialPosition(ObservableMaze maze) {
        Position entryPos = maze.getEntryPosition();
        if(!maze.getEntityManager().isEntityOnCell(entryPos)) return entryPos;

        BreadthFirstSearch.DistanceResult distResult;
        int distanceOffset = 0;
        boolean reverseDirection = false;

        do {
            int targetDistance = maze.getDistanceBetweenEntryAndExit() + distanceOffset;
            Position entryPosition = maze.getEntryPosition();

            distResult = BreadthFirstSearch.calculateAllDistances(maze, entryPosition, targetDistance);

            List<Position> occupiedPositions = new ArrayList<>();
            for(Position pos : distResult.positions()) {
                if(maze.getEntityManager().isEntityOnCell(pos)) {
                    occupiedPositions.add(pos);
                }
            }
            distResult.positions().removeAll(occupiedPositions);

            distanceOffset = reverseDirection ? distanceOffset - 1 : distanceOffset + 1;
            if(distanceOffset > 5) {
                reverseDirection = true;
                distanceOffset = -1;
            }
        } while (distResult.positions().isEmpty());

        Random random = new Random();
        return distResult.positions().get(random.nextInt(distResult.positions().size()));
    }

    public void setID(int id) {
        this.ID=id;
    }

    public int getID() {
        return ID ;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    @Override
    public MoveBehavior getMoveBehavior() {
        return super.getMoveBehavior();
    }

    public boolean isPlayerPositionAtExit(Maze maze) {
        return this.position.equals(maze.getExitPosition());
    }

}
