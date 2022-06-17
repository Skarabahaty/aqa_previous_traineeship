package utils;

import models.ResponseObject;

import java.util.Comparator;
import java.util.LinkedList;

public class CollectionsUtils {

    public static boolean isListSorted(LinkedList<ResponseObject> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(ResponseObject::getId))
                .toList()
                .equals(list);
    }
}
