package io.github.dtolmachev1.snake.coordinates;

/**
 * <p>Factory for cell coordinates.</p>
 *
 * @param <T> Type of coordinates to be created.
 */
public interface CoordinatesFactory<T extends Coordinates> {
    /**
     * <p>Creates new instance of coordinates object..</p>
     *
     * @param row Number of row.
     * @param column Number of column.
     * @return Newly created object.
     */
    T getCoordinatesInstance(int row, int column);
}
