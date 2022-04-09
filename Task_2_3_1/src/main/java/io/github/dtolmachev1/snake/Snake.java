package io.github.dtolmachev1.snake;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>Class for representing snake.</p>
 */
public class Snake {
    private final Deque<Cell> nodes;  // list of snake nodes

    /**
     * <p>Constructor to initialize snake at the specified position.</p>
     *
     * @param head Cell to be head of the newly created snake.
     */
    public Snake(Cell head) {
        this.nodes = new LinkedList<>();
        head.setType(CellType.SNAKE_NODE);
        this.nodes.addFirst(head);
    }

    /**
     * <p>Returns current length of the snake.</p>
     *
     * @return Current length of the snake.
     */
    public int getLength() {
        return nodes.size();
    }

    /**
     * <p>Checks whether snake contains specified node.</p>
     *
     * @param node Cell to check.
     * @return <code>true</code> if snake contains given node, or <code>false</code> otherwise.
     */
    public boolean contains(Cell node) {
        return this.nodes.contains(node);
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
     * <p>grows with the specified cell.</p>
     *
     * @param head New head of the snake.
     */
    public void grow(Cell head) {
        head.setType(CellType.SNAKE_NODE);
        this.nodes.addFirst(head);
    }

    /**
     * <p>Moves to the specified cell.</p>
     *
     * @param head New head of the snake.
     */
    public void move(Cell head) {
        grow(head);
        this.nodes.removeLast().setType(CellType.EMPTY);
    }
}
