package io.github.dtolmachev1.operations.complex;

import io.github.dtolmachev1.numbers.Complex;
import io.github.dtolmachev1.operations.Operation;

/**
 * <p>Class for cotangent operation.</p>
 */
class Cotangent implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    Cotangent(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the cotangent of a given number.</p>
     *
     * @return cotangent of left operand.
     */
    @Override
    public Complex eval() {
        return operand.cot();
    }

    private final Complex operand;  // operand
}
