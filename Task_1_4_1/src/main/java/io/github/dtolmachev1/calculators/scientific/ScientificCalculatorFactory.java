package io.github.dtolmachev1.calculators.scientific;

import io.github.dtolmachev1.calculators.Calculator;
import io.github.dtolmachev1.calculators.CalculatorFactory;

import java.util.function.Supplier;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Class for scientific calculator factory.</p>
 */
public class ScientificCalculatorFactory implements CalculatorFactory {
    /**
     * <p>Constructor to initialize <code>HashMap</code>.</p>
     */
    public ScientificCalculatorFactory() {
        SCIENTIFIC_CALCULATORS = new HashMap<>(2);
        SCIENTIFIC_CALCULATORS.put("FloatingPoint", FloatingPointCalculator::new);
        SCIENTIFIC_CALCULATORS.put("Complex", ComplexCalculator::new);
    }

    /**
     * <p>Creates new calculator of specified type.</p>
     *
     * @param type type of the calculator to create.
     * @return newly created instance of <code>Calculator</code> class or <code>null</code> if specified calculator can't be created.
     */
    @Override
    public Calculator getCalculatorInstance(String type) {
        Supplier<Calculator> calculator = SCIENTIFIC_CALCULATORS.get(type);
        return calculator != null ? calculator.get() : null;
    }

    private final Map<String, Supplier<Calculator>> SCIENTIFIC_CALCULATORS;  // list of scientific calculators
}
