package de.fhwedel.ueb04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import de.fhwedel.ueb04.LogMessageProtos.LogMessage;

/**
 * Einfacher Socket-Server der auf eingehende Verbindungen wartet und Meldungen in ein Logfile
 * schreibt.
 */
public class SocketServer {

    private static final String LOGFILE_PATH = "./src/de/fhwedel/ueb04/messageLog.log";
    private static PrintStream out = null;
    private static ServerSocket s = null;
    private static Socket connection = null;
    private static BufferedReader in = null;

    /**
     * Wartet auf eingehende Verbindungen und logged eingehende Log-Meldungen in ein Logfile.
     * 
     * @param args
     */
    public static void main(String args[]) {
        try {
            startConnection();
            parseInput(connection.getInputStream());
        } catch (IOException e) {
            System.err.println("Input konnte nicht gelesen werden: ");
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Nimmt eine eingehende Verbindung entgegen und verarbeitet den Input.
     * 
     * @throws IOException
     */
    private static void startConnection() {

        try {
            s = new ServerSocket(5000, 10);
            System.out.println("Server socket erstellt. Verbindungsaufbau...");
            connection = s.accept();
            System.out.println("Verbindung aufgebaut von: "
                    + connection.getInetAddress().getHostName()
                    + " : "
                    + connection.getPort());

            out = new PrintStream(connection.getOutputStream());
            out.print(new String("Connected to server.").toCharArray());
            out.flush();
        } catch (IOException e) {
            System.out.println("Verbindung konnte nicht aufgebaut werden.");
        }
    }

    /**
     * Schließt die aufgebaute Verbindung.
     */
    private static void closeConnection() {
        try {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
            if (s != null)
                s.close();
        } catch (IOException ioException) {
            System.err.println("Verbindung konnte nicht geschlossen werden.");
        }
    }

    /**
     * Parsed den Input.
     * 
     * @param inputStream
     * @throws IOException
     */
    private static void parseInput(InputStream inputStream) {
        try {
            LogMessage logMessage = LogMessage.parseDelimitedFrom(inputStream);
            writeToLogFile(logMessage, LOGFILE_PATH);
        } catch (IOException e) {
            System.out.println("LogMessage konnte nicht geparsed werden: ");
            e.printStackTrace();
        }
    }

    /**
     * Schreibt die emfpangene LogMessage in das Logfile.
     * 
     * @param filePath Pfad zur Log-Datei
     * @param message zu schreibende Nachricht
     */
    private static void writeToLogFile(LogMessage message, String filePath) {
        FileWriter logFileWriter;
        try {
            logFileWriter = new FileWriter(new File(filePath), true);
            System.out.println("Schreibe Nachricht '" + message.toString() + "' in Datei "
                    + filePath);
            logFileWriter.write(message.toString());
            logFileWriter.flush();
        } catch (IOException e) {
            System.out.println("Datei konnte nicht gefunden werden.");
        }
    }
}
