package io.github.dtolmachev1;

import java.util.Scanner;

/**
 * <p>A simple console calculator.</p>
 */
public class ConsoleCalculator {
    public static void main(String[] args) {
        /* reading queries */
        Scanner input = new Scanner(System.in, CHARSET);
        System.out.println("Enter the expression:");
        while(!input.hasNext("[Ee]xit")) {
            String query = input.nextLine();
            try {
                System.out.println("Answer: " + Calculator.evaluate(query) + "\n");
            }
            catch(ParseException E) {
                System.out.println("Syntax error!\n");
            }
            finally {
                System.out.println("Enter the expression:");
            }
        }
        System.out.println("Exiting...");
        System.exit(0);
    }

    private static final String CHARSET = "UTF-8";
}
