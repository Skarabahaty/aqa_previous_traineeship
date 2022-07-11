package models;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Author {

    public Author(JsonObject author) {
        name = author.get("name").getAsString();
        login = author.get("login").getAsString();
        email = author.get("email").getAsString();
    }


    private int id;
    private final String name;
    private final String login;
    private final String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public List<Serializable> getFields() {
        return new LinkedList<>(List.of(name, login, email));
    }
}
