package models;

import java.util.Objects;

public class Post {

    private final int authorId;
    private final String postText;

    public Post(int authorId, String postText) {
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
        Post post = (Post) o;
        return authorId == post.authorId &&
                postText.equals(post.postText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, postText);
    }
}
