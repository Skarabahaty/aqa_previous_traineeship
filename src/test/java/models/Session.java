package models;

import utils.Randomizer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Session {

    public Session(int border) {
        sessionKey = new Timestamp(System.currentTimeMillis()).toString().replaceAll("\\.\\d+", "");
        createdTime = new Timestamp(System.currentTimeMillis()).toString().replaceAll("\\.\\d+", "");
        this.buildNumber = Randomizer.getRandomIntFromOneToBorder(border);
    }

    private int id;
    private final String sessionKey;
    private final String createdTime;
    private final int buildNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public List<Serializable> getFields() {
        return new LinkedList<>(List.of(sessionKey, createdTime, buildNumber));
    }
}
