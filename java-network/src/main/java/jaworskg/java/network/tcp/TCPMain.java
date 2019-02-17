package jaworskg.java.network.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TCPMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(TCPMain.class);

    public static void main(String... args) throws IOException {
        TCPServer server = new TCPServer();
        server.start();
        TCPClient client = new TCPClient();

        String received = client.sendMessage("aaa");
        LOGGER.info("Received data: " + received);
        received = client.sendMessage("bbb");
        LOGGER.info("Received data: " + received);
        received = client.sendMessage("end");
        LOGGER.info("Received data: " + received);
        client.close();
        System.exit(0);

    }

}
