package jaworskg.java.network.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class UDPMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(UDPMain.class);

    public static void main(String... args) throws IOException {
        EchoUDPClient client = new EchoUDPClient();
        EchoUDPServer server = new EchoUDPServer();
        server.start();
        String echo = client.sendEcho("aaa");
        LOGGER.info("Received data: " + echo);
        echo = client.sendEcho("bbb");
        LOGGER.info("Received data: " + echo);
        echo = client.sendEcho("end");
        LOGGER.info("Received data: " + echo);
        client.close();
        System.exit(0);
    }

}
