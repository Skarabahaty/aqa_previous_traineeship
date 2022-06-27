package models.photo_upload.upload_url;

public class PhotoUploadLinkServerInfo {

    private final int user_id;
    private final String upload_url;
    private final int album_id;

    public PhotoUploadLinkServerInfo(int user_id, String upload_url, int album_id) {
        this.user_id = user_id;
        this.upload_url = upload_url;
        this.album_id = album_id;
    }

    public int getUserId() {
        return user_id;
    }

    public String getUploadUrl() {
        return upload_url;
    }

    public int getAlbumId() {
        return album_id;
    }
}
