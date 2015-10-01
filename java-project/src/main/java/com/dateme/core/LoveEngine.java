package com.dateme;

public class LoveEngine {
    public static int compatibility(User user1, User user2) {
        int diff = Math.abs(user1.number - user2.number);

        if (diff > 10) {
            return 10 - (diff % 10);
        } else {
            return 10 - diff;
        }
    }
}
