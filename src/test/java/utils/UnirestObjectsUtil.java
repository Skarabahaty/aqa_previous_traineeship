package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import data.ConfigData;
import data.TestData;
import models.Post;
import models.user.User;

import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("rawtypes")
public class UnirestObjectsUtil {

    public static Post getPostFromTestData(TestData testData, String key) {

        JsonElement object = testData.getJsonObject(key);
        return new Post(object);
    }

    public static boolean isPostEmpty(Post post) {
        return post.getId() == 0
                && post.getUserID() == 0
                && post.getBody() == null
                && post.getTitle() == null;
    }

    public static HashMap getObjectForPost(ConfigData configData, TestData testData) {
        JsonElement case4Post = testData.getJsonObject("case_4_post");
        Gson gson = new Gson();
        var map = gson.fromJson(case4Post, HashMap.class);

        int stringLength = configData.getInt("random_string_length");

        String title = Randomizer.getRandomStringLowerCase(stringLength);
        String body = Randomizer.getRandomStringLowerCase(stringLength);

        map.put("title", title);
        map.put("body", body);

        return map;
    }


    public static User getUserById(User[] users, int id) {
        return Arrays.stream(users).
                filter(user -> user.getId() == id).
                findFirst().
                orElse(null);
    }
}
