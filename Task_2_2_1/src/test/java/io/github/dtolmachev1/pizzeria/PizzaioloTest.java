package io.github.dtolmachev1.pizzeria;

import io.github.dtolmachev1.queue.SharedQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("SpellCheckingInspection")
class PizzaioloTest {
    @BeforeAll
    static void setUp() {
        orderQueue = new SharedQueue<>(0);
        deliveryQueue = new SharedQueue<>(QUEUE_CAPACITY);
        IntStream.range(0, NORDERS).forEach(i -> orderQueue.add(new Order(i)));
        pizzaiolos = Stream.generate(() -> new Pizzaiolo(orderQueue, deliveryQueue)).limit(NPIZZAIOLOS).collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    @DisplayName("Pizzaiolos")
    void pizzaiolo_test() {
        Assertions.assertFalse(orderQueue.isEmpty());
        Assertions.assertTrue(deliveryQueue.isEmpty());
        pizzaiolos.stream().map(Thread::new).forEach(Thread::start);
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException ignored) {}
        pizzaiolos.forEach(Pizzaiolo::stop);
        Assertions.assertTrue(orderQueue.isEmpty());
        Assertions.assertFalse(deliveryQueue.isEmpty());
    }

    private static List<Pizzaiolo> pizzaiolos;  // for generating pizzas
    private static SharedQueue<Order> orderQueue;  // list of orders
    private static SharedQueue<Order> deliveryQueue;  // list of pizzas
    private final static int NPIZZAIOLOS = 5;  // number of pizzaiolos
    private final static int NORDERS = 10;  // number of orders
    private final static int QUEUE_CAPACITY = 10;  // maximum capacity of the shared queue
    private final static int SLEEP_TIME = 1000;  // time for testing
}
