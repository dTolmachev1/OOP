package io.github.dtolmachev1.Operations.Double;

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
    Subtraction(Double leftOperand, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the difference of two double numbers.</p>
     *
     * @return difference of left and right operands.
     */
    @Override
    public Double eval() {
        return leftOperand - rightOperand;
    }

    private final Double leftOperand;  // left operand
    private final Double rightOperand;  // right operand
}

