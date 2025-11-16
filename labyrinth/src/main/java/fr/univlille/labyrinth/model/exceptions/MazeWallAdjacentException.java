package fr.univlille.labyrinth.model.exceptions;

public class MazeWallAdjacentException extends MazeException {
    public MazeWallAdjacentException(String message) {
        super(message);
    }

    public MazeWallAdjacentException(String message,Throwable cause) {
        super(message,cause);
    }
}
