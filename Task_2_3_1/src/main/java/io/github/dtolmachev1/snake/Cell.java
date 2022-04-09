package io.github.dtolmachev1.snake;

/**
 * <p>Class for representing single cell.</p>
 */
public class Cell {
    private final int row;  // number of row
    private final int column;  // number of column
    private CellType type;  // current type of the cell

    /**
     * <p>Constructor to initialize new cell with specified values;</p>
     *
     * @param row Number of row.
     * @param column Number of column.
     * @param type Type of the cell.
     */
    public Cell(int row, int column, CellType type) {
        this.row = row;
        this.column = column;
        this.type = type;
    }

    /**
     * <p>Returns number of the cell's row.</p>
     *
     * @return Number of row for this cell.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * <p>Returns number of the cell's column.</p>
     *
     * @return Number of column for this cell.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * <p>Returns current cell's type.</p>
     *
     * @return Current type for this cell.
     */
    public CellType getType() {
        return this.type;
    }


    /**
     * <p>Sets type of this cell to the specified one.</p>
     *
     * @param type Cell type to be set.
     */
    public void setType(CellType type) {
        this.type = type;
    }
}
