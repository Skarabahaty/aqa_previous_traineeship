package utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class JsonReader {

    private JsonReader() {
    }

    public static HashMap<String, Object> getDataFromFile(String sourceFileName) {
        HashMap<String, Object> returnHashMap = new HashMap<>();
        String userDir = System.getProperty("user.dir");
        String fileFullPath = String.format("%s\\src\\test\\resources\\%s", userDir, sourceFileName);
        JSONObject jsonObject = new JSONObject();
        try (
                BufferedReader bufferedReader = new BufferedReader(
                        new FileReader(fileFullPath)
                )
        ) {
            JSONTokener jsonTokener = new JSONTokener(bufferedReader);
            while (jsonTokener.more()) {
                jsonObject = (JSONObject) jsonTokener.nextValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<String> set = jsonObject.keySet();

        for (String key : set) {
            returnHashMap.put(key, jsonObject.get(key));
        }
        return returnHashMap;
    }
}
