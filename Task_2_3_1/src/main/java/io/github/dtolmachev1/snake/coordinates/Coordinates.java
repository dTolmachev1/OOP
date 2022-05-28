package io.github.dtolmachev1.snake.coordinates;

/**
 * <p>Cell coordinates.</p>
 */
public interface Coordinates {
    /**
     * <p>Checks if given coordinate intersects with another.</p>
     *
     * @param other Coordinate to be checked.
     * @return <code>true</code> in coordinates are equal, or <code>false</code> otherwise.
     */
    default boolean intersects(Coordinates other) {
        return this.row() == other.row() && this.column() == other.column();
    }

    /**
     * <p>Returns number of row.</p>
     *
     * @return Number of row.
     */
    int row();

    /**
     * <p>Returns number of column.</p>
     *
     * @return Number of column.
     */
    int column();
}
