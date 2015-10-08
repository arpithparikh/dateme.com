package com.dateme.api.dao;

import com.dateme.core.User;

import java.util.List;
import java.util.Optional;

public interface DateMeDAO {

    public User createUser(User user);
    public Optional<User> getUser(String email);
    public User updateUser(User user);
    public User deleteUser(String email);

    public List<User> findMostCompatible(User user, int count);

}
