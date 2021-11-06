package io.github.dtolmachev1.Operations.Complex;

import io.github.dtolmachev1.Numbers.Complex;
import io.github.dtolmachev1.Operations.Operation;

/**
 * <p>Class for exponentiation operation.</p>
 */
class Exponentiation implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand  left operand.
     * @param rightOperand right operand.
     */
    Exponentiation(Complex leftOperand, Complex rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the power of two complex numbers.</p>
     *
     * @return power of left and right operands.
     */
    @Override
    public Complex eval() {
        return leftOperand.pow(rightOperand);
    }

    private final Complex leftOperand;  // left operand
    private final Complex rightOperand;  // right operand
}
