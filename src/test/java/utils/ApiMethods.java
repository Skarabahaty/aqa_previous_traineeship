package utils;

public enum ApiMethods {

    WALL_POST("wall.post"),
    WALL_EDIT("wall.edit"),
    WALL_CREATE_COMMENT("wall.createComment"),
    WALL_GET_LIKES("wall.getLikes"),
    WALL_DELETE("wall.delete"),
    GET_WALL_UPLOAD_SERVER("photos.getWallUploadServer"),
    PHOTOS_SAVE_WALL_PHOTO("photos.saveWallPhoto");

    private final String method;

    ApiMethods(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }


}

