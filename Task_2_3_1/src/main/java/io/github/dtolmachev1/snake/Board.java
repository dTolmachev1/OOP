package io.github.dtolmachev1.snake;

import io.github.dtolmachev1.snake.cell.Cell;
import io.github.dtolmachev1.snake.cell.CellType;
import io.github.dtolmachev1.snake.cell.Environment;
import io.github.dtolmachev1.snake.cell.Food;

import java.util.Random;

/**
 * <p>Class for representing board.</p>
 */
public class Board {
    private Cell[][] cells;  // table with cells
    private final Random random;  // for pseudo-random number generation

    /* Default constructor to initialize internal structures */
    private Board() {
        this.random = new Random();
    }

    /**
     * <p>Constructor to initialize empty board with specified number of rows and columns</p>
     *
     * @param height Number of rows.
     * @param width Number of columns.
     */
    public Board(int height, int width) {
        this();
        this.cells = new Cell[height][width];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                this.cells[i][j] = new Cell(i, j, Environment.EMPTY);
            }
        }
    }

    /**
     * <p>Constructor to initialize new board with the specified one.</p>
     *
     * @param cells Table with cells to initialize newly created board.
     */
    public Board(Cell[][] cells) {
        this();
        this.cells = new Cell[cells.length][];
        for(int i = 0; i < cells.length; i++) {
            this.cells[i] = new Cell[cells[i].length];
            for(int j = 0; j < cells[i].length; j++) {
    this.cells[i][j] = cells[i][j].clone();
            }
        }
    }

    /**
     * <p>Returns table with cells.</p>
     *
     * @return Current table with cells.
     */
    public Cell[][] getCells() {
        Cell[][] cells = new Cell[this.cells.length][];
        for(int i = 0; i < this.cells.length; i++) {
            cells[i] = new Cell[this.cells[i].length];
            for(int j = 0; j < this.cells[i].length; j++) {
                cells[i][j] = this.cells[i][j].clone();
            }
        }
        return cells;
    }

    /**
     * <p>Returns number of rows in the table with cells.</p>
     *
     * @return Number of rows in the table with cells.
     */
    public int getHeight() {
        return this.cells.length;
    }

    /**
     * <p>Returns number of columns in the table with cells.</p>
     *
     * @return Number of columns in the table with cells.
     */
    public int getWidth() {
        return this.cells[0].length;
    }

    /**
     * <p>Checks if the specified cell is empty.</p>
     *
     * @param row Number of row.
     * @param column Number of column.
     * @return <code>true</code> if the specified cell is empty, or <code>false</code> otherwise.
     */
    public boolean isEmptyCell(int row, int column) {
        return isCellOfType(row, column, Environment.EMPTY);
    }

    /**
     * <p>Checks if the specified cell is obstacle.</p>
     *
     * @param row Number of row.
     * @param column Number of column.
     * @return <code>true</code> if the specified cell is obstacle, or <code>false</code> otherwise.
     */
    public boolean isObstacleCell(int row, int column) {
        return isCellOfType(row, column, Environment.OBSTACLE);
    }

    /**
     * <p>Checks if the specified cell is food.</p>
     *
     * @param row Number of row.
     * @param column Number of column.
     * @return <code>true</code> if the specified cell is food, or <code>false</code> otherwise.
     */
    public boolean isFoodCell(int row, int column) {
        return isCellOfType(row, column, Food.FOOD);
    }

    /**
     * <p>Returns cell at the specified position.</p>
     *
     * @param row Number of row.
     * @param column Number of column.
     * @return Cell at the specified position, or <code>null</code> if it doesn't exist.
     */
    public Cell getCell(int row, int column) {
        return row < this.cells.length && column < this.cells[row].length ? this.cells[row][column] : null;
    }

    /**
     * <p>Sets given cell at the specified position.</p>
     *
     * @param cell Cell to be set.
     * @return <code>true</code> if table with cells has changed as a result of this call, or <code>false</code> otherwise.
     */
    public boolean setCell(Cell cell) {
        if(cell.row() < this.cells.length && cell.column() < this.cells[cell.row()].length && this.cells[cell.row()][cell.column()].cellType() != cell.cellType()) {
            this.cells[cell.row()][cell.column()] = cell;
            return true;
        }
        return false;
    }

    /**
     * <p>Generates random food cell.</p>
     *
     * @return Newly generated cell.
     */
    public Cell generateFood() {
        return generateCellOfType(Food.FOOD);
    }

    /**
     * <p>Generates random obstacle cell.</p>
     *
     * @return Newly generated cell.
     */
    public Cell generateObstacle() {
        return generateCellOfType(Environment.OBSTACLE);
    }

    /**
     * <p>Clears specified cell.</p>
     *
     * @param row Number of row.
     * @param column Number of column.
     * @return <code>true</code> if table with cells has changed as a result of this call, or <code>false</code> otherwise.
     */
    public boolean clear(int row, int column) {
        if(row < this.cells.length && column < this.cells[row].length && this.cells[row][column].cellType() != Environment.EMPTY) {
            setCell(new Cell(row, column, Environment.EMPTY));
            return true;
        }
        return false;
    }

    /**
     * <p>Clears all board.</p>
     */
    public void clear() {
        for(int i = 0; i < this.cells.length; i++) {
            for(int j = 0; j < this.cells[i].length; j++) {
                clear(i, j);
            }
        }
    }

    /* Checks if the specified cell of the given type */
    private boolean isCellOfType(int row, int column, CellType cellType) {
        return this.cells[row][column].cellType() == cellType;
    }

    /* Generates random cell with the specified type */
    private Cell generateCellOfType(CellType cellType) {
        return new Cell(this.random.nextInt(getHeight()), this.random.nextInt(getWidth()), cellType);
    }
}
