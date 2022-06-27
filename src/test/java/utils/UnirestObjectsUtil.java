package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import data.ConfigData;
import data.TestData;
import models.Post;
import models.user.User;

import java.util.Arrays;

public class UnirestObjectsUtil {

    public static Post getPostFromTestData(TestData testData, String key) {

        JsonElement object = testData.getJsonObject(key);
        return new Gson().fromJson(object, Post.class);
    }

    public static boolean isPostEmpty(Post post) {
        return post.getId() == 0
                && post.getUserID() == 0
                && post.getBody() == null
                && post.getTitle() == null;
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
