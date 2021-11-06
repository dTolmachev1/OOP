package io.github.dtolmachev1.Operations.Double;

import io.github.dtolmachev1.Operations.Operation;

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
    Addition(Double leftOperand, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the sum of two double numbers.</p>
     *
     * @return sum of left and right operands.
     */
    @Override
    public Double eval() {
        return leftOperand + rightOperand;
    }

    private final Double leftOperand;  // left operand
    private final Double rightOperand;  // right operand
}
