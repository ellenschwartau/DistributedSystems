package de.fhwedel.ueb04;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import de.fhwedel.ueb04.LogMessageProtos.LogMessage;
import de.fhwedel.ueb04.LogMessageProtos.LogMessage.ID;
import de.fhwedel.ueb04.LogMessageProtos.LogMessage.Priority;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket();

        try {
            s.connect(new InetSocketAddress("localhost", 5000));
            System.out.println("Connected");

            LogMessage message = LogMessage.newBuilder()
                    .setContent("Test Message")
                    .setPriority(Priority.INFO)
                    .setTimestamp(new Date().toString())
                    .setId(ID.newBuilder().setAutor("Ellen").setLocation("Hamburg").build())
                    .build();
            message.writeTo(s.getOutputStream());

        } catch (UnknownHostException e) {
            System.err.println("Cant't find Host");
            System.exit(1);
        }
    }

}
