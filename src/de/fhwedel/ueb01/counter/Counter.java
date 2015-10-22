package de.fhwedel.ueb01.counter;

/**
 * Abstrakte Counter-Klasse.
 * 
 * @author Ellen
 *
 */
public abstract class Counter {

    public abstract void increment();

    public abstract void decrement();

    public abstract int value();

    /**
     * Prüft ob der Ergebniswert dem erwarteten entspricht.
     * 
     * @param given vorhandener Wert
     * @param expected erwarteter Wert
     */
    public void checkForInterference(int given, int expected) {
        assert (given == expected) : "Interferenz aufgetreten!";
    }
}
