package utils;

import com.mashape.unirest.http.JsonNode;
import data.ConfigData;
import data.TestData;
import models.Post;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;

public class UnirestObjectsUtil {

    public static LinkedList<Post> getResponseObjectsFromHTTPResponse(JsonNode responseBody) {
        JSONArray array = responseBody.getArray();
        LinkedList<Post> posts = new LinkedList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            Post post = new Post(jsonObject);
            posts.add(post);
        }
        return posts;
    }

    public static Post getResponseObjectFromTestData(TestData testData, String key) {

        JSONObject testObject = (JSONObject) testData.getTestObject(key);
        return new Post(testObject);
    }

    public static boolean isResponseObjectEmpty(JsonNode responseBody) {
        JSONArray array = responseBody.getArray();
        JSONObject jsonObject = (JSONObject) array.get(0);
        return jsonObject.length() == 0;
    }

    public static HashMap<String, Object> getObjectForPost(ConfigData configData, TestData testData) {
        int userId = testData.getInt("case_4_user_id");

        int stringLength = configData.getInt("random_string_length");
        String title = Randomizer.getRandomStringLowerCase(stringLength);
        String body = Randomizer.getRandomStringLowerCase(stringLength);

        HashMap<String, Object> map = new HashMap<>();

        map.put("title", title);
        map.put("body", body);
        map.put("userId", userId);

        return map;
    }

    public static Post getResponseObjectFromObjectForPost(HashMap<String, Object> objectForPost, TestData testData) {
        Object title = objectForPost.get("title");
        Object body = objectForPost.get("body");
        Object userId = objectForPost.get("userId");
        int case4Id = testData.getInt("case_4_id");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("body", body);
        jsonObject.put("userId", userId);
        jsonObject.put("id", case4Id);

        return new Post(jsonObject);
    }
}
