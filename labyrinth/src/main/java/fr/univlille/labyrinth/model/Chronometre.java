package fr.univlille.labyrinth.model;

public class Chronometre{
    private long debut=0;
    private long fin=0;
    private boolean encours=false;

    public Chronometre(){}

    public void start(){
        if (!encours){
            this.debut=System.currentTimeMillis();
            this.encours=true;
        }
    }

    public void stop(){
        if (encours){
            this.fin=System.currentTimeMillis();
            this.encours=false;
        }
    }

    public long getChrono(){
        if(!encours){
            return this.fin-this.debut;
        }
        else{
            return System.currentTimeMillis()-this.debut;
        }
    }

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

