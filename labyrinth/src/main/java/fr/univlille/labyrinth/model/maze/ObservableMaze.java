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



public class ObservableMaze extends Maze implements Observable<ObservableMaze> {
    private final List<Observer<ObservableMaze>> observers = new ArrayList<>();
    protected EntityManager entityManager ;
     protected TrapManager trapManager ;


    /**
     * Constructeur de l'ObservableMaze avec une distance et configuration d'entités par défaut.
     *
     * @param width la largeur du labyrinthe
     * @param height la hauteur du labyrinthe
     * @param distanceBetweenEntryAndExit la distance entre l'entrée et la sortie
     */
    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit) {
        this(width, height, distanceBetweenEntryAndExit, "DEFAULT");
    }

    /**
     * Constructeur de l'ObservableMaze avec une distance et une configuration d'entités spécifiées.
     *
     * @param width la largeur du labyrinthe
     * @param height la hauteur du labyrinthe
     * @param distanceBetweenEntryAndExit la distance entre l'entrée et la sortie
     * @param entitiesConfiguration la configuration des entités dans le labyrinthe
     */
    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration) {
        this(width, height, distanceBetweenEntryAndExit, entitiesConfiguration, MazeAlgorithmFactory.PERFECT.getAlgorithm(),"DEFAULT");
    }



    /**
     * Constructeur de l'ObservableMaze avec un pourcentage de murs, distance, configuration d'entités et algorithme spécifiés.
     *
     * @param width la largeur du labyrinthe
     * @param height la hauteur du labyrinthe
     * @param wallPercentage le pourcentage de murs
     * @param distanceBetweenEntryAndExit la distance entre l'entrée et la sortie
     * @param entitiesConfiguration la configuration des entités dans le labyrinthe
     * @param algo l'algorithme de génération du labyrinthe
     */
    public ObservableMaze(int width, int height, double wallPercentage, int distanceBetweenEntryAndExit, String entitiesConfiguration, MazeAlgorithm algo) {
        this(width,height,wallPercentage,distanceBetweenEntryAndExit,entitiesConfiguration,algo,"DEFAULT");
    }

    /**
     * Constructeur de l'ObservableMaze avec un pourcentage de murs, configuration d'entités, algorithme et configuration de pièges spécifiés.
     *
     * @param width la largeur du labyrinthe
     * @param height la hauteur du labyrinthe
     * @param wallPercentage le pourcentage de murs
     * @param entitiesConfiguration la configuration des entités dans le labyrinthe
     * @param algo l'algorithme de génération du labyrinthe
     * @param trapsConfiguration la configuration des pièges dans le labyrinthe
     */
    public ObservableMaze(int width, int height, double wallPercentage, String entitiesConfiguration, MazeAlgorithm algo, String trapsConfiguration) {
        this(width, height, wallPercentage, Integer.MAX_VALUE, entitiesConfiguration, algo, trapsConfiguration);
    }

    /**
     * Constructeur principal de l'ObservableMaze avec tous les paramètres.
     *
     * @param width la largeur du labyrinthe
     * @param height la hauteur du labyrinthe
     * @param wallPercentage le pourcentage de murs
     * @param distanceBetweenEntryAndExit la distance entre l'entrée et la sortie
     * @param entitiesConfiguration la configuration des entités dans le labyrinthe
     * @param algo l'algorithme de génération du labyrinthe
     * @param trapsConfiguration la configuration des pièges dans le labyrinthe
     */
    public ObservableMaze(int width, int height, double wallPercentage, int distanceBetweenEntryAndExit, String entitiesConfiguration, MazeAlgorithm algo, String trapsConfiguration) {
        super(width, height, wallPercentage, distanceBetweenEntryAndExit, algo) ;
        this.entityManager = new EntityManager();
        EntityListFactory.fillMazeEntities(this, entitiesConfiguration);
        this.trapManager = new TrapManager(this, trapsConfiguration);
    }

    /**
     * Constructeur de l'ObservableMaze avec une distance, configuration d'entités, algorithme et configuration de pièges spécifiés.
     *
     * @param width la largeur du labyrinthe
     * @param height la hauteur du labyrinthe
     * @param distanceBetweenEntryAndExit la distance entre l'entrée et la sortie
     * @param entitiesConfiguration la configuration des entités dans le labyrinthe
     * @param algo l'algorithme de génération du labyrinthe
     * @param trapsConfiguration la configuration des pièges dans le labyrinthe
     */
    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration, MazeAlgorithm algo, String trapsConfiguration) {
        super(width, height, distanceBetweenEntryAndExit, algo) ;

        this.entityManager = new EntityManager();
        EntityListFactory.fillMazeEntities(this, entitiesConfiguration);
        this.trapManager = new TrapManager(this, trapsConfiguration);
    }

    /**
     * Constructeur de l'ObservableMaze avec une distance, configuration d'entités et algorithme spécifiés.
     *
     * @param width la largeur du labyrinthe
     * @param height la hauteur du labyrinthe
     * @param distanceBetweenEntryAndExit la distance entre l'entrée et la sortie
     * @param entitiesConfiguration la configuration des entités dans le labyrinthe
     * @param algo l'algorithme de génération du labyrinthe
     */
    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration, MazeAlgorithm algo) {
        this(width,height,distanceBetweenEntryAndExit,entitiesConfiguration,algo,"DEFAULT");
    }

    /**
     * Déplace le joueur dans la direction souhaitée et avertit toutes les entités
     * de ce déplacement (certaines peuvent être amenées à bouger).  
     * Le joueur est lui-même contenu dans les entités.
     *
     * @param playerID  l'identifiant du joueur à déplacer
     * @param direction la direction du déplacement
     * @return {@code true} si le déplacement a été effectué
     */
    public boolean movePlayer(int playerID, Direction direction) {
        entityManager.moveEntities(playerID, this, direction);
        return true;
    }

    /**
     * Applique les effets des pièges après le déplacement d'un joueur.
     *
     * @param playerID     l'identifiant du joueur affecté
     * @param oldPosition  l'ancienne position du joueur
     */
    @Override
    public void trapEffect(int playerID, Position oldPosition) {
        trapManager.trapEffect(playerID, oldPosition);
        notifyObserver();
    }


    /**
     * Retourne le gestionnaire de pièges.
     *
     * @return le TrapManager gérant les pièges du labyrinthe
     */
    public TrapManager getTrapManager() {
        return trapManager;
    }

    /**
     * Retourne le gestionnaire d'entités.
     *
     * @return l'EntityManager gérant les entités du labyrinthe
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Définit la position d'un joueur dans le labyrinthe.
     *
     * @param playerID l'identifiant du joueur
     * @param position la nouvelle position du joueur
     */
    public void setPlayerPosition(int playerID, Position position) {
        PlayerEntity player = entityManager.getPlayerEntityByID(playerID);
        if (player != null) {
            player.setPosition(position);
        }
    }

    /**
     * Retourne la liste des observateurs du labyrinthe.
     *
     * @return la liste des observateurs
     */
    @Override
    public List<Observer<ObservableMaze>> getObservers() {
        return this.observers;
    }
}
