package integration;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleResponse;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRolesIntegrationTest {
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );
    public final String API_URL = System.getenv("API_URL");

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
        List<JobRole> response = APP.client().target(API_URL + "/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getJobRole_shouldReturnJobRole() {
        Response response = APP.client().target(API_URL + "/api/job-roles/40")
                .request()
                .get();
        Assertions.assertEquals(200,response.getStatus());
    }

    @Test
    void getJobRole_shouldReturnBadRequest_whenJobRoleDoesNotExist() {
        Response response = APP.client().target(API_URL + "/api/job-roles/1")
                .request()
                .get();
        Assertions.assertEquals(400,response.getStatus());
    }

}
