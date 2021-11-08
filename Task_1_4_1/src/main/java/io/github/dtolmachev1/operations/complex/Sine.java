package io.github.dtolmachev1.operations.complex;

import io.github.dtolmachev1.numbers.Complex;
import io.github.dtolmachev1.operations.Operation;

/**
 * <p>Class for sine operation.</p>
 */
class Sine implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    Sine(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the sine of a given number.</p>
     *
     * @return sine of left operand.
     */
    @Override
    public Complex eval() {
        return operand.sin();
    }

    private final Complex operand;  // operand
}
