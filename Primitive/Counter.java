package Primitive;
/**
 * The Counter class represents a simple counter that can be increased, decreased, and queried for its current value.
 */
public class Counter {
    private int num;
    /**
     * Creates a new Counter with an initial value of 0.
     */
    public Counter() {
        num = 0;
    }
    /**
     * Increases the counter by a specified number.
     * @param number the number to increase the counter by
     */
    public void increase(int number) {
        num += number;
    }
    /**
     * Decreases the counter by a specified number.
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        num -= number;
    }
    /**
     * Returns the current value of the counter.
     * @return the current value of the counter
     */
    public int getValue() {
        return num;
    }
    /**
     * Sets the value of the counter to a specified number.
     * @param number the number to set as the new value of the counter
     */
    public void setValue(int number) {
        num = number;
    }
}
