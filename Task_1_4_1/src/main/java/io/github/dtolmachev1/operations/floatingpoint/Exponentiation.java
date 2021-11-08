package io.github.dtolmachev1.operations.floatingpoint;

import io.github.dtolmachev1.operations.Operation;

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
    Exponentiation(Double leftOperand, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the power of two double numbers.</p>
     *
     * @return power of left and right operands.
     */
    @Override
    public Double eval() {
        return Math.pow(leftOperand, rightOperand);
    }

    private final Double leftOperand;  // left operand
    private final Double rightOperand;  // right operand
}
