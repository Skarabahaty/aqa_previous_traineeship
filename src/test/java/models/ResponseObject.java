package models;

import org.json.JSONObject;

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
}
