package models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Project {

    private int id;
    private final String name;

    public Project(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Serializable> getFields() {
        return new LinkedList<>(List.of(name));
    }
}
