package de.fhwedel.ueb01.counter;

/**
 * Synchronisierter Counter.
 * 
 * @author Ellen
 *
 */
public class SynchronizedCounter extends Counter {

    private int c = 0;

    public synchronized void increment() {
        int expected = this.c + 1;
        int b = this.c;
        b = b + 1;
        this.c = b;
        assert (this.c == expected) : "Interferenz aufgetreten!";
    }

    public synchronized void decrement() {
        int expected = this.c - 1;
        int d = this.c;
        d = d - 1;
        this.c = d;
        assert (this.c == expected) : "Interferenz aufgetreten!";
    }

    public synchronized int value() {
        return this.c;
    }
}
