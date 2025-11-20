package fr.univlille.labyrinth.model.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.maze.entities.*;
import fr.univlille.labyrinth.model.maze.traps.TrapManager;
import fr.univlille.labyrinth.model.maze.entities.factory.EntityListFactory;

/**
 * Implémentation de Maze en version dynamique, observable
 *
 * Afin de mieux respecter le S de S O L I D 
 * 
 *
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */



public class ObservableMaze extends Maze implements Observable<ObservableMaze> {
    private final List<Observer<ObservableMaze>> observers = new ArrayList<>();
    protected EntityManager entityManager ;
     protected TrapManager trapManager ;
    // protected EventManager eventManager ;


    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit) {
        this(width, height, distanceBetweenEntryAndExit, "DEFAULT");
    }

    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration) {
        this(width, height, distanceBetweenEntryAndExit, entitiesConfiguration, MazeAlgorithmFactory.PERFECT.getAlgorithm(),"DEFAULT");
    }



    public ObservableMaze(int width, int height, double wallPercentage, int distanceBetweenEntryAndExit, String entitiesConfiguration, MazeAlgorithm algo) {
        this(width,height,wallPercentage,distanceBetweenEntryAndExit,entitiesConfiguration,algo,"DEFAULT");
    }

    public ObservableMaze(int width, int height, double wallPercentage, String entitiesConfiguration, MazeAlgorithm algo, String trapsConfiguration) {
        this(width, height, wallPercentage, Integer.MAX_VALUE, entitiesConfiguration, algo, trapsConfiguration);
    }

    public ObservableMaze(int width, int height, double wallPercentage, int distanceBetweenEntryAndExit, String entitiesConfiguration, MazeAlgorithm algo, String trapsConfiguration) {
        super(width, height, wallPercentage, distanceBetweenEntryAndExit, algo) ;
        this.entityManager = new EntityManager();
        EntityListFactory.fillMazeEntities(this, entitiesConfiguration);
        this.trapManager = new TrapManager(this, trapsConfiguration);
    }

    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration, MazeAlgorithm algo, String trapsConfiguration) {
        super(width, height, distanceBetweenEntryAndExit, algo) ;

        this.entityManager = new EntityManager();
        EntityListFactory.fillMazeEntities(this, entitiesConfiguration);
        this.trapManager = new TrapManager(this, trapsConfiguration);
    }

    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration, MazeAlgorithm algo) {
        this(width,height,distanceBetweenEntryAndExit,entitiesConfiguration,algo,"DEFAULT");
    }

    /*
     * Déplace le joueur à la direction souhaitée et avertit toutes les entités de ce déplacement (certaines peuvent être amenéees à bouger)
     * (Le joueur est contenu dans les entités)
     * 
     * @param playerID
     * @param direction
     * @return boolean
     */
    public boolean movePlayer(int playerID, Direction direction){
        entityManager.moveEntities(playerID, this, direction);
        return true ;
    }

    /*
     * Surcharge pour ajouter les entités par défaut
     * @param playerID
     * @param oldPosition
     */

//    TrapHandler
//    EventHandler
//    MoveBehavoirHandler


    public void trapEffect(int playerID, Position oldPosition) {
        trapManager.trapEffect(playerID, oldPosition);
        notifyObserver();
    }

    /** 
     * @return TrapManager
     */
    public TrapManager getTrapManager() {
        return trapManager;
    }

    /** 
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /** 
     * @param playerID
     * @param position
     */
    public void setPlayerPosition(int playerID, Position position) {
        PlayerEntity player = entityManager.getPlayerEntityByID(playerID);
        if (player != null) {
            player.setPosition(position);
        }
    }
    
    /** 
     * @return {@code List<Observer<ObservableMaze>>}
     */
    @Override
    public List<Observer<ObservableMaze>> getObservers() {
        return this.observers;
    }
}
