package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.model.LoginRequest;
import org.kainos.ea.service.AuthService;
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
    String hashedAndSaltedPass = "U55AS5gfTUoluRuSy+nxRQ==:jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=";// admin
    @Test
    public void login_WhenGivenCorrectShouldLogin_ShouldReturnToken() throws NoSuchAlgorithmException, FailedToLoginException, FailedToGenerateTokenException, SQLException {
        //given
        LoginRequest loginRequest = new LoginRequest("admin@kainos","admin");
        //when
        when(authDao.getPasswordFromDatabase(loginRequest)).thenReturn(hashedAndSaltedPass);
        when(authDao.generateToken("admin@kainos")).thenReturn("1234567890");
        String result = authService.login(loginRequest);
        //then
        assertEquals(result,"1234567890");
    }

    @Test
    public void login_WhenLoginDataIsIncorrect_ShouldThrowFailedToLoginException() throws NoSuchAlgorithmException{
        //given
        LoginRequest loginRequest = new LoginRequest("admin@kainos",hashedAndSaltedPass);
        //when
        when(authDao.getPasswordFromDatabase(loginRequest)).thenReturn("admin1");
        //then
        assertThatExceptionOfType(FailedToLoginException.class)
                .isThrownBy(() -> authService.login(loginRequest));
    }

    @Test
    public void login_WhenSQLExceptionIsThrownInGenerateToken_ShouldThrowFailedToGenerateTokenException() throws NoSuchAlgorithmException, SQLException {
        //given
        LoginRequest loginRequest = new LoginRequest("admin@kainos","admin");
        //when
        when(authDao.getPasswordFromDatabase(loginRequest)).thenReturn(hashedAndSaltedPass);
        when(authDao.generateToken("admin@kainos")).thenThrow(new SQLException());
        //then
        assertThatExceptionOfType(FailedToGenerateTokenException.class)
                .isThrownBy(() -> authService.login(loginRequest));
    }

}