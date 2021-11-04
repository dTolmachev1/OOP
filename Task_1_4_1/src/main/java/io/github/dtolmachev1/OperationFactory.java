package io.github.dtolmachev1;

/**
 * <p>Interface for operation factory.</p>
 */
interface OperationFactory {
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
