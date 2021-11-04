package io.github.dtolmachev1;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * <p>Class for complex calculator.</p>
 */
public class ComplexCalculator extends Calculator {
    /**
     * <p>Default constructor to initialize calculator.</p>
     */
    ComplexCalculator() {
        this.operationFactory = new ComplexOperationFactory();
    }

    /**
     * <p>Calculates expression written in prefix (Polish) notation.</p>
     *
     * @param expression input string containing expression to evaluate.
     * @return result of computation as a complex number.
     * @throws ParseException if expression syntactically incorrect.
     */
    @Override
    public Complex calculate(String expression) throws ParseException {
        Stack<Complex> stack = new Stack<>();  // temporary stack for computation
        String[] atoms = expression.split("\\s");  // splitting input string into single atoms (operators and operands)
        for (int i = atoms.length - 1; i >= 0; i--) {
            if (isOperator(atoms[i])) {
                try {
                    Complex a = stack.pop();
                    if (isBinaryOperator(atoms[i])) {
                        Complex b = stack.pop();
                        stack.push((Complex) operationFactory.getOperationInstance(atoms[i], a, b).eval());
                    } else stack.push((Complex) operationFactory.getOperationInstance(atoms[i], a).eval());
                } catch (EmptyStackException E) {
                    throw new ParseException();
                }
            } else if (isOperand(atoms[i])) {
                stack.push(parseOperand(atoms[i]));
            } else throw new ParseException();
        }
        return stack.peek();
    }

    /* parses an operand (complex or real number) from a given string */
    @Override
    protected Complex parseOperand(String operand) throws ParseException {
        Complex value;  // for storing parsed number
        operand = preprocessOperand(operand);  // preprocessing input string for parsing
        try {
            /* complex number written as a tuple */
            if (operand.startsWith("(")) {
                if (!operand.contains(",") || !operand.endsWith(")")) {
                    throw new ParseException();
                }
                value = Complex.valueOf(Double.parseDouble(operand.substring(1, operand.indexOf(','))), Double.parseDouble(operand.substring(operand.indexOf(',') + 1, operand.length() - 1)));
            }
            /* angle written in degrees */
            else if (operand.endsWith("\u00B0")) {
                value = Complex.valueOf(Math.toRadians(Double.parseDouble(operand.substring(0, operand.length() - 1))));
            }
            /* complex number written in standard form */
            else value = Complex.valueOf(operand);
        } catch (NumberFormatException E) {
            throw new ParseException();
        }
        return value;
    }

    /* Returns true if given string is an operand (complex or real number) or false otherwise */
    @Override
    protected boolean isOperand(String string) {
        return string.matches(".*([\\dIi]|[Ee]|[Pp][Ii])+.*");
    }
}
