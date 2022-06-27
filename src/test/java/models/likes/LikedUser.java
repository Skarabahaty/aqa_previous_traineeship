package models.likes;

public class LikedUser {

    private final int uid;
    private final int copied;

    public LikedUser(int uid, int copied) {
        this.uid = uid;
        this.copied = copied;
    }

    public int getUid() {
        return uid;
    }

    public int getCopied() {
        return copied;
    }
}
