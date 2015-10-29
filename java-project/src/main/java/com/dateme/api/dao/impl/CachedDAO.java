package com.dateme.api.dao.impl;

import com.dateme.api.dao.DateMeDAO;
import com.dateme.core.model.Profile;

import java.util.List;
import java.util.Optional;

public abstract class CachedDAO implements DateMeDAO {

    final private DateMeDAO dao;

    public CachedDAO(DateMeDAO dao) {
        this.dao = dao;
    }

    public Profile createUser(Profile profile) {
        Profile result = dao.createUser(profile);
        //cache result
        return result;
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
