package com.dateme.web.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreationJson {
    private String email;
    private String name;
    private String pw;
    private String rgb;
    private int number;

    public UserCreationJson() {}

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public String getPw() {
        return pw;
    }

    @JsonProperty
    public String getRgb() {
        return rgb;
    }

    @JsonProperty
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
