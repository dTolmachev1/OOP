package io.github.dtolmachev1.pizzeria;

import io.github.dtolmachev1.queue.SharedQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Class for representing pizzeria.</p>
 */
@SuppressWarnings("SpellCheckingInspection")
public class Pizzeria {
    private final Customers customers;  // for generating orders
    private final List<Pizzaiolo> pizzaiolos;  // for generating pizzas
    private final List<Deliverer> deliverers;  // for delivering pizzas
    private final SharedQueue<Order> orderQueue;  // list of orders
    private final SharedQueue<Order> deliveryQueue;  // list of pizzas
    private final int SLEEP_TIME = 1000;  // time for sleep before stopping

    /**
     * <p>Constructor to initialize new <code>Pizzeria</code> instance with <code>PizzeriaConfigurator</code>.</p>
     *
     * @param pizzeriaConfigurator Configurator with some parameters of newly created <code>Pizzeria</code>.
     */
    public Pizzeria(PizzeriaConfigurator pizzeriaConfigurator) {
        this.orderQueue = new SharedQueue<>(0);
        this.deliveryQueue = new SharedQueue<>(pizzeriaConfigurator.getQueueCapacity());
        this.customers = new Customers(this.orderQueue);
        this.pizzaiolos = Arrays.stream(pizzeriaConfigurator.getPizzaiolosCookTime()).mapToObj(pizzaioloCookTime -> new Pizzaiolo(this.orderQueue, this.deliveryQueue, pizzaioloCookTime)).collect(Collectors.toCollection(ArrayList::new));
        this.deliverers = Arrays.stream(pizzeriaConfigurator.getDeliverersCapacity()).mapToObj(delivererCapacity -> new Deliverer(this.deliveryQueue, delivererCapacity)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * <p>Starts all threads.</p>
     */
    public void start() {
        Thread customersThread = new Thread(this.customers);
        customersThread.start();
        this.pizzaiolos.stream().map(Thread::new).forEach(Thread::start);
        this.deliverers.stream().map(Thread::new).forEach(Thread::start);
    }

    /**
     * <p>Stops all threads.</p>
     */
    public void stop() {
        this.customers.stop();
        try {
            Thread.sleep(this.SLEEP_TIME);
        } catch (InterruptedException ignored) {}
        this.pizzaiolos.forEach(Pizzaiolo::stop);
        try {
            Thread.sleep(this.SLEEP_TIME);
        } catch (InterruptedException ignored) {}
        this.deliverers.forEach(Deliverer::stop);
    }
}
