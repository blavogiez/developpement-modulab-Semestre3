package fr.univlille.labyrinth.view.labyrinth;

import java.util.HashMap;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import javafx.animation.AnimationTimer;

public class PlayerAnimation extends AnimationTimer {

    private boolean enabled = true ;
    private LabyrinthCanvasView view;
    private double speed;

    public PlayerAnimation(LabyrinthCanvasView view,double speed) {
        this.view = view;
        this.speed=speed;
    }

    public PlayerAnimation(LabyrinthCanvasView view) {
        this(view,0.2);
    }

    public void disable() {
        enabled=false;
    }

    /** 
     * @param now
     */
    @Override
    public void handle(long now) {
        if (!enabled) return;
        ObservableMaze maze = view.getCurrentMaze();

        HashMap<Integer, Double> xMap = view.getPlayerXMap();
        HashMap<Integer, Double> yMap = view.getPlayerYMap();
        boolean hasMoved = false;

        for (PlayerEntity player : maze.getEntityManager().getPlayerEntities()) {
            int id = player.getID();
            int targetX = player.getPosition().getX();
            int targetY = player.getPosition().getY();

            double currentX = xMap.getOrDefault(id, (double) targetX);
            double currentY = yMap.getOrDefault(id, (double) targetY);

            double newX = currentX + (targetX - currentX) * speed;
            double newY = currentY + (targetY - currentY) * speed;

            if (Math.abs(newX - targetX) < 0.01) newX = targetX;
            if (Math.abs(newY - targetY) < 0.01) newY = targetY;

            xMap.put(id, newX);
            yMap.put(id, newY);

            if (Math.abs(currentX - newX) > 0.01 || Math.abs(currentY - newY) > 0.01) {
                hasMoved = true;
            }
        }

        if (hasMoved) {
            view.draw();
        }
    }
}
