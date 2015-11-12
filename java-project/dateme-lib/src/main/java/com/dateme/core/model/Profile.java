package com.dateme.core.model;

public class Profile {

    public final String email;
    public final String name;
    public final RGB color;
    public final int number;

    public Profile(String e, String name, RGB c, int n) {
        this.email = e;
        this.name = name;
        this.color = c;
        this.number = n;
    }

    public String toString() {
        return String.format("Profile(%s, %s, %d)", email, color.toHexString(), number);
    }

}
