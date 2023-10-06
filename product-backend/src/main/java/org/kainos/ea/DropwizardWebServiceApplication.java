package org.kainos.ea;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.controller.CapabilityController;
import org.kainos.ea.controller.JobController;
import org.kainos.ea.controller.JobRoleController;
import org.kainos.ea.controller.AuthController;
import org.kainos.ea.dao.JobDao;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.service.AuthService;
import org.kainos.ea.service.JobService;

public class DropwizardWebServiceApplication extends Application<DropwizardWebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardWebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardWebService";
    }

    @Override
    public void initialize(final Bootstrap bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropwizardWebServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardWebServiceConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(DropwizardWebServiceConfiguration dropwizardWebServiceConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new JobController(new JobService(new JobDao(), new DatabaseConnector())));
        environment.jersey().register(new CapabilityController());
        environment.jersey().register(new JobRoleController());
        environment.jersey().register(new AuthController(new AuthService(new AuthDao())));
    }
}