package io.github.dtolmachev1.operations.floatingpoint;

import io.github.dtolmachev1.operations.Operation;

/**
 * <p>Class for tangent operation.</p>
 */
class Tangent implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    Tangent(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the tangent of a given number.</p>
     *
     * @return tangent of left operand.
     */
    @Override
    public Double eval() {
        return Math.tan(operand);
    }

    private final Double operand;  // operand
}
