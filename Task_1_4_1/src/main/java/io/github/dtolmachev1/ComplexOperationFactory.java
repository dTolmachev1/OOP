package io.github.dtolmachev1;

/**
 * <p>Class for operation factory.</p>
 */
class ComplexOperationFactory implements OperationFactory {
    /**
     * <p>Creates new operation depending on specified operator.</p>
     *
     * @param operator for which operation should be created.
     * @param operand on which operator should be applied.
     * @return newly created instance of operation.
     */
    @Override
    public Operation getOperationInstance(String operator, Number operand) {
        switch(operator) {
            case "log": return new ComplexLogarithm((Complex) operand);
            case "sqrt": return new ComplexSquareRoot((Complex) operand);
            case "sin": return new ComplexSine((Complex) operand);
            case "cos": return new ComplexCosine((Complex) operand);
            case "tg": return new ComplexTangent((Complex) operand);
            case "ctg": return new ComplexCotangent((Complex) operand);
            default: return null;
        }
    }

    /**
     * <p>Creates new operation depending on specified operator.</p>
     *
     * @param operator for which operation should be created.
     * @param leftOperand on which operator should be applied.
     * @param rightOperand on which operator should be applied.
     * @return newly created instance of operation.
     */
    @Override
    public Operation getOperationInstance(String operator, Number leftOperand, Number rightOperand) {
        switch(operator) {
            case "+": return new ComplexAddition((Complex) leftOperand, (Complex) rightOperand);
            case "-": return new ComplexSubtraction((Complex) leftOperand, (Complex) rightOperand);
            case "*": return new ComplexMultiplication((Complex) leftOperand, (Complex) rightOperand);
            case "/": return new ComplexDivision((Complex) leftOperand, (Complex) rightOperand);
            case "^": case "pow": return new ComplexExponentiation((Complex) leftOperand, (Complex) rightOperand);
            default: return null;
        }
    }
}
