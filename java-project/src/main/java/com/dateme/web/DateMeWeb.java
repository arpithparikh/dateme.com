package com.dateme.web;

import com.dateme.api.DateMeApi;
import com.dateme.api.dao.DateMeDAO;
import com.dateme.api.dao.impl.SqliteDAO;
import com.dateme.web.health.DateMeHealthCheck;
import com.dateme.web.resource.ProfileResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DateMeWeb extends Application<DateMeConfiguration> {
    public static void main(String[] args) throws Exception {
        new DateMeWeb().run(args);
    }


    @Override
    public void run(DateMeConfiguration config, Environment environment) throws Exception {
        //DateMeDAO dao = new SqliteDAO("jdbc:sqlite:/tmp/dateme.db");
        final DateMeDAO dao = new SqliteDAO(config.getDb());
        final DateMeApi api = new DateMeApi(dao);
        final DateMeHealthCheck healthCheck = new DateMeHealthCheck();
        environment.healthChecks().register("dateme", healthCheck);

        final ProfileResource resource = new ProfileResource(api);
        environment.jersey().register(resource);
    }
}
