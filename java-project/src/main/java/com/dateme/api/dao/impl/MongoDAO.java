package com.dateme.api.dao.impl;

import com.dateme.api.dao.DateMeDAO;
import com.dateme.core.User;

import java.util.List;
import java.util.Optional;

public class MongoDAO implements DateMeDAO {
    public User createUser(User user) {
        return null;
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
