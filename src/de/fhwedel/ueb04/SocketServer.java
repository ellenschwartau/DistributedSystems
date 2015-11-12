package de.fhwedel.ueb04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import de.fhwedel.ueb04.LogMessageProtos.LogMessage;

public class SocketServer {

    private static PrintStream out = null;
    private static ServerSocket s = null;
    private static Socket connection = null;
    private static BufferedReader in = null;

    public static void main(String args[]) {

        try {
            s = new ServerSocket(5000, 10);
            echo("Server socket erstellt. Verbindungsaufbau...");
            connection = s.accept();
            echo("Verbindung aufgebaut von: " + connection.getInetAddress().getHostName() + " : "
                    + connection.getPort());

            out = new PrintStream(connection.getOutputStream());
            out.print(new String("Connected to server.").toCharArray());
            out.flush();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (true) {
                parseInput(in);
            }

        } catch (IOException e) {
            System.err.println("IOException");
        }

        try {
            in.close();
            out.close();
            s.close();
        } catch (IOException ioException) {
            System.err.println("Verbindung konnte nicht geschlossen werden.");
        }
    }

    public static void echo(String msg) {
        System.out.println(msg);
    }

    private static void parseInput(BufferedReader in) {

    }

    private static void writeToLogFile(LogMessage message) {

    }
}
