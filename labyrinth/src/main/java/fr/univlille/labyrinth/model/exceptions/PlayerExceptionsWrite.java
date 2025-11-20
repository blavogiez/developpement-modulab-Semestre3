package fr.univlille.labyrinth.model.exceptions;

public class PlayerExceptionsWrite extends PlayerExceptions{
    public PlayerExceptionsWrite(String message) {
        super(message);
    }

    public PlayerExceptionsWrite(String message, Throwable e) {
        super(message, e);
    }
}
