package io.github.dtolmachev1.snake;

import io.github.dtolmachev1.snake.cell.Cell;
import io.github.dtolmachev1.snake.cell.SnakeNode;
import io.github.dtolmachev1.snake.coordinates.VelocityVector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SnakeTest {
    private static Snake snake;  // snake for testing
    private static final int BOTTOM_BORDER = 32;  // bottom border
    private static final int RIGHT_BORDER = 32;  // right border

    @BeforeEach
    void prepare() {
        snake = new Snake(new Cell(0, 0, SnakeNode.SNAKE_NODE), VelocityVector.RIGHT, BOTTOM_BORDER, RIGHT_BORDER);
    }

    @Test
    @DisplayName("Length")
    void getLength_Test() {
        Assertions.assertEquals(snake.getLength(), 1);
        snake.grow();
        Assertions.assertEquals(snake.getLength(), 2);
    }

    @Test
    @DisplayName("Is possible move")
    void isPossibleMove_Test() {
        Assertions.assertTrue(snake.isPossibleMove());
        Assertions.assertEquals(snake.getLength(), 1);
        for(int i = 0; i < RIGHT_BORDER - 1; i++) {
            snake.grow();
        }
        Assertions.assertEquals(snake.getLength(), RIGHT_BORDER);
        snake.setVelocityVector(VelocityVector.DOWN);
        for(int i = 0; i < BOTTOM_BORDER - 1; i++) {
            snake.grow();
        }
        Assertions.assertEquals(snake.getLength(), RIGHT_BORDER + BOTTOM_BORDER - 1);
        snake.setVelocityVector(VelocityVector.LEFT);
        for(int i = RIGHT_BORDER - 2; i >= 0; i--) {
            snake.grow();
        }
        Assertions.assertEquals(snake.getLength(), RIGHT_BORDER + BOTTOM_BORDER + RIGHT_BORDER - 2);
        snake.setVelocityVector(VelocityVector.UP);
        for(int i = BOTTOM_BORDER - 2; i > 0; i--) {
            snake.grow();
        }
        Assertions.assertEquals(snake.getLength(), RIGHT_BORDER + BOTTOM_BORDER + RIGHT_BORDER + BOTTOM_BORDER - 4);
        Assertions.assertFalse(snake.isPossibleMove());
    }

    @Test
    @DisplayName("Grow")
    void grow_Test() {
        Assertions.assertEquals(snake.getHead().row(), 0);
        Assertions.assertEquals(snake.getHead().column(), 0);
        Assertions.assertEquals(snake.getLength(), 1);
        snake.grow();
        Assertions.assertEquals(snake.getHead().row(), 0);
        Assertions.assertEquals(snake.getHead().column(), 1);
        Assertions.assertEquals(snake.getLength(), 2);
    }

    @Test
    @DisplayName("Move")
    void move_Test() {
        Assertions.assertEquals(snake.getHead().row(), 0);
        Assertions.assertEquals(snake.getHead().column(), 0);
        Assertions.assertEquals(snake.getLength(), 1);
        snake.move();
        Assertions.assertEquals(snake.getHead().row(), 0);
        Assertions.assertEquals(snake.getHead().column(), 1);
        Assertions.assertEquals(snake.getLength(), 1);
    }
}