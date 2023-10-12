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
    public String userTokenThatNeverExpires = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidXNlckBrYWlub3MuY29tIiwicm9sZUlkIjoxLCJpYXQiOjE2OTY5NDA3MDIsImV4cCI6MzYwMTY5Njk0MDcwMn0.3htxQT2vE7hpaajYxUCfbAGHOtuaKiExD6yredmfOl0";
    public String adminTokenThatNeverExpires = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiYWRtaW5Aa2Fpbm9zLmNvbSIsInJvbGVJZCI6MiwiaWF0IjoxNjk2OTQwODY0LCJleHAiOjM2MDE2OTY5NDA4NjR9.gfJ7B1kY0DVKcKTxW3u2cIcPZvQjFEjPrcZnMjwb9do";

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
        List<JobRole> response = APP.client().target(API_URL + "/api/job-roles")
                .request()
                .header("Authorization", adminTokenThatNeverExpires)
                .get(List.class);
        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getJobRole_shouldReturnJobRole() {
        Response response = APP.client().target(API_URL + "/api/job-roles/1")
                .request()
                .header("Authorization", adminTokenThatNeverExpires)
                .get();
        Assertions.assertEquals(200,response.getStatus());
    }

    @Test
    void getJobRole_shouldReturnBadRequest_whenJobRoleDoesNotExist() {
        Response response = APP.client().target(API_URL + "/api/job-roles/10000")
                .request()
                .header("Authorization", adminTokenThatNeverExpires)
                .get();
        Assertions.assertEquals(400,response.getStatus());
    }
}
