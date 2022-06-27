package models.likes;

public class LikesContainer {

    private final int count;
    private final LikedUser[] users;

    public LikesContainer(int count, LikedUser[] users) {
        this.count = count;
        this.users = users;
    }

    public int getCount() {
        return count;
    }

    public LikedUser[] getUsers() {
        return users;
    }
}
