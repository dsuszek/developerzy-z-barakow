package org.kainos.ea.db;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {

    @Test
    public void encodePassword_WhenGetsPassword_ThenShouldReturnEncodedPasswordWithTheSameLength() throws NoSuchAlgorithmException {
        String encodedSaltedPassword = PasswordEncoder.encodePassword("1234");

        assertEquals(69, encodedSaltedPassword.length());
    }

    @Test
    public void checkPassword_WhenPasswordGivenIsTheSameAsPasswordFromDB_ShouldReturnTrue() throws NoSuchAlgorithmException {
        String passwordFromDb = PasswordEncoder.encodePassword("1234");

        boolean result = PasswordEncoder.checkPassword(PasswordEncoder.encodePassword("1234"), passwordFromDb);

        assertTrue(result);
    }

    @Test
    public void checkPassword_WhenPasswordGivenIsNotTheSameAsPasswordFromDB_ShouldReturnFalse() throws NoSuchAlgorithmException {
        String passwordFromDb = PasswordEncoder.encodePassword("1234");

        boolean result = PasswordEncoder.checkPassword(PasswordEncoder.encodePassword("12345"), passwordFromDb);

        assertFalse(result);
    }

    @Test
    public void checkPassword_WhenPasswordGivenIsNull_ShouldReturnFalse() throws NoSuchAlgorithmException {

        boolean result = PasswordEncoder.checkPassword(null, "passwordFromDb");

        assertFalse(result);
    }

    @Test
    public void checkPassword_WhenPasswordGivenIsNull_ShouldReturnFalseIFOneOFThePasswordsDoesNotContainColon() throws NoSuchAlgorithmException {

        boolean result = PasswordEncoder.checkPassword("21312313", "123:12313");

        assertFalse(result);
    }

    @Test
    public void hashPassword_WhenGivenString_ShouldReturnHashedPassword() throws NoSuchAlgorithmException {
        byte[] hash = PasswordEncoder.hashPassword("1234");

        String hashedString = Base64.getEncoder().encodeToString(hash);

        assertEquals("A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=", hashedString);
    }
}