/**
 * 
 */
package de.fhwedel.ueb01;

/**
 * Klasse zur ständigen Ausgabe eines Strings.
 * 
 * @author Ellen
 */
public class Output implements Runnable {

    /**
     * String der ausgegeben werden soll
     */
    private final String output;

    /**
     * Konstruktorfunktion
     */
    public Output(String str) {
        this.output = str;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this.output);
        }
    }

}
