package de.fhwedel.ueb01.counter;

/**
 * Klasse die mit einem Counter arbeitet und eine Variable manipuliert.
 * 
 * @author Ellen
 *
 */
public class CounterThread implements Runnable {

    /** Counter Klasse zur Manipulation des Zählers */
    private Counter counter;

    /**
     * Setzt den zu verwendenen Counter
     * 
     * @param counter
     */
    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (true) {
            if (Math.random() > 0.5) {
                System.out.println(Thread.currentThread().getName() + " inkrementiert!");
                counter.increment();
            } else {
                System.out.println(Thread.currentThread().getName() + " dekrementiert!");
                counter.decrement();
            }
        }
    }

}
