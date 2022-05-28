package io.github.dtolmachev1.snake.cell;

/**
 * <p>Type of cell with some score, requires enum-based implementation.</p>
 */
public interface ScoreCellType extends CellType {
    /**
     * <p>Returns score value of this cell.</p>
     *
     * @return Score value of this cell.
     */
    int score();
}
