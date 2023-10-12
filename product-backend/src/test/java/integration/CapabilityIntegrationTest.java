package integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.model.Capability;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CapabilityIntegrationTest {
    public String adminTokenThatNeverExpires = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiYWRtaW5Aa2Fpbm9zLmNvbSIsInJvbGVJZCI6MiwiaWF0IjoxNjk2OTQwODY0LCJleHAiOjM2MDE2OTY5NDA4NjR9.gfJ7B1kY0DVKcKTxW3u2cIcPZvQjFEjPrcZnMjwb9do";
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    public final String API_URL = System.getenv("API_URL");
    @Test
    void getCapabilities_shouldReturnListOfCapabilities() {
        List<Capability> response = APP.client().target(API_URL + "/api/capabilities")
                .request()
                .header("Authorization", adminTokenThatNeverExpires)
                .get(List.class);
        Assertions.assertTrue(response.size() > 0);
    }
}