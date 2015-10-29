package de.fhwedel.ueb02.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class HttpRequestHandler implements Runnable {

    private Socket socket;

    public HttpRequestHandler(Socket socket) throws Exception {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String headerLine =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()))
                                .readLine();
                System.out.println(headerLine);
                if (!(headerLine.equals("\n") || headerLine.equals(""))
                        && new StringTokenizer(headerLine).nextToken().equals("GET")) {
                    socket.getOutputStream().write(Files.readAllBytes(Paths.get("../index.html")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
