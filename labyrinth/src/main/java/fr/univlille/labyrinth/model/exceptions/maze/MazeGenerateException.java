package fr.univlille.labyrinth.model.exceptions.maze;

public class MazeGenerateException extends MazeException{
    public MazeGenerateException(String message) {
        super(message);
    }

    public MazeGenerateException(String message, Throwable cause) {
        super(message, cause);
    }
}
