package io.github.dtolmachev1.Operations.Double;

import io.github.dtolmachev1.Operations.Operation;

/**
 * <p>Class for sine operation.</p>
 */
class Sine implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    Sine(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the sine of a given number.</p>
     *
     * @return sine of left operand.
     */
    @Override
    public Double eval() {
        return Math.sin(operand);
    }

    private final Double operand;  // operand
}
