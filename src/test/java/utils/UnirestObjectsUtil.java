package utils;

import data.ConfigData;
import data.TestData;
import models.Post;
import models.user.User;

import java.util.Arrays;
import java.util.HashMap;

public class UnirestObjectsUtil {

    public static Post getPostFromTestData(TestData testData, String key) {

        Object object = testData.getObject(key);
        return new Post(object);
    }

    public static boolean isPostEmpty(Post post) {
        return post.getId() == 0
                && post.getUserID() == 0
                && post.getBody() == null
                && post.getTitle() == null;
    }

    public static HashMap<String, Object> getObjectForPost(ConfigData configData, TestData testData) {
        HashMap<String, Object> map = (HashMap<String, Object>) testData.getObject("case_4_post");

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
