package com.dateme.web;

import com.dateme.api.DateMeApi;
import com.dateme.api.dao.DateMeDAO;
import com.dateme.api.dao.impl.SqliteDAO;
import com.dateme.core.model.User;
import com.dateme.web.auth.BasicAuthenticator;
import com.dateme.web.auth.BasicAuthorizer;
import com.dateme.web.filter.CORSFilter;
import com.dateme.web.health.DateMeHealthCheck;
import com.dateme.web.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import static org.eclipse.jetty.servlets.CrossOriginFilter.*;

public class DateMeWeb extends Application<DateMeConfiguration> {
    public static void main(String[] args) throws Exception {
        new DateMeWeb().run(args);
    }

    @Override
    public void initialize(Bootstrap<DateMeConfiguration> config) {
        //config.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }

    @Override
    public void run(DateMeConfiguration config, Environment environment) throws Exception {
        final DateMeDAO dao = new SqliteDAO(config.getDb());
        final DateMeApi api = new DateMeApi(dao);
        final DateMeHealthCheck healthCheck = new DateMeHealthCheck();
        environment.healthChecks().register("dateme", healthCheck);


        final UserResource resource = new UserResource(api);
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(resource);
        environment.jersey().register(
                new AuthDynamicFeature(
                        new BasicCredentialAuthFilter.Builder<User>()
                                .setAuthenticator(new BasicAuthenticator(api))
                                .setAuthorizer(new BasicAuthorizer())
                                .setRealm("as532zb^!FyzhD8!Xy98x92oyUBQWBfmdAJHMWJWUw6RRkkKTzVrXJT4&Y32ebV*")
                                .buildAuthFilter())
        );
        environment.jersey().register(CORSFilter.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
