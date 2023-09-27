package org.kainos.ea.db;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {

    @Test
    void encodePassword_WhenGetsPassword_ThenShouldReturnEncodedPasswordWithSalt() throws NoSuchAlgorithmException {
       String encodedSaltedPassword = PasswordEncoder.encodePassword("1234");
       String desaltedPassword= encodedSaltedPassword.split(":")[1];

       assertEquals("A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ="
               ,desaltedPassword);
    }

    @Test
    void checkPassword() throws NoSuchAlgorithmException {
        String passwordFromDb= PasswordEncoder.encodePassword("1234");

        boolean result = PasswordEncoder.checkPassword(PasswordEncoder.encodePassword("1234"),passwordFromDb);

        assertTrue(result);
    }

    @Test
    void hashPassword() throws NoSuchAlgorithmException {
        byte [] hash= PasswordEncoder.hashPassword("1234");

        String hashedString = Base64.getEncoder().encodeToString(hash);

        assertEquals("A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=",hashedString);
    }
}