package io.github.dtolmachev1.Operations.Double;

import io.github.dtolmachev1.Operations.Operation;

/**
 * <p>Class for cotangent operation.</p>
 */
class Cotangent implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    Cotangent(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the cotangent of a given number.</p>
     *
     * @return cotangent of left operand.
     */
    @Override
    public Double eval() {
        return 1.0 / Math.tan(operand);
    }

    private final Double operand;  // operand
}
