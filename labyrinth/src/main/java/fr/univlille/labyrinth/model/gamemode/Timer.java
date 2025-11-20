package fr.univlille.labyrinth.model.gamemode;

/**
 * Classe utilitaire du chronomètre d'une partie. Permet de mesurer le temps entre un début et une fin.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Timer{
    private long debut;
    private long fin;
    private boolean encours;

    public Timer(){
        this.debut=0;
        this.fin=0;
        this.encours=false;
    }

    /**
     * Cette méthode permet de lancer le chronomètre.
     */
    public void start(){
        if (!encours){
            this.debut=System.currentTimeMillis();
            this.encours=true;
        }
    }
    /**
     * Cette méthode permet d'arrêter le chronomètre.
     */
    public void stop(){
        if (encours){
            this.fin=System.currentTimeMillis();
            this.encours=false;
        }
    }

    /**
     * Permet de récupérer le chronomètre soit si le chronomètre est arrêter on récupère le temps entre le start et le stop
     * Ou si le chronomètre est en cours on récupère le temps en le start et maintenant.
     * @return long
     */
    public long getChrono(){
        if(!encours){
            return this.fin-this.debut;
        }
        else{
            return System.currentTimeMillis()-this.debut;
        }
    }

    /**
     * Permet de récupérer un String sous un format spécifier.
     * @param format
     * @return String
     */
    public String getChronoFormat(String format){
        long millis = getChrono();
        long secondes = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        return switch (format.toLowerCase()) {
        case "millis" -> millis + " ms";
        case "secondes" -> secondes + " s";
        case "minutes" -> minutes + "min";
        default -> secondes + " s";
    };
    }

    /**
     * Permet de récupérer le chronomètre sous un format spécifier.
     * @param format
     * @return long
     */

    public long convertisseurMillisVersFormat(String format){
        long millis = getChrono();
        long secondes = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        return switch (format.toLowerCase()) {
        case "millis" -> millis;
        case "secondes" -> secondes;
        case "minutes" -> minutes;
        default -> secondes;
    };
    }

    /**
     * Permet de récupérer le chronomètre sous forme de String.
     * @return String
     */
    public String toString(){
        return "Temps écoulé : "+ this.getChrono();
    }
}
