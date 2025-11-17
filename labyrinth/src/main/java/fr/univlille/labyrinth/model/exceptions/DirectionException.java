package fr.univlille.labyrinth.model.exceptions;

public class DirectionException extends MazeException{
    public DirectionException(String message) {
        super(message);
    }
    public DirectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
