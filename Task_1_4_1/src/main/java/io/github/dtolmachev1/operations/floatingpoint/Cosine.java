package io.github.dtolmachev1.operations.floatingpoint;

import io.github.dtolmachev1.operations.Operation;

/**
 * <p>Class for cosine operation.</p>
 */
class Cosine implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    Cosine(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the cosine of a given number.</p>
     *
     * @return cosine of left operand.
     */
    @Override
    public Double eval() {
        return Math.cos(operand);
    }

    private final Double operand;  // operand
}
