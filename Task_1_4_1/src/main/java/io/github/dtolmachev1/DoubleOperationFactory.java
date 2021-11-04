package io.github.dtolmachev1;

/**
 * <p>Class for operation factory.</p>
 */
class DoubleOperationFactory implements OperationFactory {
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
            case "log": return new DoubleLogarithm((Double) operand);
            case "sqrt": return new DoubleSquareRoot((Double) operand);
            case "sin": return new DoubleSine((Double) operand);
            case "cos": return new DoubleCosine((Double) operand);
            case "tg": return new DoubleTangent((Double) operand);
            case "ctg": return new DoubleCotangent((Double) operand);
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
            case "+": return new DoubleAddition((Double) leftOperand, (Double) rightOperand);
            case "-": return new DoubleSubtraction((Double) leftOperand, (Double) rightOperand);
            case "*": return new DoubleMultiplication((Double) leftOperand, (Double) rightOperand);
            case "/": return new DoubleDivision((Double) leftOperand, (Double) rightOperand);
            case "^": case "pow": return new DoubleExponentiation((Double) leftOperand, (Double) rightOperand);
            default: return null;
        }
    }
}
