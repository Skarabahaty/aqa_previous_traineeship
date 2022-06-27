package models.photo_upload.save_photo;

public class SavedPhoto {

    private final int album_id;
    private final int date;
    private final int id;
    private final int owner_id;
    private final String access_key;
    private final SavedPhotoSizes[] sizes;
    private final String text;
    private final boolean has_tags;

    public SavedPhoto(int album_id,
                      int date,
                      int id,
                      int owner_id,
                      String access_key,
                      SavedPhotoSizes[] sizes,
                      String text,
                      boolean has_tags) {
        this.album_id = album_id;
        this.date = date;
        this.id = id;
        this.owner_id = owner_id;
        this.access_key = access_key;
        this.sizes = sizes;
        this.text = text;
        this.has_tags = has_tags;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public int getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public String getAccess_key() {
        return access_key;
    }

    public SavedPhotoSizes[] getSizes() {
        return sizes;
    }

    public String getText() {
        return text;
    }

    public boolean isHas_tags() {
        return has_tags;
    }

    public String returnOriginalPhotoUrl() {
        int lastIndex = sizes.length - 1;
        return sizes[lastIndex].getUrl();
    }
}
