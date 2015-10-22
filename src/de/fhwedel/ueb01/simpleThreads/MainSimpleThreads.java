/**
 * 
 */
package de.fhwedel.ueb01.simpleThreads;

import java.util.stream.Stream;

/**
 * Einfache Thread Programme Schreiben Sie bitte ein einfaches Programm, das zwei oder mehr Threads
 * erzeugt und startet. Jeder Thread produziert Ausgaben. Lassen Sie bitte das Programm laufen und
 * beobachten Sie sein Ausgabeverhalten.
 * 
 * @author Ellen
 *
 */
public class MainSimpleThreads {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Stream.of(args).forEach(MainSimpleThreads::startThread);
    }

    /**
     * Startet für jeden Programmparameter einen Thread zur ständigen Ausgabe auf Konsole.
     * 
     * @param str Ausgabe
     */
    public static void startThread(String str) {
        new Thread(new Output(str)).start();
    }
}
