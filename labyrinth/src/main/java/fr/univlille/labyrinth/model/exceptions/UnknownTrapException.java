package fr.univlille.labyrinth.model.exceptions;

public class UnknownTrapException extends IllegalArgumentException {

    public UnknownTrapException(String name) {
        super("Nom de piège compacté inconnu " + name);
    }

}
