package fr.univlille.labyrinth.model.maze;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityManager;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

public class ObservableMaze extends Maze {
    protected EntityManager entityManager ;
    protected final List<Observer<ObservableMaze>> observers;


    public boolean add(Observer<ObservableMaze> observer){
        return observers.add(observer);
    }


    protected void notifyObserver(){
        for (Observer<ObservableMaze> observer : observers){
            observer.update(this);
        }
    }

    public boolean movePlayer(Direction direction){
        entityManager.moveEntities(this, direction);
        notifyObserver();
        return true ;
    }

    public ObservableMaze(int width, int height, int distanceBetweenEntryAndExit) {
        super(width, height, distanceBetweenEntryAndExit) ;
        this.observers = new ArrayList<>();
        this.entityManager = new EntityManager();
        entityManager.addEntity(EntityType.PLAYER.create(getEntryPosition()));
    }

    public Position getPlayerPosition() {
        PlayerEntity player = entityManager.getPlayerEntity();
        return player != null ? player.getPosition() : null;
    }

}
