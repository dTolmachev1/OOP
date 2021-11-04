package io.github.dtolmachev1;

import java.util.Scanner;

/**
 * <p>A simple console calculator.</p>
 */
public class ConsoleCalculator {
    public static void main(String[] args) {
        /* trying to load some calculator */
        if(loadCalculator()) {
            /* reading queries */
            Scanner input = new Scanner(System.in, CHARSET);
            System.out.println("Enter the expression:");
            while (!input.hasNext("[Ee]xit")) {
                String query = input.nextLine();
                try {
                    System.out.println("Answer: " + calculator.calculate(query) + "\n");
                } catch (ParseException E) {
                    System.out.println("Syntax error!\n");
                } finally {
                    System.out.println("Enter the expression:");
                }
            }
        }
        else System.out.println("Calculator can't be loaded!\n");
        System.out.println("Exiting...");
        System.exit(0);
    }

    /* tries to dynamically load complex or double calculator */
    private static boolean loadCalculator() {
        CalculatorLoader calculatorLoader = new CalculatorLoader();  // for dynamically service loading
        for(String type : CALCULATORS) {
            calculator = calculatorLoader.loadCalculatorInstance(type);
            if(calculator != null) {
                return true;
            }
        }
        return false;
    }

    private static Calculator calculator;  // for instantiating calculator
    private static final String[] CALCULATORS = {"Complex", "Double"};  // list of available calculators
    private static final String CHARSET = "UTF-8";
}
