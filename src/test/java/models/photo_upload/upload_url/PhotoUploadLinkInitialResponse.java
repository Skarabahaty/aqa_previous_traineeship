package models.photo_upload.upload_url;

public class PhotoUploadLinkInitialResponse {

    private final PhotoUploadLinkServerInfo response;

    public PhotoUploadLinkInitialResponse(PhotoUploadLinkServerInfo response) {
        this.response = response;
    }

    public PhotoUploadLinkServerInfo getUploadLinkServerInfo() {
        return response;
    }
}
