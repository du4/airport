package by.it.pvt.du4.beans;

import java.io.Serializable;

public class Command implements Serializable {
    private int id =0;
    private String name;

    public Command() {
    }

    public Command(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
