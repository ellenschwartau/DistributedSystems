/**
 * 
 */
package de.fhwedel.ueb01.counter;

/**
 * @author Ellen
 *
 */
public class MainCounter {

    /** Anzahl der Threads */
    private static final int THREAD_COUNT = 10;

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
            case "synchronized":
                System.out.println("Starte synchronisierten Counter");
                startThreads(new SynchronizedCounter());
                break;
            default:
                System.out.println("Starte unsynchronisierten Counter");
                startThreads(new UnsynchronizedCounter());
            }
        } else {
            System.out.println("Bitte Programmparameter angeben.");
        }
    }

    public static void startThreads(Counter counter) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            // System.out.println("Starte Thread: " + i);
            new Thread(new CounterThread(counter)).start();
        }
    }
}
