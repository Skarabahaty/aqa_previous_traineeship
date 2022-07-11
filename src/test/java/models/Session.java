package models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Session {

    public Session(int buildNumber) {
        sessionKey = new Timestamp(System.currentTimeMillis()).toString().replaceAll("\\.\\d+", "");
        createdTime = new Timestamp(System.currentTimeMillis()).toString().replaceAll("\\.\\d+", "");
        this.buildNumber = ThreadLocalRandom.current().nextInt(1, buildNumber);
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
