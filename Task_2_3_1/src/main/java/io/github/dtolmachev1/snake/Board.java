package io.github.dtolmachev1.snake;

import java.util.stream.IntStream;

/**
 * <p>Class for representing board.</p>
 */
public class Board {
    private final Cell[][] map;  // table with cells

    /**
     * <p>Constructor to initialize empty board with specified number of rows and columns.</p>
     *
     * @param rowCount Number of rows.
     * @param columnCount Number of columns.
     */
    public Board(int rowCount, int columnCount) {
        this.map = new Cell[rowCount][columnCount];
        IntStream.range(0, rowCount).forEach(i -> IntStream.range(0, columnCount).forEach(j -> this.map[i][j] = new Cell(i, j, CellType.EMPTY)));
    }

    /**
     * <p>Constructor to initialize new board with the specified one.</p>
     *
     * @param map Map to initialize newly created board.
     */
    public Board(Cell[][] map) {
        this.map = new Cell[map.length][];
        IntStream.range(0, map.length).forEach(i -> this.map[i] = map[i].clone());
    }

    public Cell[][] getMap() {
        Cell[][] map = new Cell[this.map.length][];
        IntStream.range(0, this.map.length).forEach(i -> map[i] = this.map[i].clone());
        return map;
    }

    /**
     * <p>Returns cell at the specified position.</p>
     *
     * @param row Number of row.
     * @param column Number of column.
     * @return Cell at the specified position, or <code>null</code> if it doesn't exist.
     */
    public Cell getCell(int row, int column) {
        return row < this.map.length && column < this.map[row].length ? this.map[row][column] : null;
    }

    /**
     * <p>Returns cell above to the specified one.</p>
     *
     * @param cell Given cell.
     * @return Cell above to the specified one, or <code>null</code> if it doesn't exist.
     */
    public Cell getCellAbove(Cell cell) {
        return cell.getRow() - 1 >= 0 ? this.map[cell.getRow()-1][cell.getColumn()] : null;
    }

    /**
     * <p>Returns cell below to the specified one.</p>
     *
     * @param cell Given cell.
     * @return Cell below to the specified one, or <code>null</code> if it doesn't exist.
     */
    public Cell getCellBelow(Cell cell) {
        return cell.getRow() + 1 < this.map.length ? this.map[cell.getRow()+1][cell.getColumn()] : null;
    }

    /**
     * <p>Returns cell left to the specified one.</p>
     *
     * @param cell Given cell.
     * @return Cell left to the specified one, or <code>null</code> if it doesn't exist.
     */
    public Cell getCellLeft(Cell cell) {
        return cell.getColumn() - 1 >= 0 ? this.map[cell.getRow()][cell.getColumn()-1] : null;
    }

    /**
     * <p>Returns cell right to the specified one.</p>
     *
     * @param cell Given cell.
     * @return Cell right to the specified one, or <code>null</code> if it doesn't exist.
     */
    public Cell getCellRight(Cell cell) {
        return cell.getColumn() + 1 < this.map[cell.getRow()].length ? this.map[cell.getRow()][cell.getColumn()+1] : null;
    }
}
