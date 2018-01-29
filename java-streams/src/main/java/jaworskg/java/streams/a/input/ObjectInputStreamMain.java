package jaworskg.java.streams.a.input;

import jaworskg.java.streams.utils.Box;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

public class ObjectInputStreamMain {

    private static final String BOX_FILE = "box.ser";

    public static void main(String[] args) {
        File file = new File(Objects.requireNonNull(ObjectInputStreamMain.class
                .getClassLoader()
                .getResource(BOX_FILE))
                .getFile());
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Box box = (Box) inputStream.readObject();
            System.out.println(box);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
