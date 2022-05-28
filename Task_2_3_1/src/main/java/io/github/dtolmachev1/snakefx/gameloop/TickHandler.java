package io.github.dtolmachev1.snakefx.gameloop;

/**
 * <p>Interface for tick handler.</p>
 */
@FunctionalInterface
public interface TickHandler {
    /**
     * <p>Handles tick.</p>
     *
     * @param secondsSinceLastFrame Time since last frame in seconds.
     */
    void tick(float secondsSinceLastFrame);
}
