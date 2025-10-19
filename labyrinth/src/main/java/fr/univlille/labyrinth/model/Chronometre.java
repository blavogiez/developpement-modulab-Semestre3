package fr.univlille.labyrinth.model;

/**
 * Modèle du chronomètre d'une partie. Mesure le temps entre l'utilisation des méthodes start/stop.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Chronometre{
    private long debut=0;
    private long fin=0;
    private boolean encours=false;

    public Chronometre(){}

    /**
     * Cette méthode permet de garder en mémoire dans le paramètre début le moment de l'exécution de celle-ci.
     */
    public void start(){
        if (!encours){
            this.debut=System.currentTimeMillis();
            this.encours=true;
        }
    }
    /**
     * Cette méthode permet de garder en mémoire dans le paramètre fin le moment de l'exécution de celle-ci.
     */
    public void stop(){
        if (encours){
            this.fin=System.currentTimeMillis();
            this.encours=false;
        }
    }

    /**
     * Renvoie la différente entre début et fin, ou entre le début et maintenant si la fin n'a pas était spécifié
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
     * Renvoie le résultat sous la forme d'un String dans le format chrono.
     */
    public String getChronoFormat(String format){
        long millis = getChrono();
        long secondes = (millis / 1000) % 60;
        return switch (format.toLowerCase()) {
        case "millis" -> millis + " ms";
        case "secondes" -> secondes + " s";
        default -> secondes + " s";
    };
    }

    public String toString(){
        return "Temps écoulé"+ this.getChrono();
    }
}

