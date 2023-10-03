package org.kainos.ea.db;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncoder {
    private static final int SALT_LENGTH = 16;

    public static String encodePassword(String password) throws NoSuchAlgorithmException {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(password);
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedPassword);
    }

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    public static byte[] hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    public static boolean checkPassword(String inputPassword, String storedPassword) throws NoSuchAlgorithmException {
        if(inputPassword == null){
            return false;
        }
        String[] partsInput = inputPassword.split(":");
        String[] partsStored = storedPassword.split(":");
        if (partsInput.length != 2 || partsStored.length != 2) {
            return false;
        }
        String desaltedInput = partsInput[1];
        String desaltedStored = partsStored[1];

        return desaltedInput.equals(desaltedStored);
    }
}