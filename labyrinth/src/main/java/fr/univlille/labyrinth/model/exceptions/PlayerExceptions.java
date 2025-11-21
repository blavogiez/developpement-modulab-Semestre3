package fr.univlille.labyrinth.model.exceptions;

public abstract class PlayerExceptions extends Exception{
    protected PlayerExceptions(String message){
        super(message);
    }

    protected PlayerExceptions(String message,Throwable e){
        super(message,e);
    }
}
