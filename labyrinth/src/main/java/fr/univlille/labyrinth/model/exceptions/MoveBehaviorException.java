package fr.univlille.labyrinth.model.exceptions;

public class MoveBehaviorException extends MazeException{
    public MoveBehaviorException(String message) {
        super(message);
    }
    public MoveBehaviorException(String message, Throwable cause) {
        super(message, cause);
    }

}
