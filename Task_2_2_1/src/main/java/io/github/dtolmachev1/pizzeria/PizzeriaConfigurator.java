package io.github.dtolmachev1.pizzeria;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;

/**
 * <p>Class for storing pizzerias configuration.</p>
 */
@SuppressWarnings("SpellCheckingInspection")
public class PizzeriaConfigurator {
    private int queueCapacity;  // maximum possible capacity of the shared queue
    private int nPizzaiolos;  // number of pizzaiolos
    private int nDeliverers;  // number of deliverers
    private int[] deliverersCapacity;  // bag capacity for each deliverer
    private transient final Gson gson;  // for json serialization

    /**
     * <p>Default constructor to initialize internal structures.</p>
     */
    public PizzeriaConfigurator() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        this.gson = builder.create();
    }

    /**
     * <p>Constructor to initialize new <code>PizzeriaConfigurator</code> with the specified values.</p>
     *
     * @param queueCapacity Maximum possible capacity of the shared queue.
     * @param nPizzaiolos Number of pizzaiolos.
     * @param nDeliverers Number of deliverers.
     * @param deliverersCapacity Bag capacity for each deliverer.
     */
    public PizzeriaConfigurator(int queueCapacity, int nPizzaiolos, int nDeliverers, int[] deliverersCapacity) {
        this();
        this.queueCapacity = queueCapacity;
        this.nPizzaiolos = nPizzaiolos;
        this.nDeliverers = nDeliverers;
        this.deliverersCapacity = deliverersCapacity;
    }

    /**
     * <p>Returns capacity of the shared queue.</p>
     *
     * @return Capacity of the shared queue.
     */
    public int getQueueCapacity() {
        return this.queueCapacity;
    }

    /**
     * <p>Returns number of pizzaiolos.</p>
     *
     * @return number of pizzaiolos.
     */
    public int getNPizzaiolos() {
        return this.nPizzaiolos;
    }

    /**
     * <p>Returns number of deliverers.</p>
     *
     * @return number of deliverers.
     */
    public int getNDeliverers() {
        return this.nDeliverers;
    }

    /**
     * <p>Returns bag capacity for each deliverer.</p>
     *
     * @return Bag capacity for each deliverer.
     */
    public int[] getDeliverersCapacity() {
        return this.deliverersCapacity;
    }

    /**
     * <p>Serializes content of this <code>PizzeriaConfigurator</code> to the specified writer.</p>
     *
     * @param writer Writer stream for serialization.
     */
    public void serialize(Writer writer) {
        gson.toJson(this, writer);
    }

    /**
     * <p>Deserializes content of this <code>PizzeriaConfigurator</code> to the specified reader.</p>
     *
     * @param reader Reader stream for deserialization.
     */
    public void deserialize(Reader reader) {
        PizzeriaConfigurator pizzeriaConfigurator = gson.fromJson(reader, PizzeriaConfigurator.class);
        this.queueCapacity = pizzeriaConfigurator.queueCapacity;
        this.nPizzaiolos = pizzeriaConfigurator.nPizzaiolos;
        this.nDeliverers = pizzeriaConfigurator.nDeliverers;
        this.deliverersCapacity = pizzeriaConfigurator.deliverersCapacity;
    }
}
