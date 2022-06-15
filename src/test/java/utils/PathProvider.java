package utils;

public class PathProvider {

    public static String getAvatarPath() {
        String userDir = System.getProperty("user.dir");
        String testResourceFolder = "src\\test\\resources";
        String fileName = "welcome-cat.png";
        return String.format("%s\\%s\\%s", userDir, testResourceFolder, fileName);
    }
}
