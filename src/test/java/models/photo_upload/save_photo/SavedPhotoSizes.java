package models.photo_upload.save_photo;

public class SavedPhotoSizes {

    private final int height;
    private final int width;
    private final String type;
    private final String url;


    public SavedPhotoSizes(int height,
                           int width,
                           String type,
                           String url) {
        this.height = height;
        this.width = width;
        this.type = type;
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
