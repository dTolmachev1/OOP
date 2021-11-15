package io.github.dtolmachev1;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

/**
 * <p>Main class for ConsoleNotebook.</p>
 */
public class ConsoleApp {
    public static void main(String[] args) {
        ConsoleNotebook consoleNotebook = new ConsoleNotebook();
        try {  // trying to parse command line arguments
            CommandLine cmd = consoleNotebook.parseCommands(args);  // parsing command line
            consoleNotebook.processCommands(cmd);
        } catch(ParseException e) {
            System.out.println("Invalid argument!");
        }
    }
}
