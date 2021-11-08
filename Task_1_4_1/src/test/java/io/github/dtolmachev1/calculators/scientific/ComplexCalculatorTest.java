package io.github.dtolmachev1.calculators.scientific;

import io.github.dtolmachev1.calculators.Calculator;
import io.github.dtolmachev1.calculators.CalculatorFactory;
import io.github.dtolmachev1.calculators.ParseException;
import io.github.dtolmachev1.numbers.Complex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ComplexCalculatorTest {
    @BeforeAll
    static void setUp() {
        CalculatorFactory calculatorFactory = new ScientificCalculatorFactory();  // for creating Calculator instance
        calculator = calculatorFactory.getCalculatorInstance("Complex");
    }

    @Test
    @DisplayName("Addition")
    void calculate_Add() throws ParseException {
        Assertions.assertEquals(calculator.calculate("+ 2 2").toString(), Complex.valueOf(4).toString());
        Assertions.assertEquals(calculator.calculate("+ 2+3I 2+3I").toString(), Complex.valueOf(4, 6).toString());
    }

    @Test
    @DisplayName("Subtraction")
    void calculate_Subtract() throws ParseException {
        Assertions.assertEquals(calculator.calculate("- 4 2").toString(), Complex.valueOf(2).toString());
        Assertions.assertEquals(calculator.calculate("- 4+6i 2+3i").toString(), Complex.valueOf(2, 3).toString());
    }

    @Test
    @DisplayName("Multiplication")
    void calculate_Multiply() throws ParseException {
        Assertions.assertEquals(calculator.calculate("* 2 2").toString(), Complex.valueOf(4).toString());
        Assertions.assertEquals(calculator.calculate("* (2,3) (2,3)").toString(), Complex.valueOf(-5, 12).toString());
    }

    @Test
    @DisplayName("Division")
    void calculate_Divide() throws ParseException {
        Assertions.assertEquals(calculator.calculate("/ 4 2").toString(), Complex.valueOf(2).toString());
        Assertions.assertEquals(calculator.calculate("/ (2,3) (2,3)").toString(), Complex.valueOf(1).toString());
    }

    @Test
    @DisplayName("Exponentiation")
    void calculate_Power() throws ParseException {
        Assertions.assertEquals(calculator.calculate("^ 2 2").toString(), Complex.valueOf(4).toString());
        Assertions.assertEquals(calculator.calculate("pow 2+3i 2+3i").toString(), Complex.valueOf(0.6817665190890337, -0.9520157134779257).toString());
    }

    @Test
    @DisplayName("Natural logarithm")
    void calculate_Log() throws ParseException {
        Assertions.assertEquals(calculator.calculate("log " + Math.E).toString(), Complex.valueOf(1).toString());
        Assertions.assertEquals(calculator.calculate("log 1+1i").toString(), Complex.valueOf(1.4142135623730951, 0.7853981633974483).toString());
    }

    @Test
    @DisplayName("Square root")
    void calculate_Sqrt() throws ParseException {
        Assertions.assertEquals(calculator.calculate("sqrt 4").toString(), Complex.valueOf(2).toString());
        Assertions.assertEquals(calculator.calculate("sqrt 4+4i").toString(), Complex.valueOf(2.19736822693562, 0.9101797211244548).toString());
    }

    @Test
    @DisplayName("Sine")
    void calculate_Sin() throws ParseException {
        Assertions.assertEquals(calculator.calculate("sin " + Math.PI / 2).toString(), Complex.valueOf(1).toString());
        Assertions.assertEquals(calculator.calculate("sin 2+3i").toString(), Complex.valueOf(9.15449914691143, -4.168906959966565).toString());
    }

    @Test
    @DisplayName("Cosine")
    void calculate_Cos() throws ParseException {
        Assertions.assertEquals(calculator.calculate("cos " + Math.PI).toString(), Complex.valueOf(-1).toString());
        Assertions.assertEquals(calculator.calculate("cos 2+3i").toString(), Complex.valueOf(-4.189625690968807, -9.109227893755337).toString());
    }

    @Test
    @DisplayName("Tangent")
    void calculate_Tg() throws ParseException {
        Assertions.assertEquals(calculator.calculate("tg 0").toString(), Complex.valueOf(0).toString());
        Assertions.assertEquals(calculator.calculate("tg 2+3i").toString(), Complex.valueOf(-0.003764025641504247, 1.00323862735361).toString());
    }

    @Test
    @DisplayName("Cotangent")
    @SuppressWarnings({"NumericOverflow", "divzero"})
    void calculate_Ctg() throws ParseException {
        Assertions.assertEquals(calculator.calculate("ctg 0").toString(), Complex.valueOf((double) 1 / 0).toString());
        Assertions.assertEquals(calculator.calculate("ctg 2+3i").toString(), Complex.valueOf(-0.003739710376337008, -0.9967577965693583).toString());
    }

    @Test
    @DisplayName("Example")
    void calculate_Example() throws ParseException {
        Assertions.assertEquals(calculator.calculate("sin + - 1 2 1").toString(), Complex.valueOf(0).toString());
    }

    @Test
    @DisplayName("Complex expression")
    void calculate_ComplexExpression() throws ParseException {
        Assertions.assertEquals(calculator.calculate("/ - 6 sqrt - pow -6 2 * 4 * 2 4 * 2 2").toString(), Complex.valueOf(1).toString());
        Assertions.assertEquals(calculator.calculate("/ + 6 sqrt - pow -6 2 * 4 * 2 4 * 2 2").toString(), Complex.valueOf(2).toString());
        Assertions.assertEquals(calculator.calculate("/ - 4 sqrt - pow -4 2 * 4 * 0.25 25 * 2 0.25").toString(), Complex.valueOf(8, -6).toString());
        Assertions.assertEquals(calculator.calculate("/ + 4 sqrt - pow -4 2 * 4 * 0.25 25 * 2 0.25").toString(), Complex.valueOf(8, 6).toString());
    }

    private static Calculator calculator;  // instance of calculator for testing
}
