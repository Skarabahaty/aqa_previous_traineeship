package models.comments_response;

public class CommentInfo {

    public CommentInfo(Integer postId, Object[] parents_stack) {
        this.comment_id = postId;
        this.parents_stack = parents_stack;
    }

    private final int comment_id;
    private final Object[] parents_stack;

    public int getCommentId() {
        return comment_id;
    }

    public Object[] getParents_stack() {
        return parents_stack;
    }
}
