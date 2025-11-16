package fr.univlille.labyrinth.model.exceptions;

public class MazeExceptionSize extends MazeException {
    public MazeExceptionSize(String message) {
        super(message);
    }
    public MazeExceptionSize(String message,Throwable e){
        super(message,e);
    }
}
