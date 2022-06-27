package models;

import java.util.Objects;

public class Post implements Returnable {

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    private final int userId;
    private final int id;
    private final String title;
    private final String body;

    public int getUserID() {
        return userId;
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

    public boolean isPostEmpty() {
        return getId() == 0
                && getUserID() == 0
                && getBody() == null
                && getTitle() == null;
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
