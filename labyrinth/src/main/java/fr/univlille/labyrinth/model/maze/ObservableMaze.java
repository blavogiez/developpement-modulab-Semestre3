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



public class ObservableMaze extends Maze implements Observable {
    protected EntityManager entityManager ;
     protected TrapManager trapManager ;
    // protected EventManager eventManager ;

    protected Position playerPosition;


    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit) {
        this(width, height, distanceBetweenEntryAndExit, "DEFAULT");
    }

    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration) {
        this(width, height, distanceBetweenEntryAndExit, entitiesConfiguration, MazeAlgorithmFactory.PERFECT.getAlgorithm(),"DEFAULT");
    }

    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration, MazeAlgorithm algo, String trapsConfiguration) {
        super(width, height, distanceBetweenEntryAndExit, algo) ;
        this.entityManager = new EntityManager();
        EntityListFactory.fillMazeEntities(this, entitiesConfiguration);
        this.trapManager = new TrapManager(this, trapsConfiguration);
    }

    /** 
     * @param playerID
     * @param direction
     * @return boolean
     */
    /*
     * Déplace le joueur à la direction souhaitée et avertit toutes les entités de ce déplacement (certaines peuvent être amenéees à bouger)
     * (Le joueur est contenu dans les entités)
     * 
     */
    public boolean movePlayer(int playerID, Direction direction){
        entityManager.moveEntities(playerID, this, direction);
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

    /** 
     * @param playerID
     * @param oldPosition
     */
    /*
     * Surcharge pour ajouter les entités par défaut
     */

//    TrapHandler
//    EventHandler
//    MoveBehavoirHandler


    @Override
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
}
