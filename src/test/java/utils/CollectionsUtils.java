package utils;

import models.Post;

import java.util.Arrays;
import java.util.Comparator;

public class CollectionsUtils {

    public static boolean isArraySortedAscending(Post[] list) {
        return Arrays.equals(
                Arrays.stream(list)
                        .sorted(Comparator.comparingInt(Post::getId))
                        .toArray(),
                list);
    }
}
