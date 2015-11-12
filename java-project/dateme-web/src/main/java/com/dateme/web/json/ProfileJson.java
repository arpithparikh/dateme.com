package com.dateme.web.json;

import com.dateme.core.model.Profile;
import com.dateme.core.model.RGB;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileJson {
    private String name;
    private String color;
    private int number;

    public ProfileJson() {}

    public ProfileJson(Profile p) {
        this.name = p.name;
        this.color = p.color.toHexString();
        this.number = p.number;
    }


    @JsonProperty
    public String getName() {
        return name;
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
