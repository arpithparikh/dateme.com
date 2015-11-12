package com.dateme.web.auth;

import com.dateme.core.model.User;
import io.dropwizard.auth.Authorizer;

public class BasicAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User principal, String role) {
        return role.equals(principal.getRole());
    }
}
