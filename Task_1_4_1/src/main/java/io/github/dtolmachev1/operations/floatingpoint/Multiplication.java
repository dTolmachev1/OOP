package io.github.dtolmachev1.operations.floatingpoint;

import io.github.dtolmachev1.operations.Operation;

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
    Multiplication(Double leftOperand, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the product of two double numbers.</p>
     *
     * @return product of left and right operands.
     */
    @Override
    public Double eval() {
        return leftOperand * rightOperand;
    }

    private final Double leftOperand;  // left operand
    private final Double rightOperand;  // right operand
}
