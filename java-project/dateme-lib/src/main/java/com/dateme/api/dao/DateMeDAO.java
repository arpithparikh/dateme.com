package com.dateme.api.dao;

import com.dateme.core.model.Profile;
import com.dateme.core.model.User;

import java.util.List;
import java.util.Optional;

public interface DateMeDAO {

    public Profile createProfile(Profile profile);
    public Optional<Profile> getProfile(String email);
    public Profile updateProfile(Profile profile);
    public Optional<Profile> deleteProfile(String email);
    public List<Profile> allProfiles();

    public User createUser(User user);
    public Optional<User> getUser(String email);
    public User updateUser(User User);
    public Optional<User> deleteUser(String email);
    public List<User> allUsers();

    public List<Profile> findMostCompatible(Profile profile, int count);

}
