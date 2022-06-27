package models;

import java.util.Objects;

public class Comment {

    private final int authorId;
    private final String postText;

    public Comment(int authorId, String postText) {
        this.authorId = authorId;
        this.postText = postText;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getPostText() {
        return postText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return authorId == comment.authorId &&
                postText.equals(comment.postText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, postText);
    }
}
