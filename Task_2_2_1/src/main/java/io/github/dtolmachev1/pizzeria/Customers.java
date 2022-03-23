package io.github.dtolmachev1.pizzeria;

import io.github.dtolmachev1.producer.AbstractProducer;
import io.github.dtolmachev1.queue.SharedQueue;

import java.util.Random;

/**
 * <p>Class for representing customers.</p>
 */
public class Customers extends AbstractProducer {
    private final SharedQueue<Order> orderQueue;  // order list
    private volatile boolean isRunning;  // status flag
    private final Random random;  // for pseudorandom number generation
    private final int DELIVERY_TIME = 1000;  // maximum possible time of delivering order
    private final int SLEEP_TIME = 100;  // maximum possible time between producing new orders

    /**
     * <p>Default constructor to initialize new <code>Customers</code> instance.</p>
     *
     * @param orderQueue Reference to the shared queue of orders.
     */
    public Customers(SharedQueue<Order> orderQueue) {
        this.orderQueue = orderQueue;
        this.isRunning = true;
        this.random = new Random();
    }

    /**
     * <p>Produces new orders.</p>
     */
    @SuppressWarnings("BusyWait")
    @Override
    public void produce() {
        for(int i = 0; this.isRunning; i++) {
            Order order = new Order(i, random.nextInt(this.DELIVERY_TIME));
            this.orderQueue.add(order);
            order.printState();
            this.orderQueue.notifyForEmpty();
            try {
                Thread.sleep(this.random.nextInt(this.SLEEP_TIME));
            } catch (InterruptedException ignored) {}
        }
    }

    /**
     * <p>Stops producing new orders.</p>
     */
    @Override
    public void stop() {
        this.isRunning = false;
    }
}
