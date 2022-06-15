package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Randomizer {

    private Randomizer() {
    }

    private static final ThreadLocalRandom localRandom = ThreadLocalRandom.current();

    public static String getRandomStringLowerCase(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (localRandom.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static String getRandomCyrillicSymbol() {
        String cyrillicCharacters = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        int randomCharIndex = localRandom.nextInt(cyrillicCharacters.length());
        return String.valueOf(cyrillicCharacters.charAt(randomCharIndex));
    }

    public static Set<Integer> getThreeRandomIndexes() {
        HashSet<Integer> integers = new HashSet<>();
        while (integers.size() < 3) {
            int index = localRandom.nextInt(21);
            if (index != 17 && index != 20) {
                integers.add(index);
            }
        }
        return integers;
    }

    public static char getRandomCharUpperCase() {
        int leftLimit = 65; // letter 'A'
        int rightLimit = 90; // letter 'Z'
        int randomLimitedInt = leftLimit + (int) (localRandom.nextFloat() * (rightLimit - leftLimit + 1));
        return (char) randomLimitedInt;
    }

    public static int getRandomIntInRange(int min, int max) {
        return localRandom.nextInt(min, max + 1);
    }

    public static int getRandomInt() {
        return localRandom.nextInt(10);
    }
}
