package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.app.SettingsManager;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.view.labyrinth.filter.HiddenPlayerFilter;
import fr.univlille.labyrinth.view.labyrinth.filter.NormalWallFilter;
import fr.univlille.labyrinth.view.labyrinth.renderer.EntityRenderer;
import fr.univlille.labyrinth.view.labyrinth.renderer.TrapRenderer;
import fr.univlille.labyrinth.view.labyrinth.renderer.WallRenderer;
import fr.univlille.labyrinth.view.layout.LabyrinthLayoutCalculator;
import fr.univlille.labyrinth.view.renderer.ComponentRenderer;

public class HiddenPlayerNormalLabyrinthCanvasView extends LabyrinthCanvasView {

    public HiddenPlayerNormalLabyrinthCanvasView(ObservableMaze maze) {
        this(maze, new LabyrinthLayoutCalculator(), new ComponentRenderer(), SettingsManager.getSettings().isAnimationEnabled());
    }

    public HiddenPlayerNormalLabyrinthCanvasView(ObservableMaze maze, LabyrinthLayoutCalculator layoutCalculator,
                                                 ComponentRenderer componentRenderer, boolean animationEnabled) {
        this(maze, layoutCalculator, componentRenderer, animationEnabled,
             new HiddenPlayerFilter(), new NormalWallFilter());
    }

    private HiddenPlayerNormalLabyrinthCanvasView(ObservableMaze maze, LabyrinthLayoutCalculator layoutCalculator,
                                                  ComponentRenderer componentRenderer, boolean animationEnabled,
                                                  HiddenPlayerFilter hiddenPlayerFilter, NormalWallFilter normalWallFilter) {
        super(maze, layoutCalculator, componentRenderer, animationEnabled,
              hiddenPlayerFilter, normalWallFilter,
              new WallRenderer(normalWallFilter),
              new EntityRenderer(componentRenderer, hiddenPlayerFilter),
              new TrapRenderer(componentRenderer, hiddenPlayerFilter),
              null);
        this.playerAnimation.disable();
    }
}
