package models;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class ResponseObject {

    public ResponseObject(JSONObject jsonObject) {
        this.userID = jsonObject.getInt("userId");
        this.id = jsonObject.getInt("id");
        this.title = jsonObject.getString("title");
        this.body = jsonObject.getString("body");
    }


    private final int userID;
    private final int id;
    private final String title;
    private final String body;

    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


    public void put(HashMap<String, Object> map) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseObject that = (ResponseObject) o;
        return userID == that.userID
                && id == that.id
                && title.isEmpty() == that.title.isEmpty()
                && body.isEmpty() == that.body.isEmpty();
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, id);
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "userID=" + userID +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
