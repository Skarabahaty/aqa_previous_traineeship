package models.wall_post;

public class WallPostInitialResponse {

    private final WallPostInfo response;

    public WallPostInitialResponse(WallPostInfo response) {
        this.response = response;
    }

    public WallPostInfo getWallPostInfo() {
        return response;
    }
}
