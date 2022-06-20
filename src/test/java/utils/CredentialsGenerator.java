package utils;

public class CredentialsGenerator {

    public static String generatePassword(String email, int lettersAmount) {
        StringBuilder password = new StringBuilder();

        String passwordBase = Randomizer.getRandomStringLowerCase(lettersAmount);
        password.append(passwordBase);

        int charPosition = Randomizer.getRandomIntInRange(0, email.length() - 1);
        char charFromEmail = email.charAt(charPosition);
        password.append(charFromEmail);

        int randomInt = Randomizer.getRandomIntFromZeroToNine();
        password.append(randomInt);

        char randomCharUpperCase = Randomizer.getRandomCharUpperCase();
        password.append(randomCharUpperCase);

        return String.valueOf(password);
    }

    public static String generateEmail(int emailLetters) {
        return Randomizer.getRandomStringLowerCase(emailLetters);
    }

    public static String generateDomain(int domainLetters) {
        return Randomizer.getRandomStringLowerCase(domainLetters);
    }
}
