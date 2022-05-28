package io.github.dtolmachev1.snake.coordinates;

/**
 * <p>Some simple operations with coordinates.</p>
 *
 * @param <T> Type of coordinates to be returned.
 */
public class Operations<T extends Coordinates> {
    private final CoordinatesFactory<T> factory;  // for generating coordinates instances.

    /**
     * <p>Default constructor to initialize Operations instance with given factory.</p>
     *
     * @param factory Coordinates factory.
     */
    public Operations(CoordinatesFactory<T> factory) {
        this.factory = factory;
    }

    /**
     * <p>Creates coordinates instance with some default values.</p>
     *
     * @return Newly created instance.
     */
    public T unit() {
        return this.factory.getCoordinatesInstance(1, 1);
    }

    /**
     * <p>Computes sum of the given coordinates.</p>
     *
     * @param a First coordinate.
     * @param b Second coordinate.
     * @return Sum of the given coordinates.
     */
    public T add(Coordinates a, Coordinates b) {
        return this.factory.getCoordinatesInstance(a.row() + b.row(), a.column() + b.column());
    }

    /**
     * <p>Computes product of the given coordinates.</p>
     *
     * @param a First coordinate.
     * @param b Second coordinate.
     * @return Product of the given coordinates
     */
    public T multiply(Coordinates a, Coordinates b) {
        return this.factory.getCoordinatesInstance(a.row() * b.row(), a.column() * b.column());
    }
}
