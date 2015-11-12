package com.dateme.web.json;

import com.dateme.api.DateMeApi;
import com.dateme.core.model.Profile;
import com.dateme.core.model.RGB;
import com.dateme.core.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class UserJson {
    private String email;
    private long createdTime;
    private Optional<Profile> profile;

    public UserJson() {}

    public UserJson(User u, Optional<Profile> profile) {
        this.email = u.email;
        this.createdTime = u.createdTime;
        this.profile = profile;
    }

    public Optional<User> toUser(DateMeApi api) {
        return api.findUserAccountByEmail(this.email);
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public long getCreatedTime() {
        return createdTime;
    }

    @JsonProperty
    public ProfileJson getProfile() {
        if (profile.isPresent()) {
            return new ProfileJson(profile.get());
        } else {
            return null;
        }
    }
}
