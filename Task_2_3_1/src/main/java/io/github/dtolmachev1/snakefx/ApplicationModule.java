package io.github.dtolmachev1.snakefx;

import io.github.dtolmachev1.snake.cell.Cell;
import io.github.dtolmachev1.snake.cell.SnakeNode;
import io.github.dtolmachev1.snake.coordinates.VelocityVector;
import io.github.dtolmachev1.snake.Board;
import io.github.dtolmachev1.snake.Snake;
import io.github.dtolmachev1.snakefx.view.MainViewController;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;

import javafx.fxml.FXMLLoader;

import javax.inject.Named;
import javax.inject.Singleton;

import java.util.prefs.Preferences;

/**
 * <p>Class for snake application module.</p>
 */
public class ApplicationModule extends AbstractModule {
    /**
     * <p>Configures a Binder via the exposed methods.</p>
     */
    @Override
    protected void configure() {
        bind(MainViewController.class).in(Singleton.class);
    }

    /**
     * <p>Provides <code>FXMLLoader</code> for main menu.</p>
     *
     * @param injector Controller factory for <code>FXMLLoader</code>.
     * @return <code>FXMLLoader</code> for main menu.
     */
    @Provides
    @Named("MainMenu")
    private FXMLLoader mainMenuLoader(Injector injector) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("MainView.fxml"));
        fxmlLoader.setControllerFactory(injector::getInstance);
        return fxmlLoader;
    }

    /**
     * <p>Provides instance of board with specified height and width.</p>
     *
     * @param preferences Game preferences.
     * @return Instance of board with specified height and width.
     */
    @Provides
    private Board board(Preferences preferences) {
        return new Board(preferences.getInt("HEIGHT", 32), preferences.getInt("WIDTH", 32));
    }

    /**
     * <p>Provides instance of snake.</p>
     *
     * @param board Instance of board for snake.
     * @return Instance of snake.
     */
    @Provides
    private Snake snake(Board board) {
        return new Snake(new Cell(0, 0, SnakeNode.SNAKE_NODE), VelocityVector.RIGHT, board.getHeight(), board.getWidth());
    }

    /**
     * <p>Provides game preferences.</p>
     *
     * @return Game preferences.
     */
    @Provides
    @Singleton
    Preferences preferences() {
        return Preferences.userNodeForPackage(SnakeApplication.class);
    }
}
