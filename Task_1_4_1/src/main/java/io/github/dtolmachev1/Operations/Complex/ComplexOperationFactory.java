package io.github.dtolmachev1.Operations.Complex;

import io.github.dtolmachev1.Numbers.Complex;
import io.github.dtolmachev1.Operations.*;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Class for operation factory.</p>
 */
public class ComplexOperationFactory implements io.github.dtolmachev1.Operations.OperationFactory {
    /**
     * <p>Constructor to initialize util <code>HashMap</code>.</p>
     */
    public ComplexOperationFactory() {
        UNARY_OPERATORS = new HashMap<>(6);
        BINARY_OPERATORS = new HashMap<>(6);
        UNARY_OPERATORS.put("log", Logarithm::new);
        UNARY_OPERATORS.put("sqrt", SquareRoot::new);
        UNARY_OPERATORS.put("sin", Sine::new);
        UNARY_OPERATORS.put("cos", Cosine::new);
        UNARY_OPERATORS.put("tg", Tangent::new);
        UNARY_OPERATORS.put("ctg", Cotangent::new);
        BINARY_OPERATORS.put("+", Addition::new);
        BINARY_OPERATORS.put("-", Subtraction::new);
        BINARY_OPERATORS.put("*", Multiplication::new);
        BINARY_OPERATORS.put("/", Division::new);
        BINARY_OPERATORS.put("^", Exponentiation::new);
        BINARY_OPERATORS.put("pow", Exponentiation::new);
    }

    /**
     * <p>Checks if given string is an operator (unary or binary).</p>
     *
     * @param string string to check.
     * @return <code>true</code> if given string is an operator or <code>false</code> otherwise.
     */
    @Override
    public boolean isOperator(String string) {
        return isUnaryOperator(string) || isBinaryOperator(string);
    }

    /**
     * <p>Checks if given string is a unary operator ('log', 'sqrt', 'sin', 'cos', 'tg', 'ctg').</p>
     *
     * @param string string to check.
     * @return <code>true</code> if given string is a unary operator or <code>false</code> otherwise.
     */
    @Override
    public boolean isUnaryOperator(String string) {
        return UNARY_OPERATORS.containsKey(string);
    }

    /**
     * <p>Checks if given string is a binary operator ('+', '-', '*', '/', '^', 'pow').</p>
     *
     * @param string string to check.
     * @return <code>true</code> if given string is a binary operator or <code>false</code> otherwise.
     */
    @Override
    public boolean isBinaryOperator(String string) {
        return BINARY_OPERATORS.containsKey(string);
    }

    /**
     * <p>Creates new operation depending on specified operator.</p>
     *
     * @param operator for which operation should be created.
     * @param operand on which operator should be applied.
     * @return newly created instance of operation.
     */
    @Override
    public Operation getOperationInstance(String operator, Number operand) {
        Function<Complex, Operation> operation = UNARY_OPERATORS.get(operator);
        return operation != null ? operation.apply((Complex) operand) : null;
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
        BiFunction<Complex, Complex, Operation> operation = BINARY_OPERATORS.get(operator);
        return operation != null ? operation.apply((Complex) leftOperand, (Complex) rightOperand) : null;
    }

    private final Map<String, Function<Complex, Operation>> UNARY_OPERATORS;  // list of unary operators
    private final Map<String, BiFunction<Complex, Complex, Operation>> BINARY_OPERATORS;  // list of binary operators
}
