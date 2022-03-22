package io.github.dtolmachev1.consoleapp;

import io.github.dtolmachev1.pizzeria.Pizzeria;
import io.github.dtolmachev1.pizzeria.PizzeriaConfigurator;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

/**
 * <p>Class for <code>Pizzeria</code>'s console application.</p>
 */
@SuppressWarnings("SpellCheckingInspection")
public class PizzeriaApp implements Runnable {
    private final Pizzeria pizzeria;  // instance of pizzeria class
    private final Random random;  // for pseudorandom number generation
    private final Path filePath;  // default pizzeria file
    private final int QUEUE_CAPACITY = 100;  // maximum capacity of the shared queue
    private final int NPIZZAIOLOS = 10;  // number of pizzaiolos
    private final int NDELIVERERS = 10;  // number of deliverers
    private final int DELIVERERS_CAPACITY = 5;  // maximum possible capacity of deliverers bag
    private final int TIME = 10000;  // execution time
    private final Charset CHARSET = StandardCharsets.UTF_8;  // for UTF-8 encoding

    /**
     * <p>Default constructor to initialize internal structures.</p>
     */
    public PizzeriaApp() {
        this.random = new Random();
        this.filePath = Paths.get("Pizzeria.json");
        createFile();
        this.pizzeria = new Pizzeria(getConfigurator());
    }

    /**
     * <p>Starts all threads for a specified time.</p>
     */
    @Override
    public void run() {
        System.out.printf("%7s |%11s\n", "number", "state");
        System.out.printf("%8s+%12s\n", "--------", "------------");
        this.pizzeria.start();
        try {
            Thread.sleep(this.TIME);
        } catch (InterruptedException ignored) {}
            this.pizzeria.stop();
    }

    /* Creates json file with pizzeria configuration */
    private void createFile() {
        try {  // trying to open output file
            if(!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
            Writer writer = Files.newBufferedWriter(this.filePath, this.CHARSET);
            int[] deliverers = new int[this.NDELIVERERS];
            Arrays.setAll(deliverers, i -> this.random.nextInt(this.DELIVERERS_CAPACITY) + 1);
            PizzeriaConfigurator pizzeriaConfigurator = new PizzeriaConfigurator(this.QUEUE_CAPACITY, this.NPIZZAIOLOS, this.NDELIVERERS, deliverers);
            pizzeriaConfigurator.serialize(writer);
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /* Creates pizzeria configurator from json file */
    private PizzeriaConfigurator getConfigurator() {
        PizzeriaConfigurator pizzeriaConfigurator = new PizzeriaConfigurator();
        try {  // trying to open input file
            Reader reader = Files.newBufferedReader(this.filePath, this.CHARSET);
            pizzeriaConfigurator.deserialize(reader);
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return pizzeriaConfigurator;
    }
}
