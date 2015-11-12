package com.dateme.web.auth;

import com.dateme.api.DateMeApi;
import com.dateme.core.model.User;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {
    private DateMeApi api;

    public BasicAuthenticator(DateMeApi api) {
        this.api = api;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        java.util.Optional<User> result = api.findUserAccountByEmail(credentials.getUsername());
        if (result.isPresent()) {
            User u = result.get();
            if (User.validatePassword(credentials.getPassword(), u)) {
                return Optional.of(u);
            } else {
                return Optional.absent();
            }
        } else {
            return Optional.absent();
        }
    }
}
