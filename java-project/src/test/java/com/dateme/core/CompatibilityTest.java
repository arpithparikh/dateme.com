package com.dateme.core;

import org.junit.Assert;
import org.junit.Test;

public class CompatibilityTest {

    @Test
    public void perfectCompatibilityTest() {
        Profile profile1 = new Profile("a@b.com", new RGB(255, 0, 0), 50);
        Profile profile2 = new Profile("b@c.com", new RGB(255, 0, 0), 50);
        int score = LoveEngine.compatibility(profile1, profile2);
        Assert.assertEquals(10, score);
    }

    @Test
    public void numberDiffCompatibilityTest() {
        Profile profile1 = new Profile("a@b.com", new RGB(255, 0, 0), 1);
        Profile profile2 = new Profile("b@c.com", new RGB(255, 0, 0), 10);
        int score = LoveEngine.compatibility(profile1, profile2);

        Assert.assertEquals(1, score);

        Profile profile3 = new Profile("a@b.com", new RGB(255, 0, 0), 5);
        Profile profile4 = new Profile("b@c.com", new RGB(255, 0, 0), 100);
        int score2 = LoveEngine.compatibility(profile3, profile4);

        Assert.assertEquals(5, score2);

    }

}
