package io.github.dtolmachev1.Calculators;

import io.github.dtolmachev1.Operations.OperationFactory;

/**
 * <p>Class for calculator.</p>
 */
public abstract class Calculator {
    /**
     * <p>Calculates expression written in prefix (Polish) notation.</p>
     *
     * @param expression input string containing expression to evaluate.
     * @return result of computation as a complex number.
     * @throws ParseException if expression syntactically incorrect.
     */
    public abstract Number calculate(String expression) throws ParseException;

    /* parses an operand from a given string */
    protected abstract Number parseOperand(String operand) throws ParseException;

    /* Returns true if given string is an operand or false otherwise */
    protected abstract boolean isOperand(String string);

    /* preprocesses given string for the further parsing */
    protected String preprocessOperand(String operand) {
        return operand.replaceAll("[Pp][Ii]", Double.toString(Math.PI));
    }

    protected OperationFactory operationFactory;  // operation factory
}
