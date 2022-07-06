package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class JsonReader {

    private JsonReader() {
    }

    public static JsonObject getDataFromFile(String sourceFileName) {

        String userDir = System.getProperty("user.dir");
        String resourcesFolder = "src\\test\\resources";
        Path path = Paths.get(userDir, resourcesFolder, sourceFileName);
        JsonElement jsonElement = null;
        try (
                BufferedReader bufferedReader = new BufferedReader(
                        new FileReader(path.toFile())
                )
        ) {
            jsonElement = JsonParser.parseReader(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(jsonElement).getAsJsonObject();
    }
}
