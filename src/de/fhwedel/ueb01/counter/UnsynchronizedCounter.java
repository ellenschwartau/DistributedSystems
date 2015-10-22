package de.fhwedel.ueb01.counter;

public class UnsynchronizedCounter extends Counter {

    private int c = 0;

    public void increment() {
        int expected = this.c + 1;
        int b = this.c;
        b = b + 1;
        this.c = b;
        this.checkForInterference(this.c, expected);
    }

    public void decrement() {
        int expected = this.c - 1;
        int d = this.c;
        d = d - 1;
        this.c = d;
        this.checkForInterference(this.c, expected);
    }

    public int value() {
        return this.c;
    }
}
