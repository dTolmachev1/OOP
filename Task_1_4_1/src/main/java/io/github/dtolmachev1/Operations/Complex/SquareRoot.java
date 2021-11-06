package io.github.dtolmachev1.Operations.Complex;

import io.github.dtolmachev1.Numbers.Complex;
import io.github.dtolmachev1.Operations.Operation;

/**
 * <p>Class for square root operation.</p>
 */
class SquareRoot implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    SquareRoot(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the square root of a given number.</p>
     *
     * @return square root of left operand.
     */
    @Override
    public Complex eval() {
        return operand.sqrt();
    }

    private final Complex operand;  // operand
}
