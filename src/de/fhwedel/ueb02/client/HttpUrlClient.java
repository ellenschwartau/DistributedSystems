package de.fhwedel.ueb02.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Einfacher HttpClient, der die Klasse Url von Java verwendet und der an einen Server eine einfache
 * HTTP-Anfrage stellen kann.
 * 
 * @author Ellen
 *
 */
public class HttpUrlClient {

    /** Zu nutzender Browser */
    private static final String USER_AGENT = "Mozilla/41.0.2";

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
        executeGet("https://www.google.com", 80);
    }

    /**
     * Führt einen Get-Request aus.
     * 
     * @param url Url
     * @param port Port
     * @throws UnknownHostException
     * @throws IOException
     */
    private static void executeGet(String url, int port) throws UnknownHostException, IOException {
        System.out.println("Baue Verbindung auf:" + url + ":" + port + "...");
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        System.out.println("Sende GET...");
        sendGet(connection, url);
        System.out.println("Lese Response...");
        readResponse(connection);
    }

    /**
     * Sendet einen GET-Request.
     * 
     * @param connection Verbindung
     * @param url Url
     * @throws IOException
     */
    private static void sendGet(HttpURLConnection connection, String url) throws IOException {
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
    }

    /**
     * Verarbeitet die Antwort des GET-Request.
     * 
     * @param connection Verbindung
     * @throws IOException
     */
    private static void readResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(connection.getInputStream()));
        reader.lines().forEach(System.out::println);
        reader.close();
    }

}
