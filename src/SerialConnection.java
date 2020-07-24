import java.io.IOException;
import java.io.InputStream;

import com.fazecast.jSerialComm.*;

public class SerialConnection extends Thread {

    private final SerialPort port;

    public SerialConnection() {
        this.port = SerialPort.getCommPorts()[0];
        port.setBaudRate(115200);
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
    }

    public void run() {
        port.openPort();
        InputStream in = port.getInputStream();
        StringBuilder packet = new StringBuilder();
        boolean recording = false;
        try {
            while (port.isOpen()) {
                char p = (char) in.read();
                if (p == 'H') recording = true;
                if (recording) packet.append(p);
                if (p == 'T') {
                    recording = false;
                    if (packet.toString().length() == 18) Main.ValueHandler.update(packet.toString());;
                    packet = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kill() {
        port.closePort();
    }
}
