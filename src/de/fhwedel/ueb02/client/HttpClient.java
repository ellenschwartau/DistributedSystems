package de.fhwedel.ueb02.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Einfacher HttpClient, der die primitiven Socket-Möglichkeiten von Java verwendet nd der an einen
 * Server eine einfache HTTP-Anfrage stellen kann.
 * 
 * @author Ellen
 *
 */
public class HttpClient {

    /**
     * Führt Get Requests aus.
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        askGoogle();
    }

    /**
     * Sendet einen GET-Request an Google.
     * 
     * @throws UnknownHostException
     * @throws IOException
     */
    private static void askGoogle() throws UnknownHostException, IOException {
        executeGet("google.com", 80);
    }

    /**
     * Führt einen Get-Request aus.
     * 
     * @param host Host
     * @param port Port
     * @throws UnknownHostException
     * @throws IOException
     */
    private static void executeGet(String host, int port) throws UnknownHostException, IOException {
        System.out.println("Baue Verbindung auf:" + host + ":" + port + "...");
        Socket socket = new Socket(InetAddress.getByName(host), 80);
        System.out.println("Sende GET...");
        sendGet(socket, host);
        System.out.println("Lese Response...");
        readResponse(socket);
        socket.close();
    }

    /**
     * Sendet einen Get Request ab.
     * 
     * @param socket Socket
     * @param host Host
     * @throws IOException
     */
    private static void sendGet(Socket socket, String host) throws IOException {
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println("GET /index.html");
        pw.println("Host: " + host);
        pw.flush();
    }

    /**
     * Liest die Response aus.
     * 
     * @param socket Socket
     * @throws IOException
     */
    private static void readResponse(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        reader.lines().forEach(System.out::println);
        reader.close();
    }

}
