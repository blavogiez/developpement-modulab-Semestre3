package fr.univlille.labyrinth.model.maze.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.DistanceResult;
import fr.univlille.labyrinth.model.algorithm.pathsearch.MazeDistance;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

/**
 * Entité représentant la sortie dans le labyrinthe.
 * La sortie a une position initiale calculée par rapport à l'entrée du labyrinthe.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class ExitEntity extends Entity {
    private static final Random RANDOM = new Random();

    /**
     * Constructeur de la sortie avec un comportement de déplacement nul.
     *
     * @param position la position initiale de la sortie
     */
    public ExitEntity(Position position) {
        this(position, null);
    }

    /**
     * Constructeur de la sortie avec un comportement de déplacement spécifié.
     *
     * @param position la position initiale de la sortie
     * @param moveBehavior le comportement de déplacement de la sortie (peut être nul)
     */
    public ExitEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    /**
     * Retourne le type de l'entité sortie.
     *
     * @return EntityType.EXIT
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.EXIT;
    }

    /**
     * Obtient une position initiale pour la sortie dans le labyrinthe.
     * Cette méthode calcule une position qui respecte une certaine distance par rapport à l'entrée
     * et qui n'est pas occupée par une autre entité.
     * Si la position n'est pas prise par une sortie, alors on la place ailleurs à la même distance entrée / sortie si possible, sinon on augmente.
     * On évite d'être bloqué pour toujours en changeant une valeur si il n'y a aucune cellule possible / libre à la distance demandée !
     * Si c'est trop augmenté et que ce n'est pas possible, on ira désormais dans les négatifs.
     *
     * @param maze le labyrinthe dans lequel positionner la sortie
     * @return une position initiale appropriée pour la sortie
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
        } while (distResult.positions().isEmpty());

        return distResult.positions().get(RANDOM.nextInt(distResult.positions().size()));

    }
}