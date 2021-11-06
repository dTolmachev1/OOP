package io.github.dtolmachev1.Calculators;

import java.util.ServiceLoader;

/**
 * <p>Service for dynamically providing instance of calculator class.</p>
 */
public class CalculatorLoader {
    /**
     * <p>Default constructor to initialize calculator loader.</p>
     */
    public CalculatorLoader() {
        this.calculatorLoader = ServiceLoader.load(CalculatorFactory.class);
    }

    /**
     * <p>Loads new calculator of specified type.</p>
     *
     * @param type type of the calculator to load.
     * @return newly loaded instance of <code>Calculator</code> class or <code>null</code> if specified calculator can't be loaded.
     */
    public Calculator loadCalculatorInstance(String type) {
        for(CalculatorFactory calculatorFactory : calculatorLoader) {
            Calculator calculator = calculatorFactory.getCalculatorInstance(type);
            if(calculator != null) {
                return calculator;
            }
        }
        return null;
    }

    private final ServiceLoader<CalculatorFactory> calculatorLoader;  // for dynamically loading services
}
