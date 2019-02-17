package jaworskg.java.network.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

import static jaworskg.java.network.tcp.TCPConfig.PORT;

class TCPClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TCPClient.class);

    private Socket clientConnection;
    private BufferedReader in;
    private DataOutputStream out;

    TCPClient() throws IOException {
        clientConnection = new Socket("localhost", PORT);
        in = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
        out = new DataOutputStream(clientConnection.getOutputStream());
    }

    String sendMessage(String message) throws IOException {
        out.writeBytes(message + "\n");
        String received = in.readLine();
        LOGGER.info("Received: " + received);
        return received;
    }

    void close() throws IOException {
        clientConnection.close();
    }


}
