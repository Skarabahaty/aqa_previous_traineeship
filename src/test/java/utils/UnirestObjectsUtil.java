package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import data.ConfigData;
import data.TestData;
import kong.unirest.UnirestException;
import models.user.User;

import java.util.Arrays;

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
}
