package io.github.dtolmachev1.operations.complex;

import io.github.dtolmachev1.numbers.Complex;
import io.github.dtolmachev1.operations.Operation;

/**
 * <p>Class for addition operation.</p>
 */
class Addition implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand  left operand.
     * @param rightOperand right operand.
     */
    Addition(Complex leftOperand, Complex rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the sum of two complex numbers.</p>
     *
     * @return sum of left and right operands.
     */
    @Override
    public Complex eval() {
        return leftOperand.add(rightOperand);
    }

    private final Complex leftOperand;  // left operand
    private final Complex rightOperand;  // right operand
}
