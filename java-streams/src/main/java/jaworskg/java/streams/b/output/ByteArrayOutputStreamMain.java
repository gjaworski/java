package jaworskg.java.streams.b.output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayOutputStreamMain {

    public static void main(String[] args) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            outputStream.write(1);
            outputStream.write(2);
            outputStream.write(3);
            outputStream.write(4);
            outputStream.write(new byte[]{1, 2, 3, 4});
            outputStream.write(new byte[]{0, 1, 2, 3, 4, 5}, 1, 4);
            System.out.println("Size of the stream is: " + outputStream.size());
            System.out.println("Content of the stream is: " + Arrays.toString(outputStream.toByteArray()));
        } catch (IOException e) {
            System.out.println("Exception has been thrown!");
        }
    }
}
