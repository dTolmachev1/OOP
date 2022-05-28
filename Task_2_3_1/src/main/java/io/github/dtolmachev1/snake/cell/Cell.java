package io.github.dtolmachev1.snake.cell;

import io.github.dtolmachev1.snake.coordinates.Coordinates;

/**
 * <p>Single cell.</p>
 *
 * @param row Number of row.
 * @param column Number of column.
 * @param cellType Type of the cell.
 */
public record Cell(int row, int column, CellType cellType) implements Coordinates{
    /**
     * <p>Creates a copy of this cell.</p>
     *
     * @return A copy of this cell.
     */
    @Override
    public Cell clone() {
        Cell cell = null;  // for storing cloned object
        try {
            cell = (Cell) super.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cell;
    }
}
