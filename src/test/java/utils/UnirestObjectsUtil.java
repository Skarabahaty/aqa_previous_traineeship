package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import data.ConfigData;
import data.TestData;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONException;
import models.Response;
import models.user.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class UnirestObjectsUtil {

    public static <T> T getObjectFromTestData(TestData testData, String key, Class<T> clazz) {
        try {
            JsonElement object = testData.getJsonObject(key);
            return new Gson().fromJson(object, clazz);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new UnirestException("Problem with getting object from test data");
        }
    }

    public static JsonObject getObjectForPost(ConfigData configData, TestData testData) {
        JsonObject post = testData.getJsonObject("case_4_post");

        int stringLength = configData.getInt("random_string_length");
        String title = Randomizer.getRandomStringLowerCase(stringLength);
        String body = Randomizer.getRandomStringLowerCase(stringLength);

        post.addProperty("title", title);
        post.addProperty("body", body);
        return post;
    }

    public static User getUserById(User[] users, int id) {
        return Arrays.stream(users).
                filter(user -> user.getId() == id).
                findFirst().
                orElse(null);
    }

    public static <T> Response getAsObject(String route, Class<T> clazz) throws UnirestException {
        try {
            HttpResponse<T> httpResponse = Unirest.get(route)
                    .asObject(clazz);
            return new Response(httpResponse);

        } catch (JSONException e) {
            e.printStackTrace();
            throw new UnirestException("Problem with response");
        }
    }

    public static <T, M extends JsonObject> Response post(String route, M body, Class<T> clazz) throws UnirestException {
        try {
            HashMap<String, Object> map = new HashMap<>();
            Set<String> strings = body.keySet();
            for (String string : strings) {
                map.put(string, body.get(string));
            }

            HttpResponse<T> httpResponse = Unirest.post(route)
                    .fields(map)
                    .asObject(clazz);
            return new Response(httpResponse);

        } catch (JSONException e) {
            e.printStackTrace();
            throw new UnirestException("Problem with response");
        }
    }
}
