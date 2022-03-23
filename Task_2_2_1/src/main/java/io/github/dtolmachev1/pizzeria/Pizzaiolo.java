package io.github.dtolmachev1.pizzeria;

import io.github.dtolmachev1.producer.AbstractProducer;
import io.github.dtolmachev1.queue.SharedQueue;

/**
 * <p>Class for representing pizzaiolo.</p>
 */
@SuppressWarnings("SpellCheckingInspection")
public class Pizzaiolo extends AbstractProducer {
    private final SharedQueue<Order> orderQueue;  // order list
    private final SharedQueue<Order> deliveryQueue;  // pizzas list
    private final int cookTime;  // maximum possible time between producing new pizzas
    private volatile boolean isRunning;  // status flag

    /**
     * <p>Default constructor to initialize new <code>Pizzaiolo</code> instance.</p>
     *
     * @param orderQueue Reference to the shared queue of orders.
     * @param deliveryQueue Reference to the shared queue of already cooked pizzas.
     * @param cookTime Time of producing new pizzas.
     */
    Pizzaiolo(SharedQueue<Order> orderQueue, SharedQueue<Order> deliveryQueue, int cookTime) {
        this.orderQueue = orderQueue;
        this.deliveryQueue = deliveryQueue;
        this.cookTime = cookTime;
        this.isRunning = true;
    }

    /**
     * <p>Produces new pizzas.</p>
     */
    @SuppressWarnings("BusyWait")
    @Override
    public void produce() {
        while(this.isRunning) {
            while (this.orderQueue.isEmpty()) {
                try {
                    this.orderQueue.waitOnEmpty();
                } catch (InterruptedException e) {
                    if(!this.isRunning) {
                        break;
                    }
                }
            }
            if(!this.isRunning) {
                break;
            }
            Order order = this.orderQueue.remove();
            if(order == null) {
                continue;
            }
            order.updateState();
            order.printState();
            this.orderQueue.notifyForFull();
            try {
                Thread.sleep(this.cookTime);
            } catch (InterruptedException ignored) {}
            while(this.deliveryQueue.isFull()) {
                try {
                    this.deliveryQueue.waitOnFull();
                } catch (InterruptedException e) {
                    if(!this.isRunning) {
                        break;
                    }
                }
            }
            if(!this.isRunning) {
                break;
            }
            this.deliveryQueue.add(order);
            order.updateState();
            order.printState();
            this.deliveryQueue.notifyForEmpty();
        }
    }

    /**
     * <p>Stops producing new pizzas.</p>
     */
    @Override
    public void stop() {
        this.isRunning = false;
        this.orderQueue.notifyForEmpty();
        this.deliveryQueue.notifyForFull();
    }
}
