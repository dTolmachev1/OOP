package io.github.dtolmachev1.snakefx;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * <p>JavaFX application class.</p>
 */
public class SnakeApplication extends Application {
    private Injector injector;
    @Inject
    @Named("MainMenu")
    private Provider<FXMLLoader> mainMenuLoaderProvider;
    @Inject
    private Logger logger;

    /**
     * <p>performs initialization prior to the actual starting of the application.</p>
     */
    @Override
    public void init() {
        this.injector = Guice.createInjector(new ApplicationModule());
        this.injector.injectMembers(this);
        this.logger.info("Injected");
    }

    /**
     * <p>The main entry point for JavaFX application.</p>
     *
     * @param primaryStage The primary stage for this application, onto which the application scene can be set.
     * @throws IOException If something goes wrong.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.logger.info("Parameters: " + getParameters().getRaw());
        primaryStage.setScene(new Scene(this.mainMenuLoaderProvider.get().load()));
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /**
     * <p>This method is called when the application should stop, and provides a convenient place to prepare for application exit and destroy resources.</p>
     */
    @Override
    public void stop() {
        this.logger.info("Exiting");
    }
}
