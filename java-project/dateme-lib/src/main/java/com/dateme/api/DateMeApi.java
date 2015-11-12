package com.dateme.api;

import com.dateme.api.dao.DateMeDAO;
import com.dateme.core.model.RGB;
import com.dateme.core.model.Profile;
import com.dateme.core.model.User;

import java.util.List;
import java.util.Optional;

public class DateMeApi {

    final DateMeDAO dao;

    public DateMeApi(DateMeDAO dao) {
        this.dao = dao;
    }

    public User createUser(String email, String pw) {
        if (dao.getUser(email).isPresent()) {
            throw new IllegalArgumentException("User with the email is already in the system.");
        }
        User user = User.createUser(email, pw);
        dao.createUser(user);
        return user;
    }

    public Optional<Profile> getProfile(User user) {
        return dao.getProfile(user.email);
    }

    public Profile createProfile(User user, String name, RGB color, int number) {
        Profile profile = new Profile(user.email, name, color, number);
        if (dao.getProfile(user.email).isPresent()) {
            return dao.updateProfile(profile);
        } else {
            return dao.createProfile(profile);
        }
    }

    public Profile updateProfile(String email, String name, RGB color, int number) {
        Profile profile = new Profile(email, name, color, number);
        return dao.updateProfile(profile);
    }

    public Optional<User> findUserAccountByEmail(String email) {
        return dao.getUser(email);
    }

    public List<Profile> getAllProfiles() {
        return dao.allProfiles();
    }

    public List<User> getAllUsers() {
        return dao.allUsers();
    }
}
