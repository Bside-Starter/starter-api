package com.bside.starterapi.api.application.space;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomCodeGenerator {
    public static String get() {
        String randomAlphabet = getRandomAlphabet();
        String randomNumeric = getRandomNumeric();
        String randomCode = randomAlphabet + randomNumeric;
        List<String> randomCodeStrings = Arrays.asList(randomCode.split(""));
        Collections.shuffle(randomCodeStrings);
        return String.join("", randomCodeStrings);
    }

    private static String getRandomAlphabet() {
        int leftLimit = 65; // letter 'A'
        int rightLimit = 90; // letter 'Z'

        int targetStringLength = 8;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static String getRandomNumeric() {
        int leftLimit = 48; // letter '0'
        int rightLimit = 57; // letter '9'

        int targetStringLength = 2;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
