package jaworskg.java.streams.utils;

import java.io.Serializable;

public class Box implements Serializable {

    private static final long serialVersionUID = 1;

    private String name;
    private int id;

    public Box(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Box{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

}
