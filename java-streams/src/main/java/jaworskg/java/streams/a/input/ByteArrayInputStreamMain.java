package jaworskg.java.streams.a.input;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayInputStreamMain {

    public static void main(String[] args) {
        byte[] input = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(input)) {
            System.out.println("inputStream.markSupported(): " + inputStream.markSupported());
            System.out.println("inputStream.available(): " + inputStream.available());
            System.out.println("inputStream.read(): " + inputStream.read());
            inputStream.mark(3);
            System.out.println("inputStream.read(): " + inputStream.read());
            System.out.println("inputStream.read(): " + inputStream.read());
            System.out.println("inputStream.read(): " + inputStream.read());
            inputStream.reset();
            System.out.println("inputStream.available(): " + inputStream.available());
            System.out.println("inputStream.read(): " + inputStream.read());
        } catch (IOException e) {
            System.out.println("Exception has been thrown!");
        }
    }

}
