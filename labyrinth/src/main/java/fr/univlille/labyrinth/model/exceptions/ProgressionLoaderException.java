package fr.univlille.labyrinth.model.exceptions;

public class ProgressionLoaderException extends RuntimeException{
    public ProgressionLoaderException(String message){
        super(message);
    }
    public ProgressionLoaderException(String message,Throwable e){
        super(message,e);
    }
}
