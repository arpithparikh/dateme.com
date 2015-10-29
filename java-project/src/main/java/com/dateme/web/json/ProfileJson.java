package com.dateme.web.json;

import com.dateme.core.model.Profile;
import com.dateme.core.model.RGB;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileJson {
    private String email;
    private String color;
    private int number;

    public ProfileJson() {}

    public ProfileJson(Profile p) {
        this.email = p.email;
        this.color = p.color.toHexString();
        this.number = p.number;
    }

    public Profile toProfile() {
        return new Profile(email, new RGB(color), number);
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public String getColor() {
        return color;
    }

    @JsonProperty
    public int getNumber() {
        return number;
    }
}
