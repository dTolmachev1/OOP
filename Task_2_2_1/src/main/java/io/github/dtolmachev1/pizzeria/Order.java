package io.github.dtolmachev1.pizzeria;

/**
 * <p>Class for representing order.</p>
 */
public class Order {
    private final int number;  // order number
    private final int deliveryTime;  // time of delivering
    private State state;  // order state

    /**
     * <p>Default constructor to initialize new order.</p>
     *
     * @param number Number of newly created order.
     * @param deliveryTime time of delivering pizza.
     */
    public Order(int number, int deliveryTime) {
        this.number = number;
        this.state = State.PENDING;
        this.deliveryTime = deliveryTime;
    }

    /**
     * <p>Returns the number of order.</p>
     *
     * @return Number of order.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * <p>Returns time of delivering for this order.</p>
     *
     * @return Time of delivering for this order.
     */
    public int getDeliveryTime() {
        return this.deliveryTime;
    }

    /**
     * <p>Returns current state of the order.</p>
     *
     * @return Current state of the order.
     */
    public String getState() {
        return this.state.toString();
    }

    /**
     * <p>Prints state of the order.</p>
     */
    public void printState() {
        System.out.printf("%7d |%11s\n", this.number, this.state.toString());
    }

    /**
     * <p>Updates state of the order.</p>
     *
     * @return <code>true</code> if state was updated or <code>false</code> otherwise.
     */
    public boolean updateState() {
        State[] values = State.values();
        int nextOrdinal = this.state.ordinal() + 1;
        if(nextOrdinal < values.length) {
            this.state = values[nextOrdinal];
            return true;
        }
        return false;
    }

    /* Enum for possible states of order */
    private enum State {
        PENDING {
            @Override
            public String toString() {
                return "pending";
            }
        },
        COOKING {
            @Override
            public String toString() {
                return "cooking";
            }
        },
        COOKED {
            @Override
            public String toString() {
                return "cooked";
            }
        },
        DELIVERING {
            @Override
            public String toString() {
                return "delivering";
            }
        },
        COMPLETED {
            @Override
            public String toString() {
                return "completed";
            }
        }
    }
}
