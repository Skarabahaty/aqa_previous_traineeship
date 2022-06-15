package utils;

import java.util.Map;

public class CredentialsGenerator {

    public static String generatePassword(String email) {
        StringBuilder password = new StringBuilder();

        String passwordBase = Randomizer.getRandomStringLowerCase(8);
        password.append(passwordBase);

        int length = email.length();
        int charPosition = Randomizer.getRandomIntInRange(0, length - 1);
        char charFromEmail = email.charAt(charPosition);
        password.append(charFromEmail);

        int randomInt = Randomizer.getRandomInt();
        password.append(randomInt);

        char randomCharUpperCase = Randomizer.getRandomCharUpperCase();
        password.append(randomCharUpperCase);

        String randomCyrillicSymbol = Randomizer.getRandomCyrillicSymbol();
        password.append(randomCyrillicSymbol);

        return String.valueOf(password);
    }

    public static Map<String, String> generateEmail(int emailLetters, int domainLetters) {
        String email = Randomizer.getRandomStringLowerCase(emailLetters);
        String domain = Randomizer.getRandomStringLowerCase(domainLetters);
        String dotSomething = String.valueOf(Randomizer.getRandomIntInRange(1, 9));

        return Map.of("email", email,
                "domain", domain,
                "dot", dotSomething);
    }
}
