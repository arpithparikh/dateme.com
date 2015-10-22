package com.dateme.api.dao.impl;

import com.dateme.api.dao.DateMeDAO;
import com.dateme.core.Profile;

import java.util.List;
import java.util.Optional;

public class MongoDAO implements DateMeDAO {
    public Profile createUser(Profile profile) {
        return null;
    }

    public Optional<Profile> getUser(String email) {
        return null;
    }

    public Profile updateUser(Profile profile) {
        return null;
    }

    public Optional<Profile> deleteUser(String email) {
        return null;
    }

    public List<Profile> findMostCompatible(Profile profile, int count) {
        return null;
    }
}
