package io.github.dtolmachev1.operations.complex;

import io.github.dtolmachev1.numbers.Complex;
import io.github.dtolmachev1.operations.Operation;

/**
 * <p>Class for natural logarithm operation.</p>
 */
class Logarithm implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    Logarithm(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the natural logarithm of a given number.</p>
     *
     * @return natural logarithm of left operand.
     */
    @Override
    public Complex eval() {
        return operand.log();
    }

    private final Complex operand;  // operand
}
