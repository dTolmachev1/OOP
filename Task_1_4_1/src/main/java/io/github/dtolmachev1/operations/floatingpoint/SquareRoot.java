package io.github.dtolmachev1.operations.floatingpoint;

import io.github.dtolmachev1.operations.Operation;

/**
 * <p>Class for square root operation.</p>
 */
class SquareRoot implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    SquareRoot(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the square root of a given number.</p>
     *
     * @return square root of left operand.
     */
    @Override
    public Double eval() {
        return Math.sqrt(operand);
    }

    private final Double operand;  // operand
}
