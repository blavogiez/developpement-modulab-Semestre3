package fr.univlille.labyrinth.model.exceptions.maze;

public class PlayerLoadDataException extends PlayerExceptions{

    public PlayerLoadDataException(String message) {
        super(message);
    }

    public PlayerLoadDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
