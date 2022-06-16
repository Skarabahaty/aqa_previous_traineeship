package utils;

import constants.Constants;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {


    private Randomizer() {
    }

    private static final ThreadLocalRandom localRandom = ThreadLocalRandom.current();

    public static String getRandomStringLowerCase(int length) {
        int smallLetterAASCIICode = Constants.SMALL_LETTER_A_ASCII_CODE;
        int smallLetterZASCIICode = Constants.SMALL_LETTER_Z_ASCII_CODE;

        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = smallLetterAASCIICode +
                    (int) (localRandom.nextFloat() * (smallLetterZASCIICode - smallLetterAASCIICode + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static Set<Integer> getRandomIndexes(int interestsNumber, int unselectAllIndex, int selectAllIndex, int interestsOverallNumber) {
        HashSet<Integer> integers = new HashSet<>();
        while (integers.size() < interestsNumber) {
            int index = localRandom.nextInt(interestsOverallNumber);
            if (index != selectAllIndex && index != unselectAllIndex) {
                integers.add(index);
            }
        }
        return integers;
    }

    public static char getRandomCharUpperCase() {
        int bigLetterAASCIICode = Constants.BIG_LETTER_A_ASCII_CODE;
        int bigLetterZASCIICode = Constants.BIG_LETTER_Z_ASCII_CODE;
        int randomLimitedInt = bigLetterAASCIICode +
                (int) (localRandom.nextFloat() * (bigLetterZASCIICode - bigLetterAASCIICode + 1));
        return (char) randomLimitedInt;
    }

    public static int getRandomIntInRange(int min, int max) {
        return localRandom.nextInt(min, max + 1);
    }

    public static int getRandomIntInRange(int max) {
        return localRandom.nextInt(0, max + 1);
    }

    public static int getRandomIntFromZeroToNine() {
        return localRandom.nextInt(10);
    }


}
