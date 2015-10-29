package com.dateme.core.model;

public class Profile {

    public final String email;
    public final RGB color;
    public final int number;

    public Profile(String e, RGB c, int n) {
        this.email = e;
        this.color = c;
        this.number = n;
    }

    public String toString() {
        return String.format("Profile(%s, %s, %d)", email, color.toHexString(), number);
    }

}
