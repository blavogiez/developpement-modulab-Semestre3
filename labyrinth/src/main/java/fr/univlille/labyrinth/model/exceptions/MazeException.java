package fr.univlille.labyrinth.model.exceptions;

public abstract class MazeException extends RuntimeException {
    public MazeException(String message){
        super(message);
    }
}
