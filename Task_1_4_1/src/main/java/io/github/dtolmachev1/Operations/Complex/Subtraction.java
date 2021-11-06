package io.github.dtolmachev1.Operations.Complex;

import io.github.dtolmachev1.Numbers.Complex;
import io.github.dtolmachev1.Operations.Operation;

/**
 * <p>Class for subtraction operation.</p>
 */
class Subtraction implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    Subtraction(Complex leftOperand, Complex rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the difference of two complex numbers.</p>
     *
     * @return difference of left and right operands.
     */
    @Override
    public Complex eval() {
        return leftOperand.subtract(rightOperand);
    }

    private final Complex leftOperand;  // left operand
    private final Complex rightOperand;  // right operand
}

