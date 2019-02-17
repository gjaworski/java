package jaworskg.java.network.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

import static jaworskg.java.network.udp.UDPConfig.ADDRESS;
import static jaworskg.java.network.udp.UDPConfig.PORT;

class EchoUDPClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoUDPClient.class);

    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buffer;

    EchoUDPClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName(ADDRESS);
    }

    public String sendEcho(String message) throws IOException {
        LOGGER.info("Sending message: " + message);
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
        socket.send(packet);
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    public void close() {
        socket.close();
        LOGGER.info("Closed socket.");
    }
}
