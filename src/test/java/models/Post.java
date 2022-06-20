package models;

import java.util.HashMap;
import java.util.Objects;

public class Post {

    public Post() {
    }

    private int userId;
    private int id;
    private String title;
    private String body;


    public Post(Object object) {
        HashMap<String, Object> map = (HashMap<String, Object>) object;
        userId = (int) (long) map.getOrDefault("userId", null);
        id = (int) (long) map.getOrDefault("id", null);
        title = (String) map.getOrDefault("title", null);
        body = (String) map.getOrDefault("body", null);
    }


    public int getUserID() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post that = (Post) o;
        return userId == that.userId
                && id == that.id
                && title.isEmpty() == that.title.isEmpty()
                && body.isEmpty() == that.body.isEmpty();
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id);
    }

    @Override
    public String toString() {
        return "Post{" +
                "userID=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
