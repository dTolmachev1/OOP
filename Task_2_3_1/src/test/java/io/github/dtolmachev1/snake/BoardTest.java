package io.github.dtolmachev1.snake;

import io.github.dtolmachev1.snake.cell.Cell;
import io.github.dtolmachev1.snake.cell.Environment;
import io.github.dtolmachev1.snake.cell.Food;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    private static Board board;  // board for testing
    private static final int HEIGHT = 32;  // number of rows
    private static final int WIDTH = 32;  // number of columns

    @BeforeEach
    void prepare() {
        board = new Board(HEIGHT, WIDTH);
    }

    @Test
    @DisplayName("Height")
    void getHeight_Test() {
        Assertions.assertEquals(board.getHeight(), HEIGHT);
    }

    @Test
    @DisplayName("Width")
    void getWidth_Test() {
        Assertions.assertEquals(board.getWidth(), WIDTH);
    }

    @Test
    @DisplayName("Is empty cell")
    void isEmptyCell_Test() {
        Assertions.assertTrue(board.isEmptyCell(0, 0));
        Assertions.assertTrue(board.setCell(new Cell(0, 0, Environment.OBSTACLE)));
        Assertions.assertFalse(board.isEmptyCell(0, 0));
    }

    @Test
    @DisplayName("Is obstacle cell")
    void isObstacleCell_Test() {
        Assertions.assertFalse(board.isObstacleCell(0, 0));
        Assertions.assertTrue(board.setCell(new Cell(0, 0, Environment.OBSTACLE)));
        Assertions.assertTrue(board.isObstacleCell(0, 0));
    }

    @Test
    @DisplayName("Is food cell")
    void isFoodCell_Test() {
        Assertions.assertFalse(board.isFoodCell(0, 0));
        Assertions.assertTrue(board.setCell(new Cell(0, 0, Food.FOOD)));
        Assertions.assertTrue(board.isFoodCell(0, 0));
    }

    @Test
    @DisplayName("Generate food")
    void generateFood_Test() {
        Cell food = board.generateFood();
        Assertions.assertTrue(food.row() < board.getHeight());
        Assertions.assertTrue(food.column() < board.getWidth());
        Assertions.assertEquals(food.cellType(), Food.FOOD);
    }

    @Test
    @DisplayName("Generate obstacle")
    void generateObstacle_Test() {
        Cell obstacle = board.generateObstacle();
        Assertions.assertTrue(obstacle.row() < board.getHeight());
        Assertions.assertTrue(obstacle.column() < board.getWidth());
        Assertions.assertEquals(obstacle.cellType(), Environment.OBSTACLE);
    }

    @Test
    @DisplayName("Clear single cell")
    void clear_SingleCell() {
        Assertions.assertFalse(board.clear(0, 0));
        Assertions.assertTrue(board.setCell(new Cell(0, 0, Environment.OBSTACLE)));
        Assertions.assertFalse(board.isEmptyCell(0, 0));
        Assertions.assertTrue(board.clear(0, 0));
        Assertions.assertTrue(board.isEmptyCell(0, 0));
    }

    @Test
    @DisplayName("Clear all cells")
    void clear_AllCells() {
        for(int i = 0; i < board.getHeight(); i++) {
            for(int j = 0; j < board.getWidth(); j++) {
                Assertions.assertTrue(board.setCell(new Cell(i, j, Environment.OBSTACLE)));
                Assertions.assertFalse(board.isEmptyCell(i, j));
            }
        }
        board.clear();
        for(int i = 0; i < board.getHeight(); i++) {
            for(int j = 0; j < board.getWidth(); j++) {
                Assertions.assertTrue(board.isEmptyCell(i, j));
            }
        }
    }
}