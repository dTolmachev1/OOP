package io.github.dtolmachev1.snake.coordinates;

/**
 * <p>For storing direction and velocity.</p>
 */
public enum VelocityVector implements Coordinates {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int verticalVelocity;  // for vertical velocity
    private final int horizontalVelocity;  // for horizontalVelocity

    /**
     * <p>Default constructor to initialize vector with given velocity.</p>
     *
     * @param verticalVelocity Vertical velocity.
     * @param horizontalVelocity Horizontal velocity.
     */
    VelocityVector(int verticalVelocity, int horizontalVelocity) {
        this.verticalVelocity = verticalVelocity;
        this.horizontalVelocity = horizontalVelocity;
    }

    /**
     * <p>Returns vertical velocity.</p>
     *
     * @return Vertical velocity.
     */
    @Override
    public int row() {
        return this.verticalVelocity;
    }

    /**
     * <p>Returns horizontal velocity.</p>
     *
     * @return Horizontal velocity.
     */
    @Override
    public int column() {
        return this.horizontalVelocity;
    }
}
