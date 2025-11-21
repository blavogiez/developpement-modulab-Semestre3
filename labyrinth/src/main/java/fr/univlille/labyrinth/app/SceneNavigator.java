package fr.univlille.labyrinth.app;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class SceneNavigator {
    private SceneNavigator(){}
    private static NavigationContext context;
    private static final SceneLoader sceneLoader = new SceneLoader();
    private static final SceneTransition sceneTransition = new SceneTransition();

    /** 
     * @param navigationContext
     */
    public static void setContext(NavigationContext navigationContext) {
        context = navigationContext;
    }

    /** 
     * @return Stage
     */
    public static Stage getPrimaryStage() {
        return context != null ? context.getPrimaryStage() : null;
    }

    /** 
     * @param page
     * @throws IOException
     */
    /*
     * Change de scène pour aller à la page FXML dont le chemin est spécifié en paramètre.
     */
    public static void goTo(String page) throws IOException {
        if (context == null) {
            throw new IllegalStateException("Navigation context absent");
        }

        Parent newContent = sceneLoader.load(page);

        if (context.getRootPane().getChildren().size() > 1) {
            Parent currentContent = (Parent) context.getRootPane().getChildren().get(1);

            sceneTransition.transitionTo(currentContent, newContent, () -> {
                context.getRootPane().getChildren().remove(1);
                context.getRootPane().getChildren().add(newContent);
                context.getBackgroundManager().setVisible(context.getBackgroundManager().shouldShowBackground(page));
                ThemeManager.applyTheme(context.getPrimaryStage().getScene());
            });
        }
    }
}