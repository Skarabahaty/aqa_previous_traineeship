package utils;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;

public class JsonReader {

    private JsonReader() {
    }

    public static HashMap<String, Object> getDataFromFile(String sourceFileName) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

        String userDir = System.getProperty("user.dir");
        String resourcesFolder = "src\\test\\resources";
        Path path = Paths.get(userDir, resourcesFolder, sourceFileName);

        JSONObject jsonObject = new JSONObject();
        try (
                BufferedReader bufferedReader = new BufferedReader(
                        new FileReader(path.toFile())
                )
        ) {
            jsonObject = (JSONObject) JSONValue.parse(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<String> set = jsonObject.keySet();

        for (String key : set) {
            Object value = jsonObject.get(key);
            returnHashMap.put(key, value);
        }
        return returnHashMap;
    }
}
