package io.github.dtolmachev1.pizzeria;

import io.github.dtolmachev1.consumer.AbstractConsumer;
import io.github.dtolmachev1.queue.SharedQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>Class for representing deliverer.</p>
 */
public class Deliverer extends AbstractConsumer {
    private final SharedQueue<Order> deliveryQueue;  // pizzas list
    private final int bagCapacity;  // maximum capacity of bag
    private volatile boolean isRunning;  // status flag
    private final Random random;  // for pseudorandom number generation
    private final int SLEEP_TIME = 500;  // maximum possible time between delivering pizzas

    /**
     * <p>Default constructor to initialize new <code>Deliverer</code> instance.</p>
     *
     * @param deliveryQueue Reference to the shared queue of already cooked pizzas.
     * @param bagCapacity Capacity of the bag.
     */
    public Deliverer(SharedQueue<Order> deliveryQueue, int bagCapacity) {
        this.deliveryQueue = deliveryQueue;
        this.bagCapacity = bagCapacity;
        this.isRunning = true;
        this.random = new Random();
    }

    /**
     * <p>Consumes pizzas.</p>
     */
    @SuppressWarnings("BusyWait")
    @Override
    public void consume() {
        while(this.isRunning) {
            List<Order> orders = new ArrayList<>(this.bagCapacity);
            for(int i = 0; i < this.bagCapacity; i++) {
                while (this.deliveryQueue.isEmpty()) {
                    try {
                        this.deliveryQueue.waitOnEmpty();
                    } catch (InterruptedException e) {
                        if(!this.isRunning) {
                            break;
                        }
                    }
                }
                if (!this.isRunning) {
                    break;
                }
                Order order = this.deliveryQueue.remove();
                if(order == null) {
                    i--;
                    continue;
                }
                orders.add(order);
                this.deliveryQueue.notifyForFull();
            }
            for(Order order : orders) {
                order.updateState();
                order.printState();
                try {
                    Thread.sleep(order.getDeliveryTime());
                } catch (InterruptedException ignored) {}
                order.updateState();
                order.printState();
            }
            try {
                Thread.sleep(this.random.nextInt(this.SLEEP_TIME));
            } catch (InterruptedException ignored) {}
        }
    }

    /**
     * <p>Stops consuming pizzas.</p>
     */
    @Override
    public void stop() {
        this.isRunning = false;
        this.deliveryQueue.notifyForEmpty();
    }
}
