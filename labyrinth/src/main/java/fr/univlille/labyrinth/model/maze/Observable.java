package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.Observer;
import java.util.ArrayList;
import java.util.List;

public interface Observable {

    List<Observer<Observable>> observers = new ArrayList<>();

    public default boolean add(Observer<Observable> observer) {
        return observers.add(observer);
    }

    public default void notifyObserver() {
        for (Observer<Observable> observer : observers) {
            observer.update(this);
        }
    }
}
