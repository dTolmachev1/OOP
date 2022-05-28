package io.github.dtolmachev1.snakefx.view;

import io.github.dtolmachev1.snake.cell.Cell;
import io.github.dtolmachev1.snake.coordinates.VelocityVector;
import io.github.dtolmachev1.snake.Board;
import io.github.dtolmachev1.snake.Snake;
import io.github.dtolmachev1.snakefx.gameloop.GameLoopTimer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Provider;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * <p>Game screen controller.</p>
 */
public class GameScreenController extends AbstractController implements Initializable {
    private Provider<Board> boardProvider;
    private Provider<Snake> snakeProvider;
    private GameLoopTimer gameLoopTimer;
    private final IntegerProperty score;
    @FXML
    private VBox root;
    @FXML
    private Pane container;
    @FXML
    private Label scoreValue;
    @Inject
    private Provider<MainViewController> mainViewControllerProvider;
    @Inject
    private Preferences preferences;
    private Board board;
    private Snake snake;
    private double scale;
    private int foodItems;
    private int finalScore;
    private int foodItemsCount;

    /**
     * <p>Default constructor to initialize internal structures.</p>
     */
    public GameScreenController() {
        this.score = new SimpleIntegerProperty(0);
        this.foodItemsCount = 0;
    }

    /**
     * <p>Constructor to initialize <code>GameScreenController</code> with specified values.</p>
     *
     * @param boardProvider <code>BoardProvider</code> to be set.
     * @param snakeProvider <code>SnakeProvider</code> to be set.
     * @param gameLoopTimer <code>GameLoopTimer</code> to be set.
     */
    @Inject
    public GameScreenController(Provider<Board> boardProvider, Provider<Snake> snakeProvider, GameLoopTimer gameLoopTimer) {
        this();
        this.boardProvider = boardProvider;
        this.snakeProvider = snakeProvider;
        this.gameLoopTimer = gameLoopTimer;
    }

    /**
     * <p>Called to initialize a controller after its root element has been completely processed.</p>
     *
     * @param location The location used to resolve relative paths for the root object, or <code>null</code> if the location is not known.
     * @param resources The resources used to localize the root object, or <code>null</code> if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindProperties();
        addKeyHandlers();
        setupGameLoopTimer();
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
     * <p>Pauses the game.</p>
     */
    public void pause() {
        this.gameLoopTimer.pause();
    }

    /**
     * <p>Resumes the game.</p>
     */
    public void resume() {
        this.gameLoopTimer.resume();
    }

    /**
     * <p>Restarts the game.</p>
     */
    public void restart() {
        initValues();
        updateView();
        this.gameLoopTimer.start();
    }

    /**
     * <p>Starts the game.</p>
     */
    public void start() {
        restart();
    }

    /**
     * <p>Stops the game.</p>
     */
    public void stop() {
        this.gameLoopTimer.stop();
    }

    /**
     * <p>Returns current stage.</p>
     *
     * @return Current stage.
     */
    @Override
    protected Optional<Stage> getStage() {
        return this.container.getScene() != null ? Optional.ofNullable((Stage) this.container.getScene().getWindow()) : Optional.empty();
    }

    /* Binds properties */
    private void bindProperties() {
        this.scoreValue.textProperty().bind(this.score.asString());
    }

    /* Initializes values */
    private void initValues() {
        this.score.set(0);
        this.foodItemsCount = 0;
        this.container.getChildren().clear();
        this.scale = this.preferences.getDouble("SCALE", 1);
        this.foodItems = this.preferences.getInt("FOOD_ITEMS", 12);
        this.finalScore = this.preferences.getInt("FINAL_SCORE", Integer.MAX_VALUE);
        this.board = this.boardProvider.get();
        this.snake = this.snakeProvider.get();
        generateObstacles();
        generateFoodItems();
    }

    /* Adds key handlers */
    private void addKeyHandlers() {
        this.container.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                newValue.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);
            }
        });
    }

    /* Initializes game loop timer */
    private void setupGameLoopTimer() {
        this.gameLoopTimer.setFps(3);
        this.gameLoopTimer.setTickHandler(this::tick);
    }

    /* Updates the game screen */
    private void updateView() {
        this.container.getChildren().clear();
        for(int i = 0; i < this.board.getHeight(); i++) {
            for(int j = 0; j < this.board.getWidth(); j++) {
                Cell cell = this.board.getCell(i, j);
                Rectangle rectangle = createRectangle(cell);
                switch (cell.cellType().toString()) {
                    case "EMPTY" -> rectangle.setStyle("-fx-fill: black;");
                    case "OBSTACLE" -> rectangle.setStyle("-fx-fill: gray;");
                    case "FOOD" -> rectangle.setStyle("-fx-fill: LimeGreen;");
                }
                this.container.getChildren().add(rectangle);
            }
        }
        for(Cell snakeNode : this.snake.getNodes()) {
            Rectangle rectangle = createRectangle(snakeNode);
            rectangle.setStyle("-fx-fill: blue;");
            this.container.getChildren().add(rectangle);
        }
    }

    /* Handles tick */
    private void tick(float secondsSinceLastFrame) {
        if(this.score.get() >= this.finalScore || !this.snake.isPossibleMove() || this.board.isObstacleCell(this.snake.getHead().row(), this.snake.getHead().column())) {
            restart();
            return;
        }
        if(this.board.isFoodCell(this.snake.getHead().row(), this.snake.getHead().column())) {
            this.score.set(this.score.get() + 1);
            this.board.clear(this.snake.getHead().row(), this.snake.getHead().column());
            this.foodItemsCount--;
            this.snake.grow();
            generateFoodItems();
        } else this.snake.move();
        updateView();
    }

    /* Handles key presses */
    private void handleKeyPress(KeyEvent event) {
        switch(event.getCode()) {
            case RIGHT -> {
                if(this.snake.getVelocityVector() != VelocityVector.LEFT) {
                    this.snake.setVelocityVector(VelocityVector.RIGHT);
                }
            }
            case LEFT -> {
                if(this.snake.getVelocityVector() != VelocityVector.RIGHT) {
                    this.snake.setVelocityVector(VelocityVector.LEFT);
                }
            }
            case UP -> {
                if(this.snake.getVelocityVector() != VelocityVector.DOWN) {
                    this.snake.setVelocityVector(VelocityVector.UP);
                }
            }
            case DOWN -> {
                if(this.snake.getVelocityVector() != VelocityVector.UP) {
                    this.snake.setVelocityVector(VelocityVector.DOWN);
                }
            }
            case ESCAPE -> {
                if(!this.gameLoopTimer.isPaused()) {
                    pause();
                    this.mainViewControllerProvider.get().openSettings();
                } else resume();
            }
        }
    }

    /* Generates random food items */
    private void generateFoodItems() {
        while(this.foodItemsCount < this.foodItems) {
            Cell foodItem = this.board.generateFood();
            if(!this.board.isObstacleCell(foodItem.row(), foodItem.column()) && this.snake.getNodes().stream().noneMatch(node -> node.intersects(foodItem))) {
                this.board.setCell(foodItem);
                this.foodItemsCount++;
            }
        }
    }

    /* Generates random obstacles */
    private void generateObstacles() {
        for(int i = 0; i < Math.min(this.board.getHeight(), this.board.getWidth()); i++) {
            Cell obstacle = this.board.generateObstacle();
            if(!this.board.isFoodCell(obstacle.row(), obstacle.column()) && this.snake.getNodes().stream().noneMatch(node -> node.intersects(obstacle))) {
                this.board.setCell(obstacle);
            } else i--;
        }
    }

    /* Creates rectangle with size and coordinates relatively to window size */
    private Rectangle createRectangle(Cell cell) {
        Rectangle rectangle = new Rectangle();
        double side = Math.min(this.container.getWidth() / this.board.getWidth(), this.container.getHeight() / this.board.getHeight()) * this.scale;
        rectangle.setWidth(side);
        rectangle.setHeight(side);
        rectangle.setX(side * cell.column());
        rectangle.setY(side * cell.row());
        return rectangle;
    }
}
