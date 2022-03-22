package io.github.dtolmachev1.pizzeria;

import io.github.dtolmachev1.queue.SharedQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("SpellCheckingInspection")
class DelivererTest {
    @BeforeAll
    static void setUp() {
        deliveryQueue = new SharedQueue<>(QUEUE_CAPACITY);
        IntStream.range(0, NORDERS).forEach(i -> deliveryQueue.add(new Order(i)));
        deliverers = Arrays.stream(DELIVERERS_CAPACITY).mapToObj(delivererCapacity -> new Deliverer(deliveryQueue, delivererCapacity)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    @DisplayName("Deliverers")
    void deliverers_test() {
        Assertions.assertFalse(deliveryQueue.isEmpty());
        deliverers.stream().map(Thread::new).forEach(Thread::start);
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException ignored) {}
        deliverers.forEach(Deliverer::stop);
        Assertions.assertTrue(deliveryQueue.isEmpty());
    }

    private static List<Deliverer> deliverers;  // for delivering pizzas
    private static SharedQueue<Order> deliveryQueue;  // list of pizzas
    private final static int NORDERS = 20;  // number of orders
    private final static int QUEUE_CAPACITY = 20;  // maximum capacity of the shared queue
    private final static int[] DELIVERERS_CAPACITY = new int[]{3, 4, 3, 4, 3};
    private final static int SLEEP_TIME = 1000;  // time for testing
}
