package io.github.dtolmachev1.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>Class for representing queue shared between producer and consumer</p>
 *
 * @param <T> Type of the shared data.
 */
public class SharedQueue<T> {
    private final Queue<T> queue;  // for storing shared data
    private final int queueCapacity;  // maximum possible capacity of the shared queue
    private final Object EMPTY = new Object();  // for locking on empty queue
    private final Object FULL = new Object();  // for locking on full queue

    /**
     * <p>Default constructor for initializing internal structures.</p>
     *
     * @param queueCapacity Maximum possible capacity of the shared queue.
     */
    public SharedQueue(int queueCapacity) {
        this.queue = new LinkedList<>();
        this.queueCapacity = queueCapacity;
    }

    /**
     * <p>Checks if queue is empty.</p>
     *
     * @return <code>true</code> if queue contains no elements or <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        synchronized(this.queue) {
            return this.queue.isEmpty();
        }
    }

    /**
     * <p>Checks if queue is full.</p>
     *
     * @return <code>true</code> if queue contains <code>maxSize</code> elements or <code>false</code> otherwise.
     */
    public boolean isFull() {
        synchronized(this.queue) {
            return this.queue.size() >= this.queueCapacity;
        }
    }

    /**
     * <p>Locks when queue is empty.</p>
     *
     * @throws InterruptedException If waiting was interrupted unexpectedly.
     */
    public void waitOnEmpty() throws InterruptedException {
        synchronized(this.EMPTY) {
            this.EMPTY.wait();
        }
    }

    /**
     * <p>Locks when queue is full.</p>
     *
     * @throws InterruptedException If waiting was interrupted unexpectedly.
     */
    public void waitOnFull() throws InterruptedException {
        synchronized(this.FULL) {
            this.FULL.wait();
        }
    }

    /**
     * <p>Releases lock when queue is not empty.</p>
     */
    public void notifyForEmpty() {
        synchronized(this.EMPTY) {
            this.EMPTY.notifyAll();
        }
    }

    /**
     * <p>Releases lock when queue is not full.</p>
     */
    public void notifyForFull() {
        synchronized(this.FULL) {
            this.FULL.notifyAll();
        }
    }

    /**
     * <p>Adds new data to the queue.</p>
     *
     * @param data Data to be added.
     */
    public void add(T data) {
        synchronized(this.queue) {
            this.queue.add(data);
        }
    }

    /**
     * <p>Removes data from queue.</p>
     *
     * @return Removed value.
     */
    public T remove() {
        synchronized(this.queue) {
            return this.queue.poll();
        }
    }
}
