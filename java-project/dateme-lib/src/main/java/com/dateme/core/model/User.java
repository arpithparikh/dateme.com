package com.dateme.core.model;


import org.mindrot.jbcrypt.BCrypt;

import java.security.Principal;

public class User implements Principal {
    public final String email;
    public final long createdTime;
    public final String enc;

    public User(String email, long createdTime, String enc) {
        this.email = email;
        this.createdTime = createdTime;
        this.enc = enc;
    }

    static public User createUser(String email, String pw) {
        return new User(email, System.currentTimeMillis(), encryptPassword(pw));
    }

    static public String encryptPassword(String pw) {
        return BCrypt.hashpw(pw, BCrypt.gensalt());
    }

    static public boolean validatePassword(String pw, User user) {
        return BCrypt.checkpw(pw, user.enc);
    }

    @Override
    public String getName() {
        return email;
    }

    public String getRole() {
        return email.equals("timothyk@gwu.edu") ? "ADMIN" : "USER";
    }
}
