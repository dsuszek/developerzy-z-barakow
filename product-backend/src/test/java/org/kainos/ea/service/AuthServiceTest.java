package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.db.UserDao;
import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.exception.FailedToRegisterUserException;
import org.kainos.ea.model.LoginRequest;
import org.kainos.ea.model.LoginResponse;
import org.kainos.ea.model.UserRegistrationRequest;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    private final AuthDao authDao = Mockito.mock(AuthDao.class);
    private final UserDao userDao = Mockito.mock(UserDao.class);
    private final UserRegistrationValidator userRegistrationValidator = Mockito.mock(UserRegistrationValidator.class);
    private AuthService authService = new AuthService(userDao, authDao, userRegistrationValidator);
    private String hashedAndSaltedPass = "U55AS5gfTUoluRuSy+nxRQ==:jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg="; // admin

    @Test
    public void login_WhenGivenCorrectShouldLogin_ShouldReturnToken() throws NoSuchAlgorithmException, FailedToLoginException, FailedToGenerateTokenException, SQLException {
        //given
        LoginRequest loginRequest = new LoginRequest("admin@kainos", "admin");
        //when
        when(authDao.getPasswordFromDatabase(loginRequest)).thenReturn(hashedAndSaltedPass);
        when(authDao.generateToken("admin@kainos")).thenReturn("1234567890");
        LoginResponse result = authService.login(loginRequest);
        //then
        assertEquals(result.getToken(), "1234567890");
    }

    @Test
    public void login_WhenLoginDataIsIncorrect_ShouldThrowFailedToLoginException() throws NoSuchAlgorithmException {
        //given
        LoginRequest loginRequest = new LoginRequest("admin@kainos", hashedAndSaltedPass);
        //when
        when(authDao.getPasswordFromDatabase(loginRequest)).thenReturn("admin1");
        //then
        assertThatExceptionOfType(FailedToLoginException.class)
                .isThrownBy(() -> authService.login(loginRequest));
    }

    @Test
    public void login_WhenSQLExceptionIsThrownInGenerateToken_ShouldThrowFailedToGenerateTokenException() throws NoSuchAlgorithmException, SQLException {
        //given
        LoginRequest loginRequest = new LoginRequest("admin@kainos", "admin");
        //when
        when(authDao.getPasswordFromDatabase(loginRequest)).thenReturn(hashedAndSaltedPass);
        when(authDao.generateToken("admin@kainos")).thenThrow(new SQLException());
        //then
        assertThatExceptionOfType(FailedToGenerateTokenException.class)
                .isThrownBy(() -> authService.login(loginRequest));
    }

    @Test
    public void register_WhenRegistrationDataIsIncorrect_ShouldThrowFailedToRegisterUserException() throws SQLException, FailedToRegisterUserException {
        // given
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest("joe@gmail.com", "3jou89234u9**(U(#HH@bnbnj", (short) 1);
        // when
        when(userDao.isEmailTaken(userRegistrationRequest.getEmail())).thenReturn(false);
        when(userDao.registerUser(userRegistrationRequest)).thenThrow(new FailedToRegisterUserException());
        // then
        assertThatExceptionOfType(FailedToRegisterUserException.class)
                .isThrownBy(() -> authService.registerUser(userRegistrationRequest));
    }

    @Test
    public void register_WhenPasswordDoesNotMeetTheCriteria_ShouldThrowFailedToRegisterUserException() throws SQLException, FailedToRegisterUserException {
        // given
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest("davidM@kainos.com", "pass123", (short) 1);
        // when
        when(userDao.isEmailTaken(userRegistrationRequest.getEmail())).thenReturn(false);
        when(userDao.registerUser(userRegistrationRequest)).thenThrow(new FailedToRegisterUserException());
        // then
        assertThatExceptionOfType(FailedToRegisterUserException.class)
                .isThrownBy(() -> authService.registerUser(userRegistrationRequest));
    }

    @Test
    public void register_WhenPasswordIsEmpty_ShouldThrowFailedToRegisterUserException() throws SQLException, FailedToRegisterUserException {
        // given
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest("davidM@kainos.com", "        ", (short) 1);
        // when
        when(userDao.isEmailTaken(userRegistrationRequest.getEmail())).thenReturn(false);
        when(userDao.registerUser(userRegistrationRequest)).thenThrow(new FailedToRegisterUserException());
        // then
        assertThatExceptionOfType(FailedToRegisterUserException.class)
                .isThrownBy(() -> authService.registerUser(userRegistrationRequest));
    }

    @Test
    public void register_WhenEmailAddressIsEmpty_ShouldThrowFailedToRegisterUserException() throws SQLException, FailedToRegisterUserException {
        // given
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest("          ", "frf23r242$R#FFE", (short) 1);
        // when
        when(userDao.isEmailTaken(userRegistrationRequest.getEmail())).thenReturn(false);
        when(userDao.registerUser(userRegistrationRequest)).thenThrow(new FailedToRegisterUserException());
        // then
        assertThatExceptionOfType(FailedToRegisterUserException.class)
                .isThrownBy(() -> authService.registerUser(userRegistrationRequest));
    }
}