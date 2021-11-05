package io.github.dtolmachev1;

/**
 * <p>Class for scientific calculator factory.</p>
 */
public class ScientificCalculatorFactory implements CalculatorFactory {
    /**
     * <p>Creates new calculator of specified type.</p>
     *
     * @param type type of the calculator to create.
     * @return newly created instance of <code>Calculator</code> class or <code>null</code> if specified calculator can't be created.
     */
    @Override
    public Calculator getCalculatorInstance(String type) {
        switch(type) {
            case "Double": return new DoubleCalculator();
            case "Complex": return new ComplexCalculator();
            default: return null;
        }
    }
}
