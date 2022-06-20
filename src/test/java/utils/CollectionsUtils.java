package utils;

import models.Post;

import java.util.Comparator;
import java.util.LinkedList;

public class CollectionsUtils {

    public static boolean isListSortedAscending(LinkedList<Post> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(Post::getId))
                .toList()
                .equals(list);
    }
}
