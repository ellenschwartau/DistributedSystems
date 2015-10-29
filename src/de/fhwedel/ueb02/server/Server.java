package de.fhwedel.ueb02.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server, der einfache HTTP-Anfragen akzeptiert und bearbeitet, indem er die index.html als Antwort
 * liefert.
 * 
 * @author Ellen
 *
 */
public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1106);
        System.out.println("HttpServer auf Port: " + serverSocket.getLocalPort());
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New connection accepted " + socket.getInetAddress() + ":"
                    + socket.getPort());
            HttpRequestHandler request = new HttpRequestHandler(socket);
            // Create a new thread to process the request.
            Thread thread = new Thread(request);
            // Start the thread.
            thread.start();
        }
    }

}
