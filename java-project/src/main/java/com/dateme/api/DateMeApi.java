package com.dateme.api;

import com.dateme.api.dao.DateMeDAO;
import com.dateme.core.RGB;
import com.dateme.core.User;

import java.util.Optional;

public class DateMeApi {

    final DateMeDAO dao;

    public DateMeApi(DateMeDAO dao) {
        this.dao = dao;
    }

    public User createUserAccount(String email, RGB c, int n) {
        User user = new User(email, c, n);
        User createdUser = dao.createUser(user);
        return createdUser;
    }

    public Optional<User> findUserAccountByEmail(String email) {
        Optional<User> result = dao.getUser(email);
        return result;
    }

    public Optional<User> updateUserAccount(String email, RGB c, int n) {
        if (dao.getUser(email).isPresent()) {
            User user = dao.updateUser(new User(email, c, n));
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }


}
