package io.github.dtolmachev1.Calculators;

/**
 * <p>Interface for calculator factory.</p>
 */
public interface CalculatorFactory {
    /**
     * <p>Creates new calculator of specified type.</p>
     *
     * @param type type of the calculator to create.
     * @return newly created instance of <code>Calculator</code> class or <code>null</code> if specified calculator can't be created.
     */
    Calculator getCalculatorInstance(String type);
}
