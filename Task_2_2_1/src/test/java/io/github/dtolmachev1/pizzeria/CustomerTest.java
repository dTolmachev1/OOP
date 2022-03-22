package io.github.dtolmachev1.pizzeria;

import io.github.dtolmachev1.queue.SharedQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerTest {
    @BeforeAll
    static void setUp() {
    orderQueue = new SharedQueue<>(0);
    customers = new Customers(orderQueue);
    }

    @Test
    @DisplayName("Customers")
    void customers_test() {
    Assertions.assertTrue(orderQueue.isEmpty());
    Thread customersThread = new Thread(customers);
    customersThread.start();
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException ignored) {}
        customers.stop();
    Assertions.assertFalse(orderQueue.isEmpty());
    }

    private static Customers customers;  // for generating orders
    private static SharedQueue<Order> orderQueue;  // list of orders
    private final static int SLEEP_TIME = 1000;  // time for testing
}
