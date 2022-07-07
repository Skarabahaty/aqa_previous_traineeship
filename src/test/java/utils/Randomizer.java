package utils;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    private Randomizer() {}

    private static final ThreadLocalRandom localRandom = ThreadLocalRandom.current();

    public static int getRandomIntFromZeroToNine() {
        return localRandom.nextInt(10);
    }
}