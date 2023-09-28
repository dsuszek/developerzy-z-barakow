package org.kainos.ea.service;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.model.LoginRequest;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    AuthDao authDao = Mockito.mock(AuthDao.class);
    AuthService authService = new AuthService(authDao);
    @Test
    public void login_WhenGivenCorrectShouldLogin_ShouldReturnToken() throws NoSuchAlgorithmException, FailedToLoginException, FailedToGenerateTokenException, SQLException {
        //given
        LoginRequest loginRequest = new LoginRequest("admin@kainos","admin");
        //when
        when(authDao.validLoginRequest(loginRequest)).thenReturn(true);
        when(authDao.generateToken("admin@kainos")).thenReturn("1234567890");
        String result = authService.login(loginRequest);
        //then
        assertEquals(result,"1234567890");
    }

    @Test
    public void login_WhenLoginDataIsIncorrect_ShouldThrowFailedToLoginException() throws NoSuchAlgorithmException, FailedToLoginException, FailedToGenerateTokenException, SQLException {
        //given
        LoginRequest loginRequest = new LoginRequest("admin@kainos","admin");
        //when
        when(authDao.validLoginRequest(loginRequest)).thenReturn(false);
        //then
        assertThatExceptionOfType(FailedToLoginException.class)
                .isThrownBy(() -> authService.login(loginRequest));
    }

    @Test
    public void login_WhenSQLExceptionIsThrownInGenerateToken_ShouldThrowFailedToGenerateTokenException() throws NoSuchAlgorithmException, FailedToLoginException, FailedToGenerateTokenException, SQLException {
        //given
        LoginRequest loginRequest = new LoginRequest("admin@kainos","admin");
        //when
        when(authDao.validLoginRequest(loginRequest)).thenReturn(true);
        when(authDao.generateToken("admin@kainos")).thenThrow(new SQLException());
        //then
        assertThatExceptionOfType(FailedToGenerateTokenException.class)
                .isThrownBy(() -> authService.login(loginRequest));
    }

}