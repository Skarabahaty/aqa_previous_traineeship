package models.photo_upload.save_photo;

public class SavedPhotoInitialResponse {

    private final SavedPhoto[] response;

    public SavedPhotoInitialResponse(SavedPhoto[] response) {
        this.response = response;
    }

    public SavedPhoto[] getUploadedAndSavedPhotos() {
        return response;
    }
}
