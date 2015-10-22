package com.dateme.core;

public class RGB {
    private final int red;
    private final int green;
    private final int blue;

    public RGB(int r, int g, int b) {
        if (r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255) {
            this.red = r;
            this.green = g;
            this.blue = b;
        } else {
            this.red = 0;
            this.green = 0;
            this.blue = 0;
        }
    }

    public RGB(String hex) {
        String r = hex.substring(1, 3);
        String g = hex.substring(3, 5);
        String b = hex.substring(5, 7);
        this.red = Integer.parseInt(r, 16);
        this.green = Integer.parseInt(g, 16);
        this.blue = Integer.parseInt(b, 16);
    }

    // Assume x is between 0 and 255, if not treat it as 0.
    private String toHexString(int x) {
        if (x >= 0 && x <= 255) {
            String c = Integer.toHexString(x);
            if (c.length() == 1) return "0"+c;
            else return c;
        } else {
            return "00";
        }
    }

    public String toHexString() {
        return "#" + toHexString(this.red) + toHexString(this.green) + toHexString(this.blue);
    }
}
