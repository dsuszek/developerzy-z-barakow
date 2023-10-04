package integration;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.model.LoginRequest;
import org.mockito.junit.jupiter.MockitoExtension;
import io.dropwizard.testing.junit5.DropwizardAppExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    public final String API_URL = System.getenv("API_URL");
    @Test
    public void login_WhenLoginIsNotFound_ShouldReturnCode400() {
        //given
        LoginRequest loginRequest = new LoginRequest("IDontexist@adadda.com","32113123131131");
        //when
        Response response = APP.client().target(API_URL+ "/api/auth/login")
                .request()
                .post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON_TYPE));
        //then
        Assertions.assertEquals(400,response.getStatus());
    }

    @Test
    public void login_WhenLoginIsNotFound_ShouldReturnCode200() {
        //given
        LoginRequest loginRequest = new LoginRequest("admin@kainos.com","admin");
        //when
        Response response = APP.client().target(API_URL+ "/api/auth/login")
                .request()
                .post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON_TYPE));
        //then
        Assertions.assertEquals(200,response.getStatus());
    }
}
