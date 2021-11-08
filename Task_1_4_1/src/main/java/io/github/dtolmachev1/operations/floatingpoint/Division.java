package io.github.dtolmachev1.operations.floatingpoint;

import io.github.dtolmachev1.operations.Operation;

/**
 * <p>Class for division operation.</p>
 */
class Division implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand  left operand.
     * @param rightOperand right operand.
     */
    Division(Double leftOperand, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the quotient of two double numbers.</p>
     *
     * @return quotient of left and right operands.
     */
    @Override
    public Double eval() {
        return leftOperand / rightOperand;
    }

    private final Double leftOperand;  // left operand
    private final Double rightOperand;  // right operand
}
