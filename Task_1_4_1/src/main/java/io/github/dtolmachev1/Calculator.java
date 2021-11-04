package io.github.dtolmachev1;

import java.util.Arrays;

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

    /* returns true if given string is an operator (unary or binary) or false otherwise */
    protected boolean isOperator(String string) {
        return isUnaryOperator(string) || isBinaryOperator(string);
    }

    /* returns true if given string is a unary operator (log, sqrt, sin, cos, tg, ctg) or false otherwise */
    protected boolean isUnaryOperator(String string) {
        return Arrays.asList(UNARY_OPERATORS).contains(string);
    }

    /* returns true if given string is a binary operator (+, -, *, /, ^, pow) or false otherwise */
    protected boolean isBinaryOperator(String string) {
        return Arrays.asList(BINARY_OPERATORS).contains(string);
    }

    /* preprocesses given string for the further parsing */
    protected String preprocessOperand(String operand) {
        return operand.replaceAll("[Pp][Ii]", Double.toString(Math.PI));
    }

    protected OperationFactory operationFactory;  // operation factory

    private final String[] UNARY_OPERATORS = {"log", "sqrt", "sin", "cos", "tg", "ctg"};  // list of unary operators
    private final String[] BINARY_OPERATORS = {"+", "-", "*", "/", "^", "pow"};  // list of binary operators
}
