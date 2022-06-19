package utils;

import com.mashape.unirest.http.JsonNode;
import data.ConfigData;
import data.TestData;
import models.ResponseObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;

public class UnirestObjectsUtil {

    public static LinkedList<ResponseObject> getResponseObjectsFromHTTPResponse(JsonNode responseBody) {
        JSONArray array = responseBody.getArray();
        LinkedList<ResponseObject> responseObjects = new LinkedList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            ResponseObject responseObject = new ResponseObject(jsonObject);
            responseObjects.add(responseObject);
        }
        return responseObjects;
    }

    public static ResponseObject getResponseObjectFromTestData(TestData testData, String key) {

        JSONObject testObject = (JSONObject) testData.getTestObject(key);
        return new ResponseObject(testObject);
    }

    public static boolean isResponseObjectAbleToGenerate(JsonNode responseBody) {
        JSONArray array = responseBody.getArray();
        JSONObject jsonObject = (JSONObject) array.get(0);
        try {
            new ResponseObject(jsonObject);
            return true;
        } catch (JSONException e) {
            return false;
        }
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

    public static ResponseObject getRespponseObjectFromObjectForPost(HashMap<String, Object> objectForPost, TestData testData) {
        Object title = objectForPost.get("title");
        Object body = objectForPost.get("body");
        Object userId = objectForPost.get("userId");
        int case4Id = testData.getInt("case_4_id");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("body", body);
        jsonObject.put("userId", userId);
        jsonObject.put("id", case4Id);

        return new ResponseObject(jsonObject);
    }
}
