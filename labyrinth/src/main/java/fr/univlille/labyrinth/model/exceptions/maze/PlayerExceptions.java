package fr.univlille.labyrinth.model.exceptions.maze;

public abstract class PlayerExceptions extends Exception{
    public PlayerExceptions(String message){
        super(message);
    }

    public PlayerExceptions(String message,Throwable e){
        super(message,e);
    }
}
