package io.github.dtolmachev1.Operations.Double;

import io.github.dtolmachev1.Operations.Operation;

/**
 * <p>Class for natural logarithm operation.</p>
 */
class Logarithm implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    Logarithm(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the natural logarithm of a given number.</p>
     *
     * @return natural logarithm of left operand.
     */
    @Override
    public Double eval() {
        return Math.log(operand);
    }

    private final Double operand;  // operand
}
