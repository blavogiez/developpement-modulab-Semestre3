package fr.univlille.labyrinth.view.labyrinth.animation;

import java.util.Map;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import javafx.animation.AnimationTimer;

/*
 * Redessine les joueurs plus fréquemment pour permettre une animation
 * Peut être désactivé dans les paramètres
 */
public class PlayerAnimation extends AnimationTimer {

    private boolean enabled = true;
    private AnimatableView view;
    private double speed;
    private long lastUpdate = 0;

    public PlayerAnimation(AnimatableView view, double speed) {
        this.view = view;
        this.speed = speed;
    }

    public PlayerAnimation(AnimatableView view) {
        this(view, 6.0);
    }

    public void disable() {
        enabled = false;
    }

    /** 
     * @return boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

    /** 
     * @param now
     */
    public void handle(long now) {
        if (!enabled)
            return;

        if (lastUpdate == 0) {
            lastUpdate = now;
            return;
        }

        double delta = (now - lastUpdate) / 1_000_000_000.0;
        lastUpdate = now;

        ObservableMaze maze = view.getCurrentMaze();

        Map<Integer, Double> xMap = view.getPlayerXMap();
        Map<Integer, Double> yMap = view.getPlayerYMap();

        boolean hasMoved = false;

        for (PlayerEntity player : maze.getEntityManager().getEntitiesByType(PlayerEntity.class)) {

            int id = player.getId();
            double targetX = player.getPosition().getX();
            double targetY = player.getPosition().getY();

            double currentX = xMap.getOrDefault(id, targetX);
            double currentY = yMap.getOrDefault(id, targetY);

            double maxStep = speed * delta;

            double newX = interpolate(currentX, targetX, maxStep);
            double newY = interpolate(currentY, targetY, maxStep);

            xMap.put(id, newX);
            yMap.put(id, newY);

            if (Math.abs(newX - currentX) > 0.001 || Math.abs(newY - currentY) > 0.001)
                hasMoved = true;
        }

        if (hasMoved)
            view.draw();
    }

    /** 
     * @param current
     * @param target
     * @param maxStep
     * @return double
     */
    private double interpolate(double current, double target, double maxStep) {
        double diff = target - current;

        if (Math.abs(diff) <= maxStep)
            return target;

        return current + Math.signum(diff) * maxStep;
    }
}
