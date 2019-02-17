package jaworskg.java.network.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import static jaworskg.java.network.udp.UDPConfig.PORT;

class EchoUDPServer extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoUDPServer.class);

    private DatagramSocket socket;
    private boolean running = true;
    private byte[] buf = new byte[256];

    EchoUDPServer() throws SocketException {
        socket = new DatagramSocket(PORT);
    }

    @Override
    public void run() {
        LOGGER.info("Started UDP server.");
        try {
            while (running) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());
                LOGGER.info("Received data: " + received);

                if (received.equals("end")) {
                    running = false;
                    continue;
                }
                socket.send(packet);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to receive data.");
        }
    }

}
