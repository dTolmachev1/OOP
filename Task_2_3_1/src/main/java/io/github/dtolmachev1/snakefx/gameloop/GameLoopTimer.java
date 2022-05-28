package io.github.dtolmachev1.snakefx.gameloop;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * <p>Game loop based of JavaFX animation timer.</p>
 */
public class GameLoopTimer extends AnimationTimer {
    private final DoubleProperty animationDuration;
    private long pauseStart;
    private long animationStart;
    private long lastFrameTime;
    private boolean isPaused;
    private boolean isResumed;
    private boolean pauseScheduled;
    private boolean resumeScheduled;
    private boolean restartScheduled;
    private TickHandler tickHandler;
    private int count;
    private int skip;

    /**
     * <p>Creates a new game loop timer.</p>
     */
    public GameLoopTimer() {
        super();
        this.animationDuration = new SimpleDoubleProperty(0);
        this.tickHandler = secondsSinceLastFrame -> {};
        this.count = 0;
    }

    /**
     * <p>Checks if game is paused.</p>
     *
     * @return <code>true</code> if game is paused, or <code>false</code> otherwise.
     */
    public boolean isPaused() {
        return this.isPaused;
    }

    /**
     * <p>Checks if game is resumed.</p>
     *
     * @return <code>true</code> if game is resumed, or <code>false</code> otherwise.
     */
    public boolean isResumed() {
        return this.isResumed;
    }

    /**
     * <p>Pauses the game.</p>
     */
    public void pause() {
        if(!this.isPaused) {
            this.pauseScheduled = true;
        }
    }

    /**
     * <p>Resumes the game.</p>
     */
    public void resume() {
        if(this.isPaused) {
            this.resumeScheduled = true;
        }
    }

    /**
     * <p>Sets fps for game loop timer.</p>
     *
     * @param fps Fps value to be set.
     * @throws IllegalArgumentException If fps value is incorrect.
     */
    public void setFps(int fps) throws IllegalArgumentException {
        if(fps < 1 || fps > 60) {
            throw new IllegalArgumentException();
        }
        this.skip = 60 / fps;
    }

    /**
     * <p>Sets tick handler for game loop timer.</p>
     *
     * @param tickHandler Tick handler to be set.
     */
    public void setTickHandler(TickHandler tickHandler) {
        this.tickHandler = tickHandler;
    }

    /**
     * <p>Starts the game loop timer.</p>
     */
    @Override
    public void start() {
        super.start();
        this.isResumed = true;
        this.restartScheduled = true;
    }

    /**
     * <p>Stops the game loop timer.</p>
     */
    @Override
    public void stop() {
        super.stop();
        this.pauseStart = 0;
        this.isPaused = false;
        this.isResumed = false;
        this.pauseScheduled = false;
        this.resumeScheduled = false;
        this.animationDuration.set(0);
    }

    /**
     * <p>This method is going to be called in every frame while the <code>GameLoopTimer</code> is active.</p>
     *
     * @param now The timestamp of the current frame given in nanoseconds.
     */
    @Override
    public void handle(long now) {
        if(this.pauseScheduled) {
            this.pauseStart = now;
            this.isPaused = true;
            this.pauseScheduled = false;
        }
        if(this.resumeScheduled) {
            this.animationStart += now - this.pauseStart;
            this.isPaused = false;
            this.resumeScheduled = false;
        }
        if(this.restartScheduled) {
            this.isPaused = false;
            this.animationStart = now;
            this.restartScheduled = false;
        }
        if(!this.isPaused) {
            if(this.count == this.skip) {
                this.count = 0;
                this.animationDuration.set((now - this.animationStart) / 1e9);
                float secondsSinceLastFrame = (float) ((now - this.lastFrameTime) / 1e9);
                this.lastFrameTime = now;
                this.tickHandler.tick(secondsSinceLastFrame);
            } else this.count++;
        }
    }
}
