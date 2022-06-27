package utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathProvider {

    public static Path getPicturePath(String pictureName, String pictureStorageFolder) {

        return Paths.get(System.getProperty("user.dir"), pictureStorageFolder, pictureName);
    }
}
