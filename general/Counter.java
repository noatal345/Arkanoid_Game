package general;

/**
 * this is a simple class that is used for counting things.
 */
public class Counter {
    private int counter;

    /**
     * constructor from initial number.
     * @param num the initial value.
     */
    public  Counter(int num) {
        this.counter = num;
    }

    /**
     * this method add a number to the counting.
     * @param number to increase.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * this method subtract number from the counting.
     * @param number to decrease.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * this method return the current value.
     * @return counter
     */
    // get current count.
    public int getValue() {
        return this.counter;
    }
}