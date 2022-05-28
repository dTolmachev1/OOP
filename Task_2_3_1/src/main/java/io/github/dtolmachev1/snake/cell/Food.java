package io.github.dtolmachev1.snake.cell;

/**
 * <p>Food cell.</p>
 */
public enum Food implements ScoreCellType {
    FOOD(1);

    private final int score;  // score for current cell

    /**
     * <p>Default constructor to initialize cell with given score.</p>
     *
     * @param score Score value to be set for this cell.
     */
    Food(int score) {
        this.score = score;
    }

    /**
     * <p>Returns score value of this cell.</p>
     *
     * @return Score value of this cell.
     */
    public int score() {
        return this.score;
    }
}
