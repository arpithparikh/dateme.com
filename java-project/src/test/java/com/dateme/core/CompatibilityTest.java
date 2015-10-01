package com.dateme.core;

import org.junit.Assert;
import org.junit.Test;

public class CompatibilityTest {

    @Test
    public void perfectCompatibilityTest() {
        User user1 = new User("a@b.com", new RGB(255, 0, 0), 50);
        User user2 = new User("b@c.com", new RGB(255, 0, 0), 50);
        int score = LoveEngine.compatibility(user1, user2);
        Assert.assertEquals(10, score);
    }

    @Test
    public void numberDiffCompatibilityTest() {
        User user1 = new User("a@b.com", new RGB(255, 0, 0), 1);
        User user2 = new User("b@c.com", new RGB(255, 0, 0), 10);
        int score = LoveEngine.compatibility(user1, user2);

        Assert.assertEquals(1, score);

        User user3 = new User("a@b.com", new RGB(255, 0, 0), 5);
        User user4 = new User("b@c.com", new RGB(255, 0, 0), 100);
        int score2 = LoveEngine.compatibility(user3, user4);

        Assert.assertEquals(5, score2);

    }

}
