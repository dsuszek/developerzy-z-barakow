package org.kainos.ea.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.model.LoginRequest;
import org.kainos.ea.service.AuthService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    AuthService authService = Mockito.mock(AuthService.class);
    AuthController authController = new AuthController();
    public String adminTokenThatNeverExpires = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiYWRtaW5Aa2Fpbm9zLmNvbSIsInJvbGVJZCI6MiwiaWF0IjoxNjk2OTQwODY0LCJleHAiOjM2MDE2OTY5NDA4NjR9.gfJ7B1kY0DVKcKTxW3u2cIcPZvQjFEjPrcZnMjwb9do";
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    public final String API_URL = System.getenv("API_URL");
    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
         List response = APP.client().target(API_URL + "/api/job-roles")
                .request()
                .header("Authorization", adminTokenThatNeverExpires)
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }
    @Test
    public void login_ShouldReturnResponseCode200_WhenLoginIsSuccessful() throws NoSuchAlgorithmException, FailedToLoginException, FailedToGenerateTokenException {
        //given
        LoginRequest loginRequest = new LoginRequest("123@kainos.com", "123456");
        //when
        when(authService.login(loginRequest)).thenReturn("12311212321");
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