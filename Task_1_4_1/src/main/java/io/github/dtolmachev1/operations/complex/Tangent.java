package io.github.dtolmachev1.operations.complex;

import io.github.dtolmachev1.numbers.Complex;
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
    Tangent(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the tangent of a given number.</p>
     *
     * @return tangent of left operand.
     */
    @Override
    public Complex eval() {
        return operand.tan();
    }

    private final Complex operand;  // operand
}
