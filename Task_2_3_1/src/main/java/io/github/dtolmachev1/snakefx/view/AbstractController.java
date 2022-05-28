package io.github.dtolmachev1.snakefx.view;

import javafx.stage.Stage;

import java.util.Optional;

/**
 * <p>Abstract class for controller.</p>
 */
public abstract class AbstractController {
    /**
     * <p>Returns current stage.</p>
     *
     * @return Current stage.
     */
    protected abstract Optional<Stage> getStage();
}
