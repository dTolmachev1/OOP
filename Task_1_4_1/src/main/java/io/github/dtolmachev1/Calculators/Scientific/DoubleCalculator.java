package io.github.dtolmachev1.Calculators.Scientific;

        import io.github.dtolmachev1.Calculators.Calculator;
        import io.github.dtolmachev1.Calculators.ParseException;
        import io.github.dtolmachev1.Operations.Double.DoubleOperationFactory;

        import java.util.EmptyStackException;
        import java.util.Stack;

/**
 * <p>Class for double calculator.</p>
 */
public class DoubleCalculator extends Calculator {
    /**
     * <p>Default constructor to initialize calculator.</p>
     */
    public DoubleCalculator() {
        this.operationFactory = new DoubleOperationFactory();
    }

    /**
     * <p>Calculates expression written in prefix (Polish) notation.</p>
     *
     * @param expression input string containing expression to evaluate.
     * @return result of computation as a complex number.
     * @throws ParseException if expression syntactically incorrect.
     */
    @Override
    public Double calculate(String expression) throws ParseException {
        Stack<Double> stack = new Stack<>();  // temporary stack for computation
        String[] atoms = expression.split("\\s");  // splitting input string into single atoms (operators and operands)
        for (int i = atoms.length - 1; i >= 0; i--) {
            if (operationFactory.isOperator(atoms[i])) {
                try {
                    Double a = stack.pop();
                    if (operationFactory.isBinaryOperator(atoms[i])) {
                        Double b = stack.pop();
                        stack.push((Double) operationFactory.getOperationInstance(atoms[i], a, b).eval());
                    } else stack.push((Double) operationFactory.getOperationInstance(atoms[i], a).eval());
                } catch (EmptyStackException E) {
                    throw new ParseException();
                }
            } else if (isOperand(atoms[i])) {
                stack.push(parseOperand(atoms[i]));
            } else throw new ParseException();
        }
        return stack.peek();
    }

    /* parses an operand (real number) from a given string */
    @Override
    protected Double parseOperand(String operand) throws ParseException {
        double value;  // for storing parsed number
        operand = preprocessOperand(operand);  // preprocessing input string for parsing
        try {
            /* angle written in degrees */
            if (operand.endsWith("\u00B0")) {
                value = Math.toRadians(Double.parseDouble(operand.substring(0, operand.length() - 1)));
            }
            /* number written in standard form */
            else value = Double.parseDouble(operand);
        } catch (NumberFormatException E) {
            throw new ParseException();
        }
        return value;
    }

    /* Returns true if given string is an operand (real number) or false otherwise */
    @Override
    protected boolean isOperand(String string) {
        return string.matches(".*(\\d|[Ee]|[Pp][Ii])+.*");
    }
}
