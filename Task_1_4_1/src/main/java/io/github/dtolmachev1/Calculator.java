package io.github.dtolmachev1;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * <p>Class for calculator.</p>
 */
public class Calculator {
    /**
     * <p>Calculates expression written in prefix (Polish) notation.</p>
     *
     * @param expression input string containing expression to evaluate.
     * @return result of computation as a complex number.
     * @throws ParseException if expression syntactically incorrect.
     */
    public static Complex evaluate(String expression) throws ParseException {
        Stack<Complex> stack = new Stack<>();  // temporary stack for computation
        String[] atoms = expression.split("\\s");  // splitting input string into single atoms (operators and operands)
        for(int i = atoms.length - 1; i >= 0; i--) {
            if (isOperator(atoms[i])) {
                try {
                    Complex a = stack.pop();
                    if (isBinaryOperator(atoms[i])) {
                        Complex b = stack.pop();
                        stack.push(eval(atoms[i], a, b));
                    } else stack.push(eval(atoms[i], a));
                }
                catch(EmptyStackException E) {
                    throw new ParseException();
                }
            }
            else if(isOperand(atoms[i])) {
                stack.push(parseOperand(atoms[i]));
            }
            else throw new ParseException();
        }
        return stack.peek();
    }

    /* evaluates simple unary-operand expression */
    private static Complex eval(String operator, Complex operand) {
        Complex result = null;  // for storing the result
        switch(operator) {
            case "log": result = Complex.log(operand); break;
            case "sqrt": result = Complex.sqrt(operand); break;
            case "sin": result = Complex.sin(operand); break;
            case "cos": result = Complex.cos(operand); break;
            case "tg": result = Complex.tan(operand); break;
            case "ctg": result = Complex.cot(operand); break;
        }
        return result;
    }

    /* evaluates simple binary-operand expression */
    private static Complex eval(String operator, Complex leftOperand, Complex rightOperand) {
        Complex result = null;  // for storing the result
        switch(operator) {
            case "+": result = Complex.add(leftOperand, rightOperand); break;
            case "-": result = Complex.subtract(leftOperand, rightOperand); break;
            case "*": result = Complex.multiply(leftOperand, rightOperand); break;
            case "/": result = Complex.divide(leftOperand, rightOperand); break;
            case "^": case "pow": result = Complex.pow(leftOperand, rightOperand); break;
        }
        return result;
    }

    /* parses an operand (complex or real number) from a given string */
    private static Complex parseOperand(String string) throws ParseException {
        Complex value;  // for storing parsed number
        string = preprocessOperand(string);  // preprocessing input string for parsing
        try {
            /* complex number written as a tuple */
            if (string.startsWith("(")) {
                if (!string.contains(",") || !string.endsWith(")")) {
                    throw new ParseException();
                }
                value = Complex.valueOf(Double.parseDouble(string.substring(1, string.indexOf(','))), Double.parseDouble(string.substring(string.indexOf(',') + 1, string.length() - 1)));
            }
            /* angle written in degrees */
            else if (string.endsWith("\u00B0")) {
                value = Complex.valueOf(Math.toRadians(Double.parseDouble(string.substring(0, string.length() - 1))));
            }
            /* complex number written in standard form */
            else value = Complex.valueOf(string);
        }
        catch(NumberFormatException E) {
            throw new ParseException();
        }
        return value;
    }

    /* preprocesses given string for the further parsing */
    private static String preprocessOperand(String string) {
        string = string.replaceAll("[Ee]", Double.toString(Math.E));  // for constants
        string = string.replaceAll("[Pp][Ii]", Double.toString(Math.PI));  // for constants
        return string;
    }

    /* Returns true if given string is an operand (complex or real number) or false otherwise */
    private static boolean isOperand(String string) {
        return string.matches(".*([\\dIi]|[Ee]|[Pp][Ii])+.*");
    }

    /* returns true if given string is an operator (unary or binary) or false otherwise */
    private static boolean isOperator(String string) {
        return isUnaryOperator(string) || isBinaryOperator(string);
    }

    /* returns true if given string is a unary operator (log, sqrt, sin, cos, tg, ctg) or false otherwise */
    private static boolean isUnaryOperator(String string) {
        return Arrays.asList(UNARY_OPERATORS).contains(string);
    }

    /* returns true if given string is a binary operator (+, -, *, /, ^, pow) or false otherwise */
    private static boolean isBinaryOperator(String string) {
        return Arrays.asList(BINARY_OPERATORS).contains(string);
    }

    private static final String[] UNARY_OPERATORS = {"log", "sqrt", "sin", "cos", "tg", "ctg"};  // list of unary operators
    private static final String[] BINARY_OPERATORS = {"+", "-", "*", "/", "^", "pow"};  // list of binary operators
}
