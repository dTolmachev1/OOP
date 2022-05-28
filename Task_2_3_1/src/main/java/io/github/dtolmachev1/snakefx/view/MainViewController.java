package io.github.dtolmachev1.snakefx.view;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * <p>Main controller, acts as mediator for other controllers.</p>
 */
public class MainViewController extends AbstractController {
    private final BooleanProperty isGameRunning;
    @FXML
    private VBox root;
    @FXML
    private GameScreenController gameScreenController;
    @FXML
    private SettingsController settingsController;

    /**
     * <p>Default constructor to initialize internal structures.</p>
     */
    public MainViewController() {
        this.isGameRunning = new SimpleBooleanProperty(false);
    }

    /**
     * <p>Checks if game is running.</p>
     *
     * @return <code>true</code> if game is running, or <code>false</code> otherwise.
     */
    public boolean isGameRunning() {
        return this.isGameRunning.get();
    }

    /**
     * <p>Returns <code>isGameRunning</code> property.</p>
     *
     * @return <code>isGameRunning</code> property.
     */
    public BooleanProperty isGameRunningAsProperty() {
        return this.isGameRunning;
    }

    /**
     * <p>Pauses the game.</p>
     */
    public void pauseGame() {
        this.gameScreenController.pause();
    }

    /**
     * <p>Resumes the game.</p>
     */
    public void resumeGame() {
        this.gameScreenController.resume();
    }

    /**
     * <p>Restarts the game.</p>
     */
    public void restartGame() {
        this.gameScreenController.restart();
    }

    /**
     * <p>Switches to main menu.</p>
     */
    public void switchToMainMenu() {
        Stage.getWindows().get(0).getScene().setRoot(this.root);
        this.gameScreenController.stop();
        this.isGameRunning.set(false);
    }

    /**
     * <p>Switches scene of primary stage and starts the game.</p>
     */
    @FXML
    public void switchAndStartGame() {
        getStage().orElseThrow().getScene().setRoot(this.gameScreenController.getRoot());
        this.gameScreenController.start();
        this.isGameRunning.set(true);
    }

    /**
     * <p>Opens settings window.</p>
     */
    @FXML
    public void openSettings() {
        this.settingsController.openWindow();
    }

    /**
     * <p>Exits the game.</p>
     */
    @FXML
    public void exit() {
        Platform.exit();
    }

    /**
     * <p>Returns current stage.</p>
     *
     * @return Current stage.
     */
    @Override
    protected Optional<Stage> getStage() {
        return this.root.getScene() != null ? Optional.ofNullable((Stage) this.root.getScene().getWindow()) : Optional.empty();
    }
}
