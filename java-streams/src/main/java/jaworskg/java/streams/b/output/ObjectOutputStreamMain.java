package jaworskg.java.streams.b.output;

import jaworskg.java.streams.utils.Box;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class ObjectOutputStreamMain {

    private static final String BOX_FILE = "box.ser";

    public static void main(String[] args) {
        File file = new File(Objects.requireNonNull(ObjectOutputStreamMain.class
                .getClassLoader()
                .getResource(BOX_FILE))
                .getFile());
        Box box = new Box("Saturn", 7);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(box);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
