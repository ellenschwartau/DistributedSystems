package de.fhwedel.ueb04;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import de.fhwedel.ueb04.LogMessageProtos.LogMessage;
import de.fhwedel.ueb04.LogMessageProtos.LogMessage.ID;
import de.fhwedel.ueb04.LogMessageProtos.LogMessage.Priority;

/**
 * Socket Client der LogNachrichten an einen Server schicken kann.
 */
public class SocketClient {

    private static final String MALTES_SERVER = "172.26.0.126";
    private static final int MALTES_PORT = 1337;

    private static final String SERVER = "127.0.0.1";
    private static final int PORT = 5000;

    /**
     * Stellt eine Verbindung zum Server her und verschickt eine Log-Nachricht.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Socket s = new Socket();
        connect(s, SERVER, PORT);
        LogMessage message = LogMessage.newBuilder()
                .setContent("Test Message")
                .setPriority(Priority.INFO)
                .setTimestamp(new Date().toString())
                .setId(ID.newBuilder().setAutor("Ellen").setLocation("Hamburg").build())
                .build();
        message.writeDelimitedTo(s.getOutputStream());
        s.close();
    }

    /**
     * Stellt eine Verbindung zum Server her.
     * 
     * @param s
     * @throws IOException
     */
    private static void connect(Socket s, String server, int host) throws IOException {
        try {
            s.connect(new InetSocketAddress(server, host));
            System.out.println("Connected");

        } catch (UnknownHostException e) {
            System.err.println("Cant't find Host");
            System.exit(1);
        }
    }

}
