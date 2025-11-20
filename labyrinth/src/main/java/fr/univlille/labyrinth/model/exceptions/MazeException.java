package fr.univlille.labyrinth.model.exceptions;

public abstract class MazeException extends RuntimeException {
    protected MazeException(String message){
        super(message);
    }
}
