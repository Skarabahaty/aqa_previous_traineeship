package models.wall_post;

public class WallPostInfo {

    public WallPostInfo(int postId) {
        this.post_id = postId;
    }

    private final int post_id;

    public int getPostId() {
        return post_id;
    }
}
