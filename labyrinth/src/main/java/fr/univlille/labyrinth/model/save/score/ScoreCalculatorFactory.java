package fr.univlille.labyrinth.model.save.score;

/**
 * Enum factory pour créer les calculateurs de score
 * Facilite la configuration par fichier CSV et respecte le pattern Factory Method
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public enum ScoreCalculatorFactory {

    /**
     * Calculateur de score standard basé sur dimensions et pourcentage de murs
     */
    STANDARD {
        @Override
        public ScoreCalculator create() {
            return new StandardScoreCalculator();
        }
    },

    /**
     * Calculateur de score speedrun avec bonus temporel
     */
    SPEEDRUN {
        @Override
        public ScoreCalculator create() {
            return new SpeedrunScoreCalculator();
        }
    };

    /**
     * Crée une instance du calculateur de score correspondant
     * Pattern Factory Method implémenté dans l'enum
     *
     * @return ScoreCalculator l'instance créée
     */
    public abstract ScoreCalculator create();
}
