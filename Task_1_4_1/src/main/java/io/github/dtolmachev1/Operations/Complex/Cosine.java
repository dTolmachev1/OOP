package io.github.dtolmachev1.Operations.Complex;

import io.github.dtolmachev1.Numbers.Complex;
import io.github.dtolmachev1.Operations.Operation;

/**
 * <p>Class for cosine operation.</p>
 */
class Cosine implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    Cosine(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the cosine of a given number.</p>
     *
     * @return cosine of left operand.
     */
    @Override
    public Complex eval() {
        return operand.cos();
    }

    private final Complex operand;  // operand
}
