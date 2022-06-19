package utils;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {


    private Randomizer() {
    }

    private static final int SMALL_LETTER_A_ASCII_CODE = 97;
    private static final int SMALL_LETTER_Z_ASCII_CODE = 122;

    private static final ThreadLocalRandom localRandom = ThreadLocalRandom.current();

    public static String getRandomStringLowerCase(int length) {

        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = SMALL_LETTER_A_ASCII_CODE +
                    (int) (localRandom.nextFloat() * (SMALL_LETTER_Z_ASCII_CODE - SMALL_LETTER_A_ASCII_CODE + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }


}
