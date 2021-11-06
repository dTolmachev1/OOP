package io.github.dtolmachev1.Operations.Complex;

import io.github.dtolmachev1.Numbers.Complex;
import io.github.dtolmachev1.Operations.Operation;

/**
 * <p>Class for multiplication operation.</p>
 */
class Multiplication implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand  left operand.
     * @param rightOperand right operand.
     */
    Multiplication(Complex leftOperand, Complex rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the product of two complex numbers.</p>
     *
     * @return product of left and right operands.
     */
    @Override
    public Complex eval() {
        return leftOperand.multiply(rightOperand);
    }

    private final Complex leftOperand;  // left operand
    private final Complex rightOperand;  // right operand
}
