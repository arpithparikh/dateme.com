package com.dateme.core;

public class LoveEngine {
    public static int compatibility(Profile profile1, Profile profile2) {
        int diff = Math.abs(profile1.number - profile2.number);

        if (diff > 10) {
            return 10 - (diff % 10);
        } else {
            return 10 - diff;
        }
    }
}
