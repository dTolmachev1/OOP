package io.github.dtolmachev1.consumer;

/**
 * <p>Abstract class for representing consumer, should be extend with some implementation.</p>
 */
public abstract class AbstractConsumer implements Runnable {
    /**
     * <p>Runs consumer, calls when thread started.</p>
     */
    @Override
    public void run() {
        consume();
    }

    /**
     * <p>Consumes some data, should be overwritten.</p>
     */
    protected abstract void consume();

    /**
     * <p>Stops consuming data, should be overwritten.</p>
     */
    protected abstract void stop();
}
