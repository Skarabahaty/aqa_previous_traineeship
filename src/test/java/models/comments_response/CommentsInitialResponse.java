package models.comments_response;

public class CommentsInitialResponse {

    private final CommentInfo response;

    public CommentsInitialResponse(CommentInfo response) {
        this.response = response;
    }

    public CommentInfo getCommentInfo() {
        return response;
    }
}
