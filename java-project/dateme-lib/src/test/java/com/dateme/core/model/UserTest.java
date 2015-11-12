package com.dateme.core.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testValidatePassword() throws Exception {

        String password = "password";
        User user = User.createUser("timothyk@gwu.edu", password);

        Assert.assertEquals(true, User.validatePassword(password, user));
        Assert.assertEquals(false, User.validatePassword(password+"!", user));



    }
}