package io.github.dtolmachev1.producer;

/**
 * <p>Abstract class for representing abstract producer, should be extend with some implementation.</p>
 */
public abstract class AbstractProducer implements Runnable {
    /**
     * <p>Runs producer, calls when thread started.</p>
     */
    @Override
    public void run() {
        produce();
    }

    /**
     * <p>Produces some data, should be overwritten.</p>
     */
    protected abstract void produce();

    /**
     * <p>Stops producing new data, should be overwritten.</p>
     */
    protected abstract void stop();
}
