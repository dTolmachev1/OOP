package io.github.dtolmachev1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    @Test
    @DisplayName("Addition")
    void evaluate_Add() {
        try {
            Assertions.assertEquals(Calculator.evaluate("+ 2 2").toString(), Complex.valueOf(4).toString());
            Assertions.assertEquals(Calculator.evaluate("+ 2+3I 2+3I").toString(), Complex.valueOf(4, 6).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Subtraction")
    void evaluate_Subtract() {
        try {
            Assertions.assertEquals(Calculator.evaluate("- 4 2").toString(), Complex.valueOf(2).toString());
            Assertions.assertEquals(Calculator.evaluate("- 4+6i 2+3i").toString(), Complex.valueOf(2, 3).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Multiplication")
    void evaluate_Multiply() {
        try {
            Assertions.assertEquals(Calculator.evaluate("* 2 2").toString(), Complex.valueOf(4).toString());
            Assertions.assertEquals(Calculator.evaluate("* (2,3) (2,3)").toString(), Complex.valueOf(-5, 12).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Division")
    void evaluate_Divide() {
        try {
            Assertions.assertEquals(Calculator.evaluate("/ 4 2").toString(), Complex.valueOf(2).toString());
            Assertions.assertEquals(Calculator.evaluate("/ (2,3) (2,3)").toString(), Complex.valueOf(1).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Exponentiation")
    void evaluate_Power() {
        try {
            Assertions.assertEquals(Calculator.evaluate("^ 2 2").toString(), Complex.valueOf(4).toString());
            Assertions.assertEquals(Calculator.evaluate("pow 2+3i 2+3i").toString(), Complex.valueOf(0.6817665190890337, -0.9520157134779257).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Natural logarithm")
    void evaluate_Log() {
        try {
            Assertions.assertEquals(Calculator.evaluate("log " + Math.E).toString(), Complex.valueOf(1).toString());
            Assertions.assertEquals(Calculator.evaluate("log 1+1i").toString(), Complex.valueOf(1.4142135623730951, 0.7853981633974483).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Square root")
    void evaluate_Sqrt() {
        try {
            Assertions.assertEquals(Calculator.evaluate("sqrt 4").toString(), Complex.valueOf(2).toString());
            Assertions.assertEquals(Calculator.evaluate("sqrt 4+4i").toString(), Complex.valueOf(2.19736822693562, 0.9101797211244548).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Sine")
    void evaluate_Sin() {
        try {
            Assertions.assertEquals(Calculator.evaluate("sin " + Math.PI / 2).toString(), Complex.valueOf(1).toString());
            Assertions.assertEquals(Calculator.evaluate("sin 2+3i").toString(), Complex.valueOf(9.15449914691143, -4.168906959966565).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Cosine")
    void evaluate_Cos() {
        try {
            Assertions.assertEquals(Calculator.evaluate("cos " + Math.PI).toString(), Complex.valueOf(-1).toString());
            Assertions.assertEquals(Calculator.evaluate("cos 2+3i").toString(), Complex.valueOf(-4.189625690968807, -9.109227893755337).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Tangent")
    void evaluate_Tg() {
        try {
            Assertions.assertEquals(Calculator.evaluate("tg 0").toString(), Complex.valueOf(0).toString());
            Assertions.assertEquals(Calculator.evaluate("tg 2+3i").toString(), Complex.valueOf(-0.003764025641504247, 1.00323862735361).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Cotangent")
    void evaluate_Ctg() {
        try {
            Assertions.assertEquals(Calculator.evaluate("ctg 0").toString(), Complex.valueOf((double) 1 / 0).toString());
            Assertions.assertEquals(Calculator.evaluate("ctg 2+3i").toString(), Complex.valueOf(-0.003739710376337008, -0.9967577965693583).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Example")
    void evaluate_Example() {
        try {
            Assertions.assertEquals(Calculator.evaluate("sin + - 1 2 1").toString(), Complex.valueOf(0).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }

    @Test
    @DisplayName("Complex expression")
    void evaluate_ComplexExpression() {
        try {
            Assertions.assertEquals(Calculator.evaluate("/ - 6 sqrt - pow -6 2 * 4 * 2 4 * 2 2").toString(), Complex.valueOf(1).toString());
            Assertions.assertEquals(Calculator.evaluate("/ + 6 sqrt - pow -6 2 * 4 * 2 4 * 2 2").toString(), Complex.valueOf(2).toString());
            Assertions.assertEquals(Calculator.evaluate("/ - 4 sqrt - pow -4 2 * 4 * 0.25 25 * 2 0.25").toString(), Complex.valueOf(8, -6).toString());
            Assertions.assertEquals(Calculator.evaluate("/ + 4 sqrt - pow -4 2 * 4 * 0.25 25 * 2 0.25").toString(), Complex.valueOf(8, 6).toString());
        }
        catch(ParseException E) {
            E.printStackTrace();
        }
    }
}
