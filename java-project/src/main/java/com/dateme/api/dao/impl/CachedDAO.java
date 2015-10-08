package com.dateme.api.dao.impl;

import com.dateme.api.dao.DateMeDAO;
import com.dateme.core.User;

import java.util.List;
import java.util.Optional;

public class CachedDAO implements DateMeDAO {

    final private DateMeDAO dao;

    public CachedDAO(DateMeDAO dao) {
        this.dao = dao;
    }

    public User createUser(User user) {
        User result = dao.createUser(user);
        //cache result
        return result;
    }

    public Optional<User> getUser(String email) {
        return null;
    }

    public User updateUser(User user) {
        return null;
    }

    public User deleteUser(String email) {
        return null;
    }

    public List<User> findMostCompatible(User user, int count) {
        return null;
    }
}
