package fr.univlille.labyrinth.model.maze;

import java.util.ArrayList;
import java.util.List;

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



public class ObservableMaze extends Maze {
    protected EntityManager entityManager ;
     protected TrapManager trapManager ;
    // protected EventManager eventManager ;

    protected Position playerPosition;
    protected final List<Observer<ObservableMaze>> observers;


    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit) {
        this(width, height, distanceBetweenEntryAndExit, "DEFAULT");
    }

    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration) {
        this(width, height, distanceBetweenEntryAndExit, entitiesConfiguration, MazeAlgorithmFactory.PERFECT.getAlgorithm());
    }

    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration, MazeAlgorithm algo) {
        super(width, height, distanceBetweenEntryAndExit, algo) ;
        this.observers = new ArrayList<>();
        this.entityManager = new EntityManager();
        EntityListFactory.fillMazeEntities(this, entitiesConfiguration);
        this.trapManager = new TrapManager(this);
    }


    /**
     * Cette méthode permet d'ajouter un observateur à Maze, afin qu'il puisse être alerté d'une modification
     *
     * @param observer un observateur de Maze.
     */
    public boolean add(Observer<ObservableMaze> observer){
        return observers.add(observer);
    }


    protected void notifyObserver(){
        for (Observer<ObservableMaze> observer : observers){
            observer.update(this);
        }
    }

    /*
     * Déplace le joueur à la direction souhaitée et avertit toutes les entités de ce déplacement (certaines peuvent être amenéees à bouger)
     * (Le joueur est contenu dans les entités)
     * 
     */
    public boolean movePlayer(Direction direction){
        entityManager.moveEntities(this, direction);
        return true ;
    }


    
    /**
     * Cette méthode permet de générer un labyrinthe avec la longueur de chemin minimale par défaut (maximale). Cette méthode sera notamment appelée par le createMaze du mode libre
     *
     * @param width La largeur du labyrinthe
     * @param height La hauteur du labyrinthe
     * @param wallPercentage Le pourcentage de mur entre 0 et 0.5
     */


    /**
     * Cette méthode renvoie true si le joueur se situe à la sortie.
     * Délègue à l'encapsulation EntityManager
     */
    public boolean isPlayerAtExit() {
        return entityManager.checkPlayerOnExit();
    }


    /*
     * Surcharge pour ajouter les entités par défaut
     */


    public Position getPlayerPosition() {
        PlayerEntity player = entityManager.getPlayerEntity();
        return player != null ? player.getPosition() : null;
    }

//    TrapHandler
//    EventHandler
//    MoveBehavoirHandler


    @Override
    public void trapEffect(Position oldPlayerPosition) {
        trapManager.trapEffect(oldPlayerPosition);
        notifyObserver();
    }

    public TrapManager getTrapManager() {
        return trapManager;
    }
    /*
     * Renvoie les coordonnées de sortie de la sortie de l'entité (si elle bouge)
     * S'il n'y a pas (encore) de sortie on appelle le getter de la variable (super)
     */
    public Position getExitPosition() {
        for (Entity entity : entityManager.getEntities()) {
            if (entity.getEntityType()==EntityType.EXIT) {
                return entity.getPosition();
            }
        }
        return super.getExitPosition();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setPlayerPosition(Position playerPosition) {
        entityManager.getPlayerEntity().setPosition(playerPosition);
    }
}
