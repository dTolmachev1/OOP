package io.github.dtolmachev1.operations;

/**
 * <p>Interface for operation factory.</p>
 */
public interface OperationFactory {
    /**
     * <p>Checks if given string is an operator (unary or binary).</p>
     *
     * @param string string to check.
     * @return <code>true</code> if given string is an operator or <code>false</code> otherwise.
     */
    boolean isOperator(String string);

    /**
     * <p>Checks if given string is a unary operator ('log', 'sqrt', 'sin', 'cos', 'tg', 'ctg').</p>
     *
     * @param string string to check.
     * @return <code>true</code> if given string is a unary operator or <code>false</code> otherwise.
     */
    boolean isUnaryOperator(String string);

    /**
     * <p>Checks if given string is a binary operator ('+', '-', '*', '/', '^', 'pow').</p>
     *
     * @param string string to check.
     * @return <code>true</code> if given string is a binary operator or <code>false</code> otherwise.
     */
    boolean isBinaryOperator(String string);

    /**
     * <p>Creates new operation depending on specified operator.</p>
     *
     * @param operator for which operation should be created.
     * @param operand on which operator should be applied.
     * @return newly created instance of operation.
     */
    Operation getOperationInstance(String operator, Number operand);

    /**
     * <p>Creates new operation depending on specified operator.</p>
     *
     * @param operator for which operation should be created.
     * @param leftOperand on which operator should be applied.
     * @param rightOperand on which operator should be applied.
     * @return newly created instance of operation.
     */
    Operation getOperationInstance(String operator, Number leftOperand, Number rightOperand);
}
