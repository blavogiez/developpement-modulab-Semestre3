package fr.univlille.labyrinth.model.exceptions;

public class ChallengePlayerException extends PlayerExceptions{

    public ChallengePlayerException(String message) {
        super(message);
    }

    public ChallengePlayerException(String message, Throwable e) {
        super(message, e);
    }
}
