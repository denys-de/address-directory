package de.dpa.addressdirectory.domain.utils;

import java.security.SecureRandom;
import java.util.HexFormat;

/**
 * @author Denys Babich
 * 17.09.2024
 */
public class Utils {

    private Utils() {
    }

    public static String generateRandomString(int bytes) {
        return HexFormat.of().formatHex(generateRandomBytes(bytes));
    }

    public static byte[] generateRandomBytes(int bytes) {
        final var randomBytes = new byte[bytes];
        SecureRandomHolder.secureRandom.nextBytes(randomBytes);
        return randomBytes;
    }

    // In a holder class to defer initialization until needed
    private static class SecureRandomHolder {

        static final SecureRandom secureRandom = new SecureRandom();

    }

}
