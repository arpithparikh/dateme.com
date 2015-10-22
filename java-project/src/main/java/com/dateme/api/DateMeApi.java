package com.dateme.api;

import com.dateme.api.dao.DateMeDAO;
import com.dateme.core.RGB;
import com.dateme.core.Profile;

import java.util.Optional;

public class DateMeApi {

    final DateMeDAO dao;

    public DateMeApi(DateMeDAO dao) {
        this.dao = dao;
    }

    public Profile createUserAccount(String email, RGB c, int n) {
        Profile profile = new Profile(email, c, n);
        Profile createdProfile = dao.createUser(profile);
        return createdProfile;
    }

    public Optional<Profile> findUserAccountByEmail(String email) {
        Optional<Profile> result = dao.getUser(email);
        return result;
    }

    public Optional<Profile> updateUserAccount(String email, RGB c, int n) {
        if (dao.getUser(email).isPresent()) {
            Profile profile = dao.updateUser(new Profile(email, c, n));
            return Optional.of(profile);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Profile> removeAccount(String email) {
        return dao.deleteUser(email);
    }


}
