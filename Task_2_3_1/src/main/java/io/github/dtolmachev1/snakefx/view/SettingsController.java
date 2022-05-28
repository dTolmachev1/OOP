package io.github.dtolmachev1.snakefx.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.inject.Inject;
import javax.inject.Provider;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * <p>Settings window controller.</p>
 */
public class SettingsController extends AbstractController implements Initializable {
    @FXML
    private VBox root;
    @FXML
    private VBox inGameMenu;
    @FXML
    private Label widthValue;
    @FXML
    private Slider widthSlider;
    @FXML
    private Label heightValue;
    @FXML
    private Slider heightSlider;
    @FXML
    private Label scaleValue;
    @FXML
    private Slider scaleSlider;
    @FXML
    private Label foodItemsValue;
    @FXML
    private Slider foodItemsSlider;
    @FXML
    private ToggleGroup gameMode;
    @FXML
    private Label finalScoreValue;
    @FXML
    private Slider finalScoreSlider;
    @FXML
    private RadioButton goalBtn;
    @FXML
    private RadioButton unlimitedBtn;
    @Inject
    private Provider<MainViewController> mainViewControllerProvider;
    @Inject
    private Preferences preferences;

    /**
     * <p>Called to initialize a controller after its root element has been completely processed.</p>
     *
     * @param location The location used to resolve relative paths for the root object, or <code>null</code> if the location is not known.
     * @param resources The resources used to localize the root object, or <code>null</code> if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindProperties();
        initValues();
        addKeyHandlers();
    }

    /**
     * <p>Returns root node.</p>
     *
     * @return Root node.
     */
    public VBox getRoot() {
        return this.root;
    }

    /**
     * <p>Opens settings window.</p>
     */
    public void openWindow() {
        Stage stage = getStage().orElseGet(() -> new Stage(StageStyle.UNDECORATED));
        if(stage.getOwner() == null) {
            stage.initOwner(Stage.getWindows().get(0));
        }
        stage.setScene(getRoot().getScene() != null ? getRoot().getScene() : new Scene(getRoot()));
        stage.show();
    }

    /**
     * <p>Closes settings window.</p>
     */
    public void closeWindow() {
        getStage().orElseThrow().hide();
    }

    /**
     * <p>Resumes the game.</p>
     */
    @FXML
    public void resume() {
        closeWindow();
        this.mainViewControllerProvider.get().resumeGame();
    }

    /**
     * <p>Restarts the game.</p>
     */
    @FXML
    public void restart() {
        closeWindow();
        this.mainViewControllerProvider.get().restartGame();
    }

    /**
     * <p>Returns to main menu.</p>
     */
    @FXML
    public void backToMainMenu() {
        closeWindow();
        this.mainViewControllerProvider.get().switchToMainMenu();
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

    /* Binds properties */
    private void bindProperties() {
        this.inGameMenu.visibleProperty().bind(this.mainViewControllerProvider.get().isGameRunningAsProperty());
        this.widthValue.setText(String.valueOf(this.widthSlider.getValue()));
        this.widthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.widthSlider.setValue(newValue.intValue());
            this.widthValue.setText(String.valueOf(newValue.intValue()));
            this.preferences.putInt("WIDTH", newValue.intValue());
        });
        this.heightValue.setText(String.valueOf(this.heightSlider.getValue()));
        this.heightSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.heightSlider.setValue(newValue.intValue());
            this.heightValue.setText(String.valueOf(newValue.intValue()));
            this.preferences.putInt("HEIGHT", newValue.intValue());
        });
        this.scaleValue.setText(String.valueOf(this.scaleSlider.getValue()));
        this.scaleSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.scaleSlider.setValue(newValue.intValue());
            this.scaleValue.setText(String.valueOf(newValue.doubleValue() / 100));
            this.preferences.putDouble("SCALE", newValue.doubleValue() / 100);
        });
        this.foodItemsValue.setText(String.valueOf(this.foodItemsSlider.getValue()));
        this.foodItemsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.foodItemsSlider.setValue(newValue.intValue());
            this.foodItemsValue.setText(String.valueOf(newValue.intValue()));
            this.preferences.putInt("FOOD_ITEMS", newValue.intValue());
        });
        this.finalScoreValue.setText(String.valueOf(this.finalScoreSlider.getValue()));
        this.finalScoreSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.finalScoreSlider.setValue(newValue.intValue());
            this.finalScoreValue.setText(String.valueOf(newValue.intValue()));
            this.preferences.putInt("FINAL_SCORE", newValue.intValue());
        });
        this.gameMode.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == goalBtn) {
                this.preferences.putInt("FINAL_SCORE", (int) this.finalScoreSlider.getValue());
            } else preferences.putInt("FINAL_SCORE", Integer.MAX_VALUE);
        });
    }

    /* Initializes values */
    private void initValues() {
        this.widthSlider.setValue(this.preferences.getInt("WIDTH", 32));
        this.heightSlider.setValue(this.preferences.getInt("HEIGHT", 32));
        this.scaleSlider.setValue(100 * this.preferences.getDouble("SCALE", 1));
        this.foodItemsSlider.setValue(this.preferences.getInt("FOOD_ITEMS", 12));
        this.finalScoreSlider.setValue(this.preferences.getInt("FINAL_SCORE", 12));
        if(this.preferences.getInt("FINAL_SCORE", Integer.MAX_VALUE) == Integer.MAX_VALUE) {
            this.unlimitedBtn.setSelected(true);
        } else this.goalBtn.setSelected(true);
    }

    /* Adds key handlers */
    private void addKeyHandlers() {
        getRoot().sceneProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                newValue.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if(event.getCode() == KeyCode.ESCAPE) {
                        resume();
                    }
                });
            }
        });
    }
}
