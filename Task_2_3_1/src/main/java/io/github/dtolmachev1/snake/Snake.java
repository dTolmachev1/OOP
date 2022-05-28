package io.github.dtolmachev1.snake;

import io.github.dtolmachev1.snake.cell.Cell;
import io.github.dtolmachev1.snake.cell.SnakeNode;
import io.github.dtolmachev1.snake.coordinates.Operations;
import io.github.dtolmachev1.snake.coordinates.VelocityVector;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>Class for representing snake.</p>
 */
public class Snake {
    private final Deque<Cell> nodes;  // list of snake nodes
    private VelocityVector velocityVector;  // for storing current velocity and direction
    private final int bottomBorder;  // bottom border
    private final int rightBorder;  // right border
    private final Operations<Cell> operations;  // for some computations with coordinates

    /**
     * <p>Constructor to initialize snake at the specified position.</p>
     *
     * @param head Cell to be head of the newly created snake.
     * @param velocityVector Velocity and direction for the snake to be created.
     * @param bottomBorder Bottom border.
     * @param rightBorder Right border.
     */
    public Snake(Cell head, VelocityVector velocityVector, int bottomBorder, int rightBorder) {
        this.nodes = new LinkedList<>();
        this.nodes.addFirst(head);
        this.velocityVector = velocityVector;
        this.bottomBorder = bottomBorder;
        this.rightBorder = rightBorder;
        this.operations = new Operations<>((row, column) -> new Cell(row, column, SnakeNode.SNAKE_NODE));
    }

    /**
     * <p>Returns length of the snake.</p>
     *
     * @return Current length of the snake.
     */
    public int getLength() {
        return this.nodes.size();
    }

    /**
     * <p>Checks if moving in currently set direction is possible.</p>
     *
     * @return <code>true</code> if snake is able to move without intersecting itself, or <code>false</code> otherwise.
     */
    public boolean isPossibleMove() {
        Cell newHead = newHead();
        return this.nodes.stream().noneMatch(node -> node.intersects(newHead));
    }

    /**
     * <p>Returns head of the snake.</p>
     *
     * @return Current head of the snake.
     */
    public Cell getHead() {
        return this.nodes.getFirst();
    }

    /**
     * <p>Returns snake nodes.</p>
     *
     * @return Current snake nodes.
     */
    public Deque<Cell> getNodes() {
        return this.nodes;
    }

    /**
     * <p>Returns snakes velocity and direction.</p>
     *
     * @return Current snakes velocity and direction.
     */
    public VelocityVector getVelocityVector() {
        return this.velocityVector;
    }

    /**
     * <p>Changes velocity and direction of the snake to the specified one.</p>
     *
     * @param velocityVector Velocity and direction to be set.
     */
    public void setVelocityVector(VelocityVector velocityVector) {
        this.velocityVector = velocityVector;
    }

    /**
     * <p>Grows in the currently set direction.</p>
     */
    public void grow() {
        this.nodes.addFirst(newHead());
    }

    /**
     * <p>Moves in the currently set direction.</p>
     */
    public void move() {
        grow();
        this.nodes.removeLast();
    }

    /* Returns head to be grown */
    private Cell newHead() {
        Cell newHead = this.operations.add(getHead(), operations.multiply(operations.unit(), this.velocityVector));
        int row = newHead.row() < 0 ? this.bottomBorder + newHead.row() : newHead.row() % this.bottomBorder;
        int column = newHead.column() < 0 ? this.rightBorder + newHead.column() : newHead.column() % this.rightBorder;
        return new Cell(row, column, SnakeNode.SNAKE_NODE);
    }
}
