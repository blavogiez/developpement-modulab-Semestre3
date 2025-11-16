package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.Observer;
import java.util.ArrayList;
import java.util.List;

public interface Observable<T extends Observable<T>> {
    List<Observer<T>> getObservers();

    default boolean add(Observer<T> observer) {
        return getObservers().add(observer);
    }

    default void notifyObserver() {
        for (Observer<T> observer : getObservers()) {
            observer.update((T) this);
        }
    }
}
