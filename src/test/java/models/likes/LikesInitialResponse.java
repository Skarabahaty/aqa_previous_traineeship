package models.likes;

public class LikesInitialResponse {

    private final LikesContainer response;

    public LikesInitialResponse(LikesContainer response) {
        this.response = response;
    }

    public LikesContainer getLikesContainer() {
        return response;
    }
}
