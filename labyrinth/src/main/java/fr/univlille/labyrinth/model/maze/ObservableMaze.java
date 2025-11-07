package fr.univlille.labyrinth.model.maze;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityManager;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.entities.factory.EntityListFactory;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MovingStepBehavior;

public class ObservableMaze extends Maze {
    protected EntityManager entityManager ;
    protected final List<Observer<ObservableMaze>> observers;

    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit, String entitiesConfiguration) {
        super(width, height, distanceBetweenEntryAndExit);
        this.observers = new ArrayList<>();
        this.entityManager = new EntityManager();
        List<Entity> entities = EntityListFactory.createEntities(entitiesConfiguration, this);
        entities.forEach(entityManager::addEntity);
    }

    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit) {
        this(width, height, distanceBetweenEntryAndExit, "DEFAULT");
    }

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
        notifyObserver();
        return true ;
    }

    public Position getPlayerPosition() {
        PlayerEntity player = entityManager.getPlayerEntity();
        return player != null ? player.getPosition() : null;
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

    public boolean isPlayerAtExit() {
        PlayerEntity player = entityManager.getPlayerEntity();
        if (player == null) return false;
        Position playerPos = player.getPosition();
        Position exitPos = getExitPosition();
        return playerPos != null && exitPos != null && playerPos.equals(exitPos);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
