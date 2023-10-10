package org.kainos.ea.controller;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.model.LoginRequest;
import org.kainos.ea.model.LoginResponse;
import org.kainos.ea.service.AuthService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    AuthService authService = Mockito.mock(AuthService.class);
    AuthController authController = new AuthController(authService);

    @Test
    public void login_ShouldReturnResponseCode200_WhenLoginIsSuccessful() throws NoSuchAlgorithmException, FailedToLoginException, FailedToGenerateTokenException {
        //given
        LoginRequest loginRequest = new LoginRequest("123@kainos.com", "123456");
        //when
        when(authService.login(loginRequest)).thenReturn(new LoginResponse("12311212321",false));
        Response response = authController.login(loginRequest);
        //then
        assertEquals(response.getStatus(), 200);
    }

    @Test
    public void login_ShouldReturnResponseCode400_WhenLoginHasFailed() throws NoSuchAlgorithmException, FailedToLoginException, FailedToGenerateTokenException {
        //given
        LoginRequest loginRequest = new LoginRequest("123@kainos.com", "123456");
        //when
        when(authService.login(loginRequest)).thenThrow(new FailedToLoginException());
        Response response = authController.login(loginRequest);
        //then
        assertEquals(response.getStatus(), 400);
    }

    @Test
    public void login_ShouldReturnResponseCode500_WhenFailedToGenerateToken() throws NoSuchAlgorithmException, FailedToLoginException, FailedToGenerateTokenException {
        //given
        LoginRequest loginRequest = new LoginRequest("123@kainos.com", "123456");
        //when
        when(authService.login(loginRequest)).thenThrow(new FailedToGenerateTokenException());
        Response response = authController.login(loginRequest);
        //then
        assertEquals(response.getStatus(), 500);
    }


}