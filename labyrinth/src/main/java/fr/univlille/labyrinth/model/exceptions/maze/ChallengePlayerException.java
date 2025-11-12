package fr.univlille.labyrinth.model.exceptions.maze;

public class ChallengePlayerException extends PlayerExceptions{

    public ChallengePlayerException(String message) {
        super(message);
    }

    public ChallengePlayerException(String message, Throwable e) {
        super(message, e);
    }
}
