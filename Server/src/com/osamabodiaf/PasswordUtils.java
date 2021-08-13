package com.osamabodiaf;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static String encryptPassword(String password) {
        String salt = getSalt(30);

        byte[] hashedPassword = hash(password.toCharArray(), salt.getBytes());

        String encodedHash = Base64.getEncoder().encodeToString(hashedPassword);

        return encodedHash + "$" + salt;
    }

    private static String encryptPassword(String password, String salt) {
        byte[] hashedPassword = hash(password.toCharArray(), salt.getBytes());

        String encodedHash = Base64.getEncoder().encodeToString(hashedPassword);

        return encodedHash + "$" + salt;
    }

    public static boolean verifyPassword(String plainPassword, String encryptedPassword) {
        String salt = encryptedPassword.split("\\$")[1];
        // Generate New secure password with the same salt
        String newEncryptedPassword = encryptPassword(plainPassword, salt);

        // Return if two passwords are equal
        return newEncryptedPassword.equalsIgnoreCase(encryptedPassword);
    }
}
