package models.photo_upload;

public class PhotoUploadServerResponseAfterUpload {

    private final int server;
    private final String hash;
    private final String photo;

    public PhotoUploadServerResponseAfterUpload(int server, String hash, String photo) {
        this.server = server;
        this.hash = hash;
        this.photo = photo;
    }

    public int getServer() {
        return server;
    }

    public String getHash() {
        return hash;
    }

    public String getPhoto() {
        return photo;
    }
}
