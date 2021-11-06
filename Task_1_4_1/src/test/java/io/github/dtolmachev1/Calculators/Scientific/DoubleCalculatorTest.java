package io.github.dtolmachev1.Calculators.Scientific;

import io.github.dtolmachev1.Calculators.Calculator;
import io.github.dtolmachev1.Calculators.CalculatorFactory;
import io.github.dtolmachev1.Calculators.ParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DoubleCalculatorTest {
    @BeforeAll
    static void setUp() {
        CalculatorFactory calculatorFactory = new ScientificCalculatorFactory();  // for creating Calculator instance
        calculator = calculatorFactory.getCalculatorInstance("Double");
    }

    @Test
    @DisplayName("Addition")
    void calculate_Add() throws ParseException {
        Assertions.assertEquals(calculator.calculate("+ 2 2").toString(), Double.valueOf(4).toString());
    }

    @Test
    @DisplayName("Subtraction")
    void calculate_Subtract() throws ParseException {
        Assertions.assertEquals(calculator.calculate("- 4 2").toString(), Double.valueOf(2).toString());
    }

    @Test
    @DisplayName("Multiplication")
    void calculate_Multiply() throws ParseException {
        Assertions.assertEquals(calculator.calculate("* 2 2").toString(), Double.valueOf(4).toString());
    }

    @Test
    @DisplayName("Division")
    void calculate_Divide() throws ParseException {
        Assertions.assertEquals(calculator.calculate("/ 4 2").toString(), Double.valueOf(2).toString());
    }

    @Test
    @DisplayName("Exponentiation")
    void calculate_Power() throws ParseException {
        Assertions.assertEquals(calculator.calculate("^ 2 2").toString(), Double.valueOf(4).toString());
    }

    @Test
    @DisplayName("Natural logarithm")
    void calculate_Log() throws ParseException {
        Assertions.assertEquals(calculator.calculate("log " + Math.E).toString(), Double.valueOf(1).toString());
    }

    @Test
    @DisplayName("Square root")
    void calculate_Sqrt() throws ParseException {
        Assertions.assertEquals(calculator.calculate("sqrt 4").toString(), Double.valueOf(2).toString());
    }

    @Test
    @DisplayName("Sine")
    void calculate_Sin() throws ParseException {
        Assertions.assertEquals(calculator.calculate("sin " + Math.PI / 2).toString(), Double.valueOf(1).toString());
    }

    @Test
    @DisplayName("Cosine")
    void calculate_Cos() throws ParseException {
        Assertions.assertEquals(calculator.calculate("cos " + Math.PI).toString(), Double.valueOf(-1).toString());
    }

    @Test
    @DisplayName("Tangent")
    void calculate_Tg() throws ParseException {
        Assertions.assertEquals(calculator.calculate("tg 0").toString(), Double.valueOf(0).toString());
    }

    @Test
    @DisplayName("Cotangent")
    @SuppressWarnings({"NumericOverflow", "divzero"})
    void calculate_Ctg() throws ParseException {
        Assertions.assertEquals(calculator.calculate("ctg 0").toString(), Double.valueOf((double) 1 / 0).toString());
    }

    @Test
    @DisplayName("Example")
    void calculate_Example() throws ParseException {
        Assertions.assertEquals(calculator.calculate("sin + - 1 2 1").toString(), Double.valueOf(0).toString());
    }

    @Test
    @DisplayName("Complex expression")
    void calculate_ComplexExpression() throws ParseException {
        Assertions.assertEquals(calculator.calculate("/ - 6 sqrt - pow -6 2 * 4 * 2 4 * 2 2").toString(), Double.valueOf(1).toString());
        Assertions.assertEquals(calculator.calculate("/ + 6 sqrt - pow -6 2 * 4 * 2 4 * 2 2").toString(), Double.valueOf(2).toString());
    }

    private static Calculator calculator;  // instance of calculator for testing
}
