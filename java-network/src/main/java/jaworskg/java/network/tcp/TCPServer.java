package jaworskg.java.network.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static jaworskg.java.network.tcp.TCPConfig.PORT;

class TCPServer extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(TCPServer.class);

    private ServerSocket serverSocket;
    private boolean running = true;

    TCPServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    @Override
    public void run() {
        LOGGER.info("Started TCP server.");
        try {
            Socket clientConnection = serverSocket.accept();
            while (running) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientConnection.getInputStream()));
                String received = in.readLine();
                LOGGER.info("Received data: " + received);
                DataOutputStream out = new DataOutputStream(clientConnection.getOutputStream());
                out.writeBytes(received.toUpperCase() + "\n");
                if (received.equals("end")) {
                    running = false;
                }
            }
        } catch (IOException e) {
            LOGGER.error("Failed to receive data.");
        }


    }

}
